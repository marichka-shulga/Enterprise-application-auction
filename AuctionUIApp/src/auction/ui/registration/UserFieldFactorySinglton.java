package auction.ui.registration;

public class UserFieldFactorySinglton {
	
	   public static class SingletonHolder {
	        public static final UserFieldFactory USER_FIELD_FACTORY = new UserFieldFactory();
	    }
	        
	    public static UserFieldFactory getUserFieldFactory() {
	        return SingletonHolder.USER_FIELD_FACTORY;
	    }
}
