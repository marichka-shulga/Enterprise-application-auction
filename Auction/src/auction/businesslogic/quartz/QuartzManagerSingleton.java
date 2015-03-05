package auction.businesslogic.quartz;

/**
 * @author Marichka
 *
 */
public class QuartzManagerSingleton {

	public static class SingletonHolder {
	        public static final QuartzManager QUARTZ_MANAGER = new QuartzManager();
	    }
	        
	    public static QuartzManager getQuartzManager() {
	        return SingletonHolder.QUARTZ_MANAGER;
	    }

	    public static void shutdownQuartzManager() {
			SingletonHolder.QUARTZ_MANAGER.shutdown();
	    }	
	
}
