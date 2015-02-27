package auction.facadeservice;

import java.util.Set;

import auction.businesslogic.modelBL.BidLogic;
import auction.businesslogic.modelBL.LotLogic;
import auction.businesslogic.modelBL.UserLogic;
import auction.dao.LotDAO;
import auction.model.Bid;
import auction.model.Lot;
import auction.model.User;

public class FacadeService {
	private UserLogic userLogic;
	private LotLogic lotLogic;
	private BidLogic bidLogic;
	private LotDAO lotDAO;
	
	public FacadeService(){
		userLogic = new UserLogic();
		lotLogic = new LotLogic();
		bidLogic = new BidLogic(); 
		lotDAO = new LotDAO();
	}

	//invoke at load system
	public boolean firstLoadSystem(){
		return lotLogic.firstAssignJobToLot();
	}		
	
	//invoke at close system
	public void releaseResource(){
		lotLogic.closeQuartzManager();
		lotDAO.closeEntityManagerFactory();
	}	
	
	public User userAuthentication(String login, String password){
		return userLogic.authentication(login, password);
	}
	
	public boolean userRegistration(User user){
		return userLogic.registration(user);
	}

	public Set<Lot> loadAllLots(){
		return lotDAO.getLots(false);
	}	

	//do not forgot add user in lot (lot.setUser(user))
	public boolean addLot(Lot lot){
		return lotLogic.addLot(lot);
	}		
	
	public boolean cancelLot(Lot lot){
		return lotLogic.cancelOfTrades(lot);
	}	
	
	//do not forgot add user in bid (bid.setUser(user))	
	public boolean addBid(Lot lot, Bid bid){
		return bidLogic.addBid(lot, bid);
	}		
	
}
