package auction.ui.addlot;

public class LotFieldFactorySinglton {

	
	   public static class SingletonHolder {
	        public static final LotFieldFactory LOT_FIELD_FACTORY = new LotFieldFactory();
	    }
	        
	    public static LotFieldFactory getLotFieldFactory() {
	        return SingletonHolder.LOT_FIELD_FACTORY;
	    }

	
}
