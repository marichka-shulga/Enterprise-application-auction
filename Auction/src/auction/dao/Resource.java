package auction.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class  Resource {
	
	private static final String UNIT_NAME = "auction";	
	
	   public static class SingletonHolder {
	        public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(UNIT_NAME);
	    }
	        
	    public static EntityManagerFactory getEntityManagerFactory() {
	        return SingletonHolder.ENTITY_MANAGER_FACTORY;
	    }

	    public static void closeEntityManagerFactory() {
			if( (null != SingletonHolder.ENTITY_MANAGER_FACTORY) && (SingletonHolder.ENTITY_MANAGER_FACTORY.isOpen()) ){
				SingletonHolder.ENTITY_MANAGER_FACTORY.close();
			}
	    }
	    

	
}
