package auction.businesslogic.quartz;

import java.sql.Timestamp;

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

import auction.log.LogFactory;

public class QuartzManager {

	private static final String JOB_NAME = "JOB_NAME";

	private static final String JOB_GROUP = "JOB_GROUP";

	private SchedulerFactory schedFactory;
	
	private static final Logger LOGGRER = LogFactory.getLogger(QuartzManager.class);

	public QuartzManager() {
		
		try {
		schedFactory = new org.quartz.impl.StdSchedulerFactory();
		} catch (Exception e) {
			LOGGRER.error("Some problem with eclipse reason={}", e.getMessage());
		}
		
	}

	public void shutdown() {
		try {
			schedFactory.getScheduler().shutdown(true);
		} catch (SchedulerException e) {
			LOGGRER.error("Is not satisfied: shutdown QuartzManager reason={}", e.getMessage());	
		}
	}

	public void addJob(final String triggerId, final Timestamp date, final Class<? extends Job> jobClass) throws SchedulerException {
		Scheduler sched = schedFactory.getScheduler();
		if (!sched.isStarted()) {
			sched.start();
		}

		JobDetail job = JobBuilder.newJob().withIdentity(JOB_NAME, JOB_GROUP).ofType(jobClass).build();

		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(triggerId, JOB_GROUP).forJob(job).startAt(date)
				.build();
		sched.getContext().put(trigger.getKey().toString(), triggerId);

		if (sched.checkExists(job.getKey())) {
			sched.scheduleJob(trigger);
		} else {
			sched.scheduleJob(job, trigger);
		}
		LOGGRER.info("Add job QuartzManager: idLot={}", triggerId);
	}

	public void removeTrigger(String triggerId) throws SchedulerException {
		SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
		Scheduler sched = schedulerFactory.getScheduler();
		sched.unscheduleJob(new TriggerKey(triggerId, JOB_GROUP));
		sched.getContext().remove(new TriggerKey(triggerId, JOB_GROUP));
		LOGGRER.info("Remove job QuartzManager: idLot={}", triggerId);	
	}

}
