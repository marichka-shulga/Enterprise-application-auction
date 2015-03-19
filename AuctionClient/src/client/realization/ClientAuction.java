package client.realization;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.logging.log4j.Logger;

import client.artefacts.AuctionService;
import client.artefacts.BaseResponse;
import client.artefacts.FacadeService;
import client.artefacts.GetBidsByIdLotResponse;
import client.artefacts.GetLotsResponse;
import client.artefacts.User;
import client.artefacts.Lot;
import client.artefacts.Bid;
import client.artefacts.UserAuthenticResponse;
import client.artefacts.StateResult;
import client.log.LogFactory;

public class ClientAuction {
	
	private static final Logger LOGGRER = LogFactory.getLogger(ClientAuction.class);	
	
	private String namespaceService;
	private String serviceName;
	private String wsdlURL;		
	
	private static FacadeService port = null;
	
	public void setNamespaceService(String namespaceService) {
		this.namespaceService = namespaceService;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setWsdlURL(String wsdlURL) {
		this.wsdlURL = wsdlURL;
	}
	
	private FacadeService getFacadeService() throws Exception {
		if( null == port ){
			try{
				QName Q_NAME = new QName(namespaceService,serviceName);
				URL url = new URL(wsdlURL);
	        		AuctionService auctionService = new AuctionService(url, Q_NAME);
	        		port = auctionService.getAuctionServicePort();
			}
			catch (Exception e){
				LOGGRER.error("Is not satisfied getFacadeService={} reason={}", e, e.getMessage());		
				throw e;
			}
		}
		return port;
	}
	
	public BaseResponse userRegistration(User user){
		BaseResponse response = null;
		try {
			 response = getFacadeService().userRegistration(user);
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied userRegistration={} reason={}", e, e.getMessage());	
			response = new BaseResponse();
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}
	
	public UserAuthenticResponse userAuthentication(String login, String password){
		UserAuthenticResponse response = null;
		try {
			 response = getFacadeService().userAuthentication(login, password);
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied userAuthentication={} reason={}", e, e.getMessage());
			response = new UserAuthenticResponse();
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}

	public GetLotsResponse getAllLots(){
		GetLotsResponse response = null;
		try {
			 response = getFacadeService().getAllLots();
		} catch (Exception e) {
			response = new GetLotsResponse();
			LOGGRER.error("Is not satisfied getAllLots={} reason={}", e, e.getMessage());	
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}
	
	public BaseResponse addLot(Lot lot){
		BaseResponse response = null;
		try {
			 response = getFacadeService().addLot(lot);

		} catch (Exception e) {
			LOGGRER.error("Is not satisfied addLot={} reason={}", e, e.getMessage());	
			response = new BaseResponse();
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}	
	
	public BaseResponse addBid(Bid bid){
		BaseResponse response = null;
		try {
			 response = getFacadeService().addBid(bid);

		} catch (Exception e) {
			LOGGRER.error("Is not satisfied addBid={} reason={}", e, e.getMessage());	
			response = new BaseResponse();
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}	

	public BaseResponse cancelLot(Lot lot){
		BaseResponse response = null;
		try {
			 response = getFacadeService().cancelLot(lot);

		} catch (Exception e) {
			LOGGRER.error("Is not satisfied cancelLot={} reason={}", e, e.getMessage());	
			response = new BaseResponse();
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}	
	
	public GetBidsByIdLotResponse getBids(Integer idLot){
		GetBidsByIdLotResponse response = null;
		try {
			 response = getFacadeService().getBids(idLot);
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied getBids={} reason={}", e, e.getMessage());	
			response = new GetBidsByIdLotResponse();
			response.setStateResult(StateResult.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}	
	
}
