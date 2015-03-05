package client.realization;

import org.apache.logging.log4j.Logger;

import client.log.LogFactory;
import client.artefacts.BaseResponse;
import client.artefacts.FacadeService;
import client.artefacts.GetLotByIdResponse;
import client.artefacts.GetLotsResponse;
import client.artefacts.User;
import client.artefacts.Lot;
import client.artefacts.Bid;
import client.artefacts.UserAuthenticResponse;
import client.artefacts.StateResult;

public class ClientAuction {
	
	private static final Logger LOGGRER = LogFactory.getLogger(ClientAuction.class);	
	
	private static final  FacadeService port = AuctionServiceSinglton.getFacadeService();
	
	public BaseResponse userRegistration(User user){
		BaseResponse response = null;
		try {
			 response = port.userRegistration(user);
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied userRegistration={} reason={}", e, e.getMessage());	
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}
	
	public UserAuthenticResponse userAuthentication(String login, String password){
		UserAuthenticResponse response = null;
		try {
			 response = port.userAuthentication(login, password);
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied userAuthentication={} reason={}", e, e.getMessage());	
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}

	public GetLotsResponse getAllLots(){
		GetLotsResponse response = null;
		try {
			 response = port.getAllLots();
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied getAllLots={} reason={}", e, e.getMessage());	
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}
	
	public BaseResponse addLot(Lot lot){
		BaseResponse response = null;
		try {
			 response = port.addLot(lot);

		} catch (Exception e) {
			LOGGRER.error("Is not satisfied addLot={} reason={}", e, e.getMessage());	
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}	
	
	public BaseResponse addBid(Bid bid){
		BaseResponse response = null;
		try {
			 response = port.addBid(bid);

		} catch (Exception e) {
			LOGGRER.error("Is not satisfied addBid={} reason={}", e, e.getMessage());	
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}	

	public BaseResponse cancelLot(Lot lot){
		BaseResponse response = null;
		try {
			 response = port.cancelLot(lot);

		} catch (Exception e) {
			LOGGRER.error("Is not satisfied cancelLot={} reason={}", e, e.getMessage());	
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}	
	
	public GetLotByIdResponse getLot(Integer idLot){
		GetLotByIdResponse response = null;
		try {
			 response = port.getLot(idLot);

		} catch (Exception e) {
			LOGGRER.error("Is not satisfied getLot={} reason={}", e, e.getMessage());	
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}
}
