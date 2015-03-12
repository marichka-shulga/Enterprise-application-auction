package auction.businesslogic.modelBL;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.quartz.SchedulerException;

import auction.businesslogic.quartz.FinishTradesJob;
import auction.businesslogic.quartz.QuartzManager;
import auction.businesslogic.quartz.QuartzManagerSingleton;
import auction.dao.BidDAO;
import auction.dao.LotDAO;
import auction.log.LogFactory;
import auction.model.Bid;
import auction.model.Lot;
import auction.model.LotState;
import auction.service.response.BaseResponse;
import auction.service.response.StateResult;

public class LotLogic {
	private LotDAO lotDAO;
	private BidDAO bidDAO;
	private static final QuartzManager manager = QuartzManagerSingleton.getQuartzManager();
	
	private static final Logger LOGGRER = LogFactory.getLogger(LotLogic.class);
	
	public LotLogic(){		
		lotDAO = new LotDAO();
		bidDAO = new BidDAO();
	}
	
	public BaseResponse addLot(Lot lot){
		BaseResponse res = new BaseResponse();
		try {
			lotDAO.save(lot);
			manager.addJob(String.valueOf(lot.getIdLot()),lot.getFinishDate(),FinishTradesJob.class);
			res.setStateResult(StateResult.SUCCESS);
			res.setIdEntity(lot.getIdLot());
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied addLot={}, reason={}, idLot={}, userLogin={}", 
					e, e.getMessage(), lot.getIdLot(), lot.getUser().getUserLogin());
			res.setStateResult(StateResult.ERROR);
			res.setErrorMessage(e.getMessage());
		}
		
		LOGGRER.info("The addition lot successfully idLot={}, userLogin={}", 
					lot.getIdLot(), lot.getUser().getUserLogin());

		return res;
	}
	
	
	public BaseResponse cancelOfTrades(Lot lot){
		
		BaseResponse res = new BaseResponse();
		if( lot.getState().equals(LotState.ACTIVE) ){
			lot.setState(LotState.CANCELLED);		
			try {
				lotDAO.update(lot);
				manager.removeTrigger(String.valueOf(lot.getIdLot()));
				res.setStateResult(StateResult.SUCCESS);
			} catch (Exception e) {
				LOGGRER.error("Is not satisfied cancelOfTrades={}, reason={}, idLot={}", 
						e, e.getMessage(), lot.getIdLot());
				res.setStateResult(StateResult.ERROR);
				res.setErrorMessage(e.getMessage());	
			}
			LOGGRER.info("Trades canceled idLot={}", lot.getIdLot());
		} else{
			res.setStateResult(StateResult.NOT_SUCCESS);
			LOGGRER.info("Trades not cancel idLot={}", lot.getIdLot());
		}
	
		return res;
	}	
	
//	public GetLotByIdResponse getLotById(Integer idLot){
//		GetLotByIdResponse res = new GetLotByIdResponse();
//		try {
//			res.setStateResult(StateResult.SUCCESS);
//			res.setLot(lotDAO.getObjectById(idLot));
//		} catch (Exception e) {
//			LOGGRER.error("Is not satisfied gelLotById={}, reason={}, idLot={}", 
//					e, e.getMessage(),idLot);
//			res.setStateResult(StateResult.ERROR);
//			res.setErrorMessage(e.getMessage());	
//		}
//
//		return res;
//	}	


	private LotState getStateLotAtFinishedTrades(Lot lot){
		LotState res = LotState.NOT_SOLD;
		if( 0 < bidDAO.getCountBidsForLot(lot.getIdLot()) ){
			res = LotState.SOLD;
		}
	
		return res;
	}

	public void finishTrades(Integer idLot){
		Lot lot = null;
		try {
			lot = lotDAO.getEntityById(idLot);
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied finishTrades getObjectById={}, reason={}, idLot={}", 
					e, e.getMessage(), idLot);
		}
		finishTrades(lot);
	}		
	
	private void finishTrades(Lot lot){
		lot.setState(getStateLotAtFinishedTrades(lot));
		Bid bid = null;
		try {
			bid = bidDAO.getWinningBid(lot.getIdLot());			
			if( null != bid){
				bid.setIsWinningBid(true);
				bidDAO.update(bid);
			}	
				lotDAO.update(lot);
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied finishTrades={}, reason={}, idLot={}", 
					e, e.getMessage(), lot.getIdLot());
		}
		
		LOGGRER.info("Finish trades idLot={}, idBid={}", lot.getIdLot());
		
	}	
	
	public void firstAssignJobToLot(){
		Set<Lot> activeLots = (lotDAO.getLots(true)).getListLots();
		Iterator<Lot> it = activeLots.iterator();
		
		while(it.hasNext()){
			Lot lot = it.next();
			Date curTime = new Date();
			if( !lot.getFinishDate().after(curTime) ){
				
				finishTrades(lot);
			}
			try {
				manager.addJob(String.valueOf(lot.getIdLot()),lot.getFinishDate(),FinishTradesJob.class);
			} catch (SchedulerException e) {
				LOGGRER.error("Is not satisfied firstLoadAllActiveLots={}, reason={}, idLot={}", 
						e, e.getMessage(), lot.getIdLot());
			}

		}
		
	}

	
}
