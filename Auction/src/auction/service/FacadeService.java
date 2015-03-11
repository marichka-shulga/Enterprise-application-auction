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
import auction.service.response.GetLotByIdResponse;
import auction.service.response.GetLotStateByIdLotResponse;
import auction.service.response.GetLotsResponse;
import auction.service.response.GetWinningBidByIdResponseResponse;
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
	private BidDAO bidDAO;
	
	public FacadeService(){
		userLogic = new UserLogic();
		lotLogic = new LotLogic();
		bidLogic = new BidLogic(); 
		lotDAO = new LotDAO();
		bidDAO = new BidDAO();
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
	

	@WebMethod
	public BaseResponse addLot(Lot lot){
		return lotLogic.addLot(lot);
	}		

	@WebMethod
	public BaseResponse cancelLot(Lot lot){
		return lotLogic.cancelOfTrades(lot);
	}	
	
	@WebMethod
	public BaseResponse addBid(Bid bid){
		return bidLogic.addBid(bid);
	}		
	
	@WebMethod
	public GetBidsByIdLotResponse getBids(Integer idLot){
		return bidDAO.getBids(idLot);
	}	
	
	@WebMethod
	public GetLotStateByIdLotResponse getLotState(Integer idLot){
		return lotDAO.getLotStateByLotId(idLot);
	}
	
	@WebMethod	
	public GetWinningBidByIdResponseResponse getWinningBid(Integer idLot){
		return  bidDAO.getWinningBidForLot(idLot);
	}
}
