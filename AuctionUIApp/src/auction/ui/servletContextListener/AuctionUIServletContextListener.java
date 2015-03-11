package auction.ui.servletContextListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import auction.ui.quartz.QuartzManagerSingleton;

public class AuctionUIServletContextListener implements ServletContextListener{
	
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		QuartzManagerSingleton.getQuartzManager().shutdown();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

	}

}
