package auction.ui;

import org.apache.logging.log4j.Logger;

import auction.ui.loadproperties.AuctionServiceProperties;
import auction.ui.log.LogFactory;
import client.realization.ClientAuction;

public class ClientAuctionSinglton {
		
		   public static class SingletonHolder {
			   
			   private static final Logger LOGGRER = LogFactory.getLogger(ClientAuctionSinglton.class);

			   private static ClientAuction getClientAuction(){
				   ClientAuction client = null;
				   try{
					   client = new ClientAuction();
					   AuctionServiceProperties auctionServiceProperties = new AuctionServiceProperties();
					   client.setNamespaceService(auctionServiceProperties.getNamespaceService());
					   client.setServiceName(auctionServiceProperties.getServiceName());
					   client.setWsdlURL(auctionServiceProperties.getWsdlURL());
	   
				   }catch(Exception e){
					   LOGGRER.error("Is not satisfied getClientAuction={}, reason={}", e, e.getMessage());
				   }
				   return client;
		        }
  
		        public static final ClientAuction CLIENT_AUCTION = getClientAuction();
		    }
		        
		    public static ClientAuction getClientAuction() {
		        return SingletonHolder.CLIENT_AUCTION;
		    }


}
