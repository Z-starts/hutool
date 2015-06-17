/**
 * 
 */
package com.zxx.hutool.ext.timer;

import java.util.concurrent.TimeUnit;

/**
 * @author zxx
 * 2014年12月18日
 */
public abstract class ITask implements Runnable {
	/**
	 * 起始延迟
	 */
	private long initialDelay;
	/**
	 * 延迟时间
	 */
	private long delay;
	/**
	 * 时间单位
	 */
	private TimeUnit unit;

	/**
	 * @return the delay
	 */
	public long getDelay() {
		return delay;
	}

	/**
	 * @param delay the delay to set
	 */
	public void setDelay(long delay) {
		this.delay = delay;
	}

	/**
	 * 默认为秒
	 * @return the unit
	 */
	public TimeUnit getUnit() {
		if (unit == null)
			unit = TimeUnit.SECONDS;
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(TimeUnit unit) {
		this.unit = unit;
	}

	/**
	 * @return the initialDelay
	 */
	public long getInitialDelay() {
		return initialDelay;
	}

	/**
	 * @param initialDelay the initialDelay to set
	 */
	public void setInitialDelay(long initialDelay) {
		this.initialDelay = initialDelay;
	}

}
