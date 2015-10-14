/**
 * 
 */
package com.zxx.hutool.ext.quartz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 * quartz 工具类
 * @author zxx
 *
 */
public class QuartzUtil {
	/**
	 * 生成带uuid的JobBuilder
	 * @param scheduler
	 * @param jobClass
	 * @param uuid  UUID.randomUUID().toString();
	 * @return
	 */
	public static JobBuilder newJobWithUUID(Scheduler scheduler, Class<? extends Job> jobClass, String uuid) {
		return JobBuilder.newJob(jobClass).usingJobData("id", uuid);
	}

	/**
	 * 暂停任务
	 * @param scheduler
	 * @param triggerName
	 * @param group
	 */
	public static void pauseTrigger(Scheduler scheduler, String triggerName, String group) {
		try {
			scheduler.pauseTrigger(TriggerKey.triggerKey(triggerName, group));//停止触发器  
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 恢复任务
	 * @param scheduler
	 * @param triggerName
	 * @param group
	 */
	public void resumeTrigger(Scheduler scheduler, String triggerName, String group) {
		try {
			scheduler.resumeTrigger(TriggerKey.triggerKey(triggerName, group));//重启触发器  
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 移除任务
	 * @param scheduler
	 * @param triggerName
	 * @param group
	 * @return
	 */
	public static boolean removeTrigger(Scheduler scheduler, String triggerName, String group) {
		try {
			scheduler.pauseTrigger(TriggerKey.triggerKey(triggerName, group));//停止触发器  

			return scheduler.unscheduleJob(TriggerKey.triggerKey(triggerName, group));//移除触发器  
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 删除任务
	 * @param scheduler
	 * @param jobDetail
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean removeJob(Scheduler scheduler, JobDetail jobDetail) throws SchedulerException {
		return scheduler.deleteJob(jobDetail.getKey());
	}

	/**
	 * 获取运行中的job
	 * @param scheduler
	 * @throws SchedulerException
	 */
	public static List<JobDetail> getRunningJobList(Scheduler scheduler) throws SchedulerException {
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<JobDetail> jobs = new ArrayList<JobDetail>();
		for (JobExecutionContext executingJob : executingJobs) {
			JobDetail jobDetail = executingJob.getJobDetail();
			jobs.add(jobDetail);
		}
		return jobs;
	}

	/**
	 * 获取一个在特定时间内一直触发的trigger
	 * @param triggerName
	 * @param triggerGroup
	 * @param intervalInSeconds
	 * @param uuid UUID.randomUUID().toString();
	 * @return
	 */
	public Trigger getRepeatForeverTrigerByInterval(String triggerName, String triggerGroup, int intervalInSeconds, String uuid) {
		return QuartzUtil.newTriggerWithUUID(uuid).withIdentity(triggerName, triggerGroup).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(intervalInSeconds).repeatForever())
				.build();
	}

	/**
	 * 创建带uuid的triggerbuilder
	 * @param uuid
	 * @return
	 */
	public static TriggerBuilder<Trigger> newTriggerWithUUID(String uuid) {
		return TriggerBuilder.newTrigger().usingJobData("id", uuid);
	}

	/**
	 * 创建特定名称的scheduler（默认配置，走持久化）
	 * @param name
	 * @return
	 * @throws SchedulerException
	 * @throws IOException 
	 */
	public static Scheduler newSchedulerWithName(String name) throws SchedulerException, IOException {
		return newSchedulerWithName(name, "quartz.properties");
	}

	/**
	 * 根据指定配置初始化
	 * @param name
	 * @param propertyName
	 * @return
	 * @throws SchedulerException
	 * @throws IOException
	 */
	public static Scheduler newSchedulerWithName(String name, String propertyName) throws SchedulerException, IOException {
		StdSchedulerFactory sf = new StdSchedulerFactory();
		Properties props = new Properties();
		props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyName));
		props.put("org.quartz.scheduler.instanceName", name);
		sf.initialize(props);
		return sf.getScheduler();
	}

	/**
	 * 根据特定key，获取触发器。
	 * @param scheduler
	 * @param uuid
	 * @param triggerName TriggerManager.noticeTrigerName
	 * @param triggerGroup GMContants.QUARTZ_GROUP_TRIGGER_NOTICE
	 * @return
	 * @throws SchedulerException S
	 */
	public static Trigger findTriggerByUUID(Scheduler scheduler, String triggerName, String uuid, String triggerGroup) throws SchedulerException {
		TriggerKey key = new TriggerKey(triggerName + uuid, triggerGroup);
		return scheduler.getTrigger(key);
	}
}
