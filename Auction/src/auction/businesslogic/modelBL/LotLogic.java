package auction.businesslogic.modelBL;


import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.quartz.SchedulerException;

import auction.businesslogic.quartz.FinishTradesJob;
import auction.businesslogic.quartz.QuartzManager;
import auction.dao.BidDAO;
import auction.dao.LotDAO;
import auction.dao.UserDAO;
import auction.log.LogFactory;
import auction.model.Bid;
import auction.model.Lot;
import auction.model.LotState;
import auction.model.User;
import auction.service.response.BaseResponse;
import auction.service.response.GetLotByIdResponse;
import auction.service.response.StateResult;

public class LotLogic {
	private LotDAO lotDAO;
	private UserDAO userDAO;
	private BidDAO bidDAO;
	private QuartzManager manager;
	
	private static final Logger LOGGRER = LogFactory.getLogger(LotLogic.class);
	
	public LotLogic(){
		lotDAO = new LotDAO();
		userDAO = new UserDAO();
		bidDAO = new BidDAO();
		manager = new QuartzManager();
	}
	
	public BaseResponse addLot(Lot lot){
		BaseResponse res = new BaseResponse();
		
		User user = lot.getUser();
		user.addLot(lot);
		try {
			userDAO.update(user);
			manager.addJob(String.valueOf(lot.getIdLot()),lot.getFinashDate(),FinishTradesJob.class);
			
			res.setStateResult(StateResult.SUCCESS);
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied addLot={}, reason={}, idLot={}, userLogin={}", 
					e, e.getMessage(), lot.getIdLot(), user.getUserLogin());
			res.setStateResult(StateResult.ERROR);
			res.setErrorMessage(e.getMessage());
		}
		
		LOGGRER.info("The addition lot successfully idLot={}, userLogin={}", 
					lot.getIdLot(), user.getUserLogin());

		return res;
	}
	
	
	public BaseResponse cancelOfTrades(Lot lot){
		BaseResponse res = new BaseResponse();
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
		
		
		return res;
	}	
	
	public GetLotByIdResponse getLotById(Integer idLot){
		GetLotByIdResponse res = new GetLotByIdResponse();
		try {
			res.setLot(lotDAO.getObjectById(idLot));
			res.setStateResult(StateResult.SUCCESS);
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied gelLotById={}, reason={}, idLot={}", 
					e, e.getMessage(),idLot);
			res.setStateResult(StateResult.ERROR);
			res.setErrorMessage(e.getMessage());	
		}

		return res;
	}	
	
	
	
	

	private LotState getStateLotAtFinishedTrades(Lot lot){
		LotState res = LotState.NOT_SOLD;
		
		if( !(lot.getBids().isEmpty()) ){
			res = LotState.SOLD;
		}
	
		return res;
	}

	public void finishTrades(Integer idLot){
		Lot lot = null;
		try {
			lot = lotDAO.getObjectById(idLot);
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
				lotDAO.update(lot);
			}
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied finishTrades={}, reason={}, idLot={}, idBit={}", 
					e, e.getMessage(), lot.getIdLot(), bid.getIdBid());
		}
		
		LOGGRER.info("Finish trades idLot={}, idBid={}", lot.getIdLot(), bid.getIdBid());
		
	}	
	
	public void firstAssignJobToLot(){
		Set<Lot> activeLots = (lotDAO.getLots(true)).getListLots();
		Iterator<Lot> it = activeLots.iterator();
		
		while(it.hasNext()){
			Lot lot = it.next();
			Timestamp curTime = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
			if( !lot.getFinashDate().after(curTime) ){
				finishTrades(lot);
			}
			try {
				manager.addJob(String.valueOf(lot.getIdLot()),lot.getFinashDate(),FinishTradesJob.class);
			} catch (SchedulerException e) {
				LOGGRER.error("Is not satisfied firstLoadAllActiveLots={}, reason={}, idLot={}", 
						e, e.getMessage(), lot.getIdLot());
			}

		}
		
	}

	
}
