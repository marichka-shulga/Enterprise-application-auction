package auction.ui;

import client.realization.ClientAuction;

public class ClientAuctionSinglton {
		
		   public static class SingletonHolder {
  

			   private static ClientAuction getClientAuction(){
				   ClientAuction client = null;
				   try{
					   
					   client = new ClientAuction();
					   
				   }catch(Exception e){
	
					   
				   }
				   return client;

		        }
  
		        public static final ClientAuction CLIENT_AUCTION = getClientAuction();
		    }
		        
		    public static ClientAuction getClientAuction() {
		        return SingletonHolder.CLIENT_AUCTION;
		    }


}
