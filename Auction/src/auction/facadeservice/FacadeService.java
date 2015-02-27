package auction.facadeservice;

import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import auction.businesslogic.modelBL.BidLogic;
import auction.businesslogic.modelBL.LotLogic;
import auction.businesslogic.modelBL.UserLogic;
import auction.dao.LotDAO;
import auction.model.Bid;
import auction.model.Lot;
import auction.model.User;

@WebService(serviceName = "AuctionSerce",
			portName = "AuctionSercePort",	
			targetNamespace = "http://auction.facadeservice/jaxws/AuctionSercePort")

@SOAPBinding(style=SOAPBinding.Style.DOCUMENT,use=SOAPBinding.Use.LITERAL,
			parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)

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
	@WebMethod
	public boolean firstLoadSystem(){
		return lotLogic.firstAssignJobToLot();
	}		
	
	//invoke at close system
	@WebMethod
	public void releaseResource(){
		lotLogic.closeQuartzManager();
		lotDAO.closeEntityManagerFactory();
	}	
	
	@WebMethod
	public User userAuthentication(String login, String password){
		return userLogic.authentication(login, password);
	}
	
	@WebMethod
	public boolean userRegistration(User user){
		return userLogic.registration(user);
	}

	@WebMethod
	public Set<Lot> loadAllLots(){
		return lotDAO.getLots(false);
	}	

	//do not forgot add user in lot (lot.setUser(user))
	@WebMethod
	public boolean addLot(Lot lot){
		return lotLogic.addLot(lot);
	}		
	
	@WebMethod
	public boolean cancelLot(Lot lot){
		return lotLogic.cancelOfTrades(lot);
	}	
	
	//do not forgot add user in bid (bid.setUser(user))	
	@WebMethod
	public boolean addBid(Lot lot, Bid bid){
		return bidLogic.addBid(lot, bid);
	}		
	
}
