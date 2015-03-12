package auction.ui.lotdetails;

public class LotDetailsFactorySingleton {
	
	   public static class SingletonHolder {
	        public static final LotDetailsFactory LOT_DETAILS_FACTORY = new LotDetailsFactory();
	    }
	        
	    public static LotDetailsFactory getLotDetailsFactory() {
	        return SingletonHolder.LOT_DETAILS_FACTORY;
	    }
}
