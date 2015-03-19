package auction.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import auction.businesslogic.modelBL.LotLogic;
import auction.businesslogic.quartz.QuartzManagerSingleton;
import auction.dao.Resource;


public class AuctionServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		QuartzManagerSingleton.getQuartzManager().shutdown();
		Resource.closeEntityManagerFactory();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		new LotLogic().firstAssignJobToLot();		
	}

}
