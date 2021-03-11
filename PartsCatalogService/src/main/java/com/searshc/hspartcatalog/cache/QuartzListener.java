package com.searshc.hspartcatalog.cache;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searshc.hspartcatalog.util.RandomUtils;

public class QuartzListener implements ServletContextListener {

	private Scheduler scheduler = null;

	private static final String SCHEDULE_PATTERN = "0 %s %s * * ?";
	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		LOGGER.info("ServletContext Initialized starting ChannelPriceJob to load override price...");

		try {
			JobKey channelPriceJobKey = JobKey.jobKey("ChannelPriceJob", "CacheGroup");
			JobDetail channelPriceJob = newJob(PriceOverrideCacheJob.class).withIdentity(channelPriceJobKey).build();
			
			JobKey brandJobKey = JobKey.jobKey("BrandJob", "CacheGroup");
			JobDetail brandJob = newJob(BrandCacheJob.class).withIdentity(brandJobKey).build();
			
			JobKey categoryJobKey = JobKey.jobKey("CategoryJob", "CacheGroup");
			JobDetail categoryJob = newJob(CategoryCacheJob.class).withIdentity(categoryJobKey).build();
			
			String channelPriceSchedule = createSchedule();
			String brandSchedule = createSchedule();
			String categorySchedule = createSchedule();
		
			Trigger channelPriceTrigger = newTrigger().withIdentity("ChannelPriceTriggerDaily", "CacheGroup").startNow()
					.withSchedule(CronScheduleBuilder.cronSchedule(channelPriceSchedule)).build();
			Trigger brandTrigger = newTrigger().withIdentity("BrandTriggerDaily", "CacheGroup").startNow()
					.withSchedule(CronScheduleBuilder.cronSchedule(brandSchedule)).build();
			Trigger categoryTrigger = newTrigger().withIdentity("CategoryTriggerDaily", "CacheGroup").startNow()
					.withSchedule(CronScheduleBuilder.cronSchedule(categorySchedule)).build();

			// Setup the Job and Trigger with Scheduler & schedule jobs
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(channelPriceJob, channelPriceTrigger);
			scheduler.scheduleJob(brandJob, brandTrigger);
			scheduler.scheduleJob(categoryJob, categoryTrigger);
			
			//fire job NOW
			scheduler.triggerJob(channelPriceJobKey);
			scheduler.triggerJob(brandJobKey);
			scheduler.triggerJob(categoryJobKey);
			
		} catch (Exception e) {
			LOGGER.error("ServletContext Initialized FAILED to start Jobs to load cache data...", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

		LOGGER.info("ServletContext Destroyed shutting down ChannelPriceJob scheduler...");

		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			LOGGER.error("ServletContext Destroyed FAILED to stop ChannelPriceJob scheduler...", e);
		}
	}

	private String createSchedule() {

		// Daily try to randomize a little across servers...
		int min = RandomUtils.inRange(0, 60);
		int hour = RandomUtils.inRange(1, 4);
		String schedule = String.format(SCHEDULE_PATTERN, min, hour);
		LOGGER.info("ChannelPriceJob to load and cache prices will be scheduled using >>> {}", schedule);

		return schedule;

	}
}
