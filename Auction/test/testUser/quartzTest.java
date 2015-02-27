package testUser;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;

import auction.businesslogic.quartz.FinishTradesJob;
import auction.businesslogic.quartz.QuartzManager;


public class quartzTest {

	@Test
	public void test() {
		QuartzManager manager = new QuartzManager();
		try {
			{
				Timestamp finishDate = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
				finishDate.setTime(new Date().getTime() + 2000);
//				Date date = new Date();
//				date.setTime(date.getTime() + 2000);
				
				manager.addJob("2",	finishDate, FinishTradesJob.class);
			}
			{
				Timestamp finishDate = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
				finishDate.setTime(new Date().getTime() + 3250);		
//				Date date = new Date();
//				date.setTime(date.getTime() + 3250);
				
				manager.addJob("3",	finishDate, FinishTradesJob.class);
			}
			{
//				Date date = new Date();
//				date.setTime(date.getTime() + 4000);
				Timestamp finishDate = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
				finishDate.setTime(new Date().getTime() + 4000);				
				manager.addJob("4",	finishDate, FinishTradesJob.class);
			}


			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO add log this
			// DO NOT WRITE ON CONSOLE AT ALL, WRITE TO LOG -> don't use this ->
			// e.printStackTrace();
			e.printStackTrace();
		} finally {
			manager.shutdown();
		}

	}


}
