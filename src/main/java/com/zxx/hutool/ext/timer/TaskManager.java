/**
 * 
 */
package com.zxx.hutool.ext.timer;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.xiaoleilu.hutool.DateUtil;

/**
 * 定时任务管理类
 * @author zxx
 * 2014年12月18日
 */
public class TaskManager {
	/**
	 * 调度线程池
	 */
	private static ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
	private static TaskManager ins = null;

	private TaskManager() {
	}

	/**
	 * 获取定时任务管理类实例
	 * @return
	 */
	public static TaskManager getInstance() {
		if (ins == null) {
			ins = new TaskManager();
		}
		return ins;
	}

	/**
	 * 添加定时任务
	 * @param taskS
	 */
	public void addFixedTask(ITask task) {
		ses.scheduleWithFixedDelay(task, task.getInitialDelay(), task.getDelay(), task.getUnit());
	}

	public static void main(String[] args) {
		ITask submoretask = new ITask() {
			@Override
			public void run() {
				System.err.println(DateUtil.formatDateTime(new Date()) + "开始一个task！");
			}
		};
		submoretask.setDelay(1 * 60);
		submoretask.setUnit(TimeUnit.SECONDS);
		submoretask.setInitialDelay(5);
		TaskManager.getInstance().addFixedTask(submoretask);
	}
}
