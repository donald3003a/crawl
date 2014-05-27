package com.xxx.crawl.common.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzSchedlue {
	
	public QuartzSchedlue() throws SchedulerException{
		startSchedule();
	}
	 public void startSchedule() throws SchedulerException
	    {
	        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	        Scheduler scheduler = schedulerFactory.getScheduler();
	        
	        JobDetail jobDetail = 
	        	new JobDetail("jobDetail", "jobDetailGroup", MailJob.class);
	        CronTrigger cronTrigger = new CronTrigger("cronTrigger", "triggerGroup2");
	        try {
	            cronTrigger.setCronExpression("0/5 * * * * ?");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        scheduler.scheduleJob(jobDetail, cronTrigger);
	        scheduler.start();
	    }
	 public static void main(String[] args) throws SchedulerException {
		 QuartzSchedlue schedule=new QuartzSchedlue();
	}
}
