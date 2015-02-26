package quartz;

import log.LogFactory;
import modelBL.LotLogic;

import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class FinishTradesJob implements Job {
	
	private static final Logger LOGGRER = LogFactory.getLogger(FinishTradesJob.class);
	
	private static LotLogic lotLogic;
	
//	private FinishTradesJob(){
//		if( null == lotLogic )
//			lotLogic = new LotLogic();	
//	}

	@Override
	public void execute(final JobExecutionContext context) throws JobExecutionException {
		
		lotLogic = new LotLogic();
		Object someId = null;
		try {
			someId = context.getScheduler().getContext().get(context.getTrigger().getKey().toString());
			System.out.println((String)someId);
			lotLogic.finishTrades(Integer.parseInt(someId.toString()));
			
			
			// SYSOUT int's a bad practice, use debug or trace logs =)
			//System.out.println(someId + ", real time is: " + realTime + "ms");
			
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied: execute job idLot={}, reason={}", (String)someId, e.getMessage());	
		}

	}

}