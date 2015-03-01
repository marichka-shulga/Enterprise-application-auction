package auction.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import auction.businesslogic.modelBL.LotLogic;
import auction.businesslogic.quartz.QuartzManager;
import auction.dao.Resource;


public class AuctionServletContextListener implements ServletContextListener {

	private LotLogic lotLogic;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		QuartzManager.shutdown();
		Resource.closeEntityManagerFactory();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Resource.getEntityManagerFactory();
		lotLogic = new LotLogic();
		QuartzManager.intitQuartzManager();
		lotLogic.firstAssignJobToLot();		
	}

}
