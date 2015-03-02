package client.realization;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.logging.log4j.Logger;

import client.artefacts.AuctionService;
import client.artefacts.FacadeService;
import client.log.LogFactory;


public class AuctionServiceSinglton {
	
	private static final Logger LOGGRER = LogFactory.getLogger(AuctionServiceSinglton.class);	
	
	   public static class SingletonHolder {
		   
			 private static final String NAMESPACE_SERVICE = "http://auction.facadeservice/jaxws/auctionservice";
			 private static final String SERVICE_NAME = "AuctionService";
			 private static final String WSDL_URL = "http://localhost:8080/Auction/AuctionService?wsdl";		   

		   private static FacadeService getFacadeService(){
	        	FacadeService port = null;
	        	try{
	        		QName Q_NAME = new QName(NAMESPACE_SERVICE,SERVICE_NAME);
	        		URL url = new URL(WSDL_URL);
		        	AuctionService auctionService = new AuctionService(url, Q_NAME);
		        	port = auctionService.getAuctionServicePort();
	        	}
	        	catch (Exception e){
	    			LOGGRER.error("Is not satisfied getFacadeService={} reason={}", e, e.getMessage());		        		
	        	}
	        	return port;
	        }
	        
	        public static final FacadeService PORT = getFacadeService();
	    }
	        
	    public static FacadeService getFacadeService() {
	        return SingletonHolder.PORT;
	    }

	
}
