package auction.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import auction.businesslogic.modelBL.BidLogic;
import auction.businesslogic.modelBL.LotLogic;
import auction.businesslogic.modelBL.UserLogic;
import auction.dao.BidDAO;
import auction.dao.LotDAO;
import auction.model.Bid;
import auction.model.Lot;
import auction.model.User;
import auction.service.response.BaseResponse;
import auction.service.response.GetBidsByIdLotResponse;
import auction.service.response.GetLotsResponse;
import auction.service.response.UserAuthenticResponse;

@WebService(serviceName = "AuctionService",
			portName = "AuctionServicePort",	
			targetNamespace = "http://auction.facadeservice/jaxws/auctionservice")

@SOAPBinding(style=SOAPBinding.Style.DOCUMENT,use=SOAPBinding.Use.LITERAL,
			parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)

public class FacadeService {

	@WebMethod
	public UserAuthenticResponse userAuthentication(String login, String password){
		UserLogic userLogic = new UserLogic();
		return userLogic.authentication(login, password);
	}
	
	@WebMethod
	public BaseResponse userRegistration(User user){
		UserLogic userLogic = new UserLogic();
		return userLogic.registration(user);
	}
	
	@WebMethod
	public GetLotsResponse getAllLots(){
		LotDAO lotDAO = new LotDAO();
		return lotDAO.getLots(false);
	}	
	
	@WebMethod
	public BaseResponse addLot(Lot lot){
		LotLogic lotLogic = new LotLogic();
		return lotLogic.addLot(lot);
	}		

	@WebMethod
	public BaseResponse cancelLot(Lot lot){
		LotLogic lotLogic = new LotLogic();
		return lotLogic.cancelOfTrades(lot);
	}	
	
	@WebMethod
	public BaseResponse addBid(Bid bid){
		BidLogic bidLogic = new BidLogic();
		return bidLogic.addBid(bid);
	}		
	
	@WebMethod
	public GetBidsByIdLotResponse getBids(Integer idLot){
		BidDAO bidDAO = new BidDAO();
		return bidDAO.getBids(idLot);
	}	
	
}
