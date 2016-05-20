/**
* ////////////////////////////////////////////////////////////////////
//                            _ooOoo_                             //
//                           o8888888o                            //
//                           88" . "88                            //
//                           (| -_- |)                            //
//                           O\  =  /O                            //
//                        ____/`---'\____                         //
//                      .'  \|     |//  `.                        //
//                     /  \|||  :  |||//  \                       //
//                    /  _||||| -:- |||||-  \                     //
//                    |   | \\  -  /// |    |                     //
//                    | \_|  ''\---/''  |   |                     //
//                    \  .-\__  `-`  ___/-. /                     //
//                  ___`. .'  /--.--\  `. . ___                   //
//                ."" '<  `.___\_<|>_/___.'  >'"".                //
//              | | :  `- \`.;`\ _ /`;.`/ - ` : | |               //
//              \  \ `-.   \_ __\ /__ _/   .-` /  /               //
//        ========`-.____`-.___\_____/___.-`____.-'========       //
//                             `=---='                            
//        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^      //
                      Buddha Bless non-existent Bugs                
////////////////////////////////////////////////////////////////////
 */
package com.zxx.hutool.ext;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.xiaoleilu.hutool.util.DateUtil;


/**
 * 日期格式化工具
 * @author zxx
 * 2015年6月11日
 */
public class ZDateUtils extends DateUtil {

	/**
	 * 获取一段时间内的日期列表；</br>
	 * 格式：yyyy-MM-dd;
	 * @param bdt
	 * @param edt
	 * @return 递增日期列表。
	 */
	public static List<String> getDaysBetweenRange(Date startDate, Date endDate) {
		List<String> days = new LinkedList<String>();
		final Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		while (true) {
			if (startDate.after(endDate)) {
				startDate = endDate;
			}
			days.add(getDay(cal));
			if (startDate.equals(endDate)) {
				break;
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
			startDate = cal.getTime();
		}
		return days;
	}

	private static String getDay(Calendar cal) {
		return DateUtil.formatDate(cal.getTime());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.err.println(getDaysBetweenRange(DateUtil.parse("2015-05-06"), DateUtil.parse("2015-05-11")));
	}

}
