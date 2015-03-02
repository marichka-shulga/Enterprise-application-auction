package auction.service;


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
import auction.service.response.BaseResponse;
import auction.service.response.GetLotByIdResponse;
import auction.service.response.GetLotsResponse;
import auction.service.response.UserAuthenticResponse;

@WebService(serviceName = "AuctionService",
			portName = "AuctionServicePort",	
			targetNamespace = "http://auction.facadeservice/jaxws/auctionservice")

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


	@WebMethod
	public UserAuthenticResponse userAuthentication(String login, String password){
		return userLogic.authentication(login, password);
	}
	
	@WebMethod
	public BaseResponse userRegistration(User user){
		return userLogic.registration(user);
	}
	
	@WebMethod
	public GetLotsResponse getAllLots(){
		return lotDAO.getLots(false);
	}	
	
	@WebMethod
	public GetLotByIdResponse getLot(Integer idLot){
		return lotLogic.getLotById(idLot);
	}	
	

	//do not forgot add user in lot (lot.setUser(user))
	@WebMethod
	public BaseResponse addLot(Lot lot){
		return lotLogic.addLot(lot);
	}		
	
	@WebMethod
	public BaseResponse cancelLot(Lot lot){
		return lotLogic.cancelOfTrades(lot);
	}	
	
	//do not forgot add user in bid (bid.setUser(user))	
	@WebMethod
	public BaseResponse addBid(Lot lot, Bid bid){
		return bidLogic.addBid(lot, bid);
	}		
	
}
