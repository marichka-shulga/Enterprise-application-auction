package auction.businesslogic.quartz;

import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import auction.businesslogic.modelBL.LotLogic;
import auction.log.LogFactory;


public class FinishTradesJob implements Job {
	
	private static final Logger LOGGRER = LogFactory.getLogger(FinishTradesJob.class);
	
	private static LotLogic lotLogic;
	
	@Override
	public void execute(final JobExecutionContext context) throws JobExecutionException {
		
		lotLogic = new LotLogic();
		Object someId = null;
		try {
			someId = context.getScheduler().getContext().get(context.getTrigger().getKey().toString());
			lotLogic.finishTrades(Integer.parseInt(someId.toString()));
			
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied: execute job idLot={}, reason={}", (String)someId, e.getMessage());	
		}

	}

}