package auction.businesslogic.quartz;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;


public class QuartzManager {

	private static final String JOB_NAME = "JOB_NAME";

	private static final String JOB_GROUP = "JOB_GROUP";

	private SchedulerFactory schedulerFactory;
	
	private static final Logger LOGGRER = LogManager.getLogger(QuartzManager.class);
	
	public QuartzManager(){
		try {
			schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied intitQuartzManager={}, reason={}", e, e.getMessage());
		}		
		
	}

	public void shutdown() {
		try {
			if( null != schedulerFactory )
				schedulerFactory.getScheduler().shutdown(true);
		} catch (SchedulerException e) {
			LOGGRER.error("Is not satisfied QuartzManager shutdown={}, reason={}", e, e.getMessage());	
		}
	}
	

	public void addJob(final String triggerId, final Date date, final Class<? extends Job> jobClass) throws SchedulerException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		if ( !scheduler.isStarted() ) {
			scheduler.start();
		}

		JobDetail job = JobBuilder.newJob().withIdentity(JOB_NAME, JOB_GROUP).ofType(jobClass).build();

		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(triggerId, JOB_GROUP).forJob(job).startAt(date)
				.build();
		scheduler.getContext().put(trigger.getKey().toString(), triggerId);

		if ( scheduler.checkExists(job.getKey()) ) {
			scheduler.scheduleJob(trigger);
		} else {
			scheduler.scheduleJob(job, trigger);
		}
		LOGGRER.info("Add job QuartzManager idLot={}", triggerId);
	}

	public void removeTrigger(String triggerId) throws SchedulerException {
		SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.unscheduleJob(new TriggerKey(triggerId, JOB_GROUP));
		scheduler.getContext().remove(new TriggerKey(triggerId, JOB_GROUP));
		LOGGRER.info("Remove job QuartzManager idLot={}", triggerId);	
	}

}
