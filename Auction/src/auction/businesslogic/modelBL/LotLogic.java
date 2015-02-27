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
		//////////////////////////////
		//когда закрывать????? 	manager.shutdown();
		manager = new QuartzManager();
		
	}
	
	public boolean addLot(Lot lot, User user){
		boolean res = false;

			user.addLot(lot);
			try {
				userDAO.update(user);
				res = true;
			} catch (Exception e) {
				LOGGRER.error("Is not satisfied: addLot idLot={}, logginUser={}, reason={}", 
								lot.getIdLot(), user.getLogin(), e.getMessage());
			}

			try {
				manager.addJob(String.valueOf(lot.getIdLot()),lot.getFinashDate(),FinishTradesJob.class);
			} catch (SchedulerException e) {
				LOGGRER.error("Is not satisfied: addLot{}, idLot={}, idUser{}, reason={}", 
						e, lot.getIdLot(), user.getIdUser(), e.getMessage());
			}
			
			String message = null;
			if( res )
				message = "The addition lot successfully: idLot={}, userLoggin={}";
		
			else
				message = "The addition lot not successfully: idLot={}, userLoggin={}";
			
			LOGGRER.info(message, lot.getIdLot(), user.getLogin());
			

		return res;
	}
	
	
	public boolean cancelOfTrades(Lot lot){
		boolean res = false;
		try {
			lot.setState(LotState.CANCELLED);
			lotDAO.update(lot);

			manager.removeTrigger(String.valueOf(lot.getIdLot()));
			
			res = true;
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied: cancelOfTrades{}, idLot={}, reason={}", 
					e, lot.getIdLot(), e.getMessage());
		}
		
		LOGGRER.info("Trades canceled: idLot={}", lot.getIdLot());
		
		
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
		Lot lot = lotDAO.getObjectById(idLot);
		finishTrades(lot);
	}		
	
	private void finishTrades(Lot lot){
		lot.setState(getStateLotAtFinishedTrades(lot));

		Bid bid = bidDAO.getWinningBid(lot.getIdLot());

		if( null != bid){
			bid.setIsWinningBid(true);
			try {
				bidDAO.update(bid);
			} catch (Exception e) {
				LOGGRER.error("Is not satisfied: finishTrades{}, idBit={}, reason={}", 
						e, bid.getIdBid(), e.getMessage());
			}
		}	
		try {
			lotDAO.update(lot);

		} catch (Exception e) {
			LOGGRER.error("Is not satisfied: finishTrades{}, idLot={}, reason={}", 
					e, lot.getIdLot(), e.getMessage());
		}
		
	}	
	
	public void firstAssignJobToLot(){
		Set<Lot> activeLots = lotDAO.getLots(true);
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
				LOGGRER.error("Is not satisfied: firstLoadAllActiveLots{}, idLot={}, reason={}", 
						e, lot.getIdLot(), e.getMessage());
			}

		}
	}
	
	
	
	
	
}
