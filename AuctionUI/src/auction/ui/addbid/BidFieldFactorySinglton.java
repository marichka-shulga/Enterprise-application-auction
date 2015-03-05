package auction.ui.addbid;

public class BidFieldFactorySinglton {
	
	   public static class SingletonHolder {
	        public static final BidFieldFactory BID_FIELD_FACTORY = new BidFieldFactory();
	    }
	        
	    public static BidFieldFactory getBidFieldFactory() {
	        return SingletonHolder.BID_FIELD_FACTORY;
	    }

}
