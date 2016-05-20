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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.xiaoleilu.hutool.util.MathUtil;


/**
 * @author zxx
 * 2015年6月17日
 */
public class ZMathUtil extends MathUtil {
	/**
	 * 格式化数据为###,####.##格式
	 * @param num 金额
	 * @param len 小数位数
	 * @return 格式后的金额
	 */
	public static String insertComma(double num, int len) {
		NumberFormat formater = null;
		if (len == 0) {
			formater = new DecimalFormat("###,###");

		} else {
			StringBuffer buff = new StringBuffer();
			buff.append("###,###.");
			for (int i = 0; i < len; i++) {
				buff.append("#");
			}
			formater = new DecimalFormat(buff.toString());
		}
		return formater.format(num);
	}

	/**
	 * 金额去掉“,”
	 * @param s 金额
	 * @return 去掉“,”后的金额
	 */
	public static String delComma(String s) {
		String formatString = "";
		if (s != null && s.length() >= 1) {
			formatString = s.replaceAll(",", "");
		}

		return formatString;
	}

	/**
	 * 保留小数位
	 * @param number 被保留小数的数字
	 * @param digit 保留的小数位数
	 * @return 保留小数后的字符串
	 */
	public static String roundStr(Number number, Number digit) {
		return String.format("%." + digit + 'f', number);
	}

	/**
	 * 保留小数位
	 * @param number 被保留小数的数字
	 * @param digit 保留的小数位数
	 * @return 保留小数后的字符串
	 */
	public static double round(double number, int digit) {
		final BigDecimal bg = new BigDecimal(number);
		return bg.setScale(digit, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 除法计算，保留小数
	 * @param y
	 * @param z
	 * @param digit 精度
	 * @return
	 */
	public static double divide(Number y, Number z, int digit) {
		double baiy = y.doubleValue() * 1.0;
		double baiz = z.doubleValue() * 1.0;
		if (baiz == 0) {
			return 0.00;
		} else {
			double fen = (baiy / baiz);
			return round(fen, digit);
		}
	}

	/**
	 * 计算百分比（含%）
	 * @param y
	 * @param z
	 * @return
	 */
	public static String caluate2PercentStr(Number y, Number z) {
		String baifenbi = "";// 接受百分比的值  
		double baiy = y.doubleValue() * 1.0;
		double baiz = z.doubleValue() * 1.0;
		double fen = 0.00;
		if (baiz != 0) {
			fen = (baiy / baiz);
		}
		DecimalFormat df1 = new DecimalFormat("#0.00%"); // ##.00%  
															// 百分比格式，后面不足2位的用0补齐  
		baifenbi = df1.format(fen);
		return baifenbi;
	}

	/**
	 * 计算百分比
	 * @param y
	 * @param z
	 * @param isWithPercent 返回的字符串是否带%号
	 * @return
	 */
	public static String caluate2PercentStr(Number y, Number z, boolean isWithPercent) {
		String baifenbi = "";// 接受百分比的值  
		if (isWithPercent) {
			return caluate2PercentStr(y, z);
		} else {
			double baiy = y.doubleValue() * 1.0;
			double baiz = z.doubleValue() * 1.0;
			double fen = 0.00;
			if (baiz != 0) {
				fen = (baiy / baiz);
			}
			DecimalFormat df1 = new DecimalFormat("#0.00"); // ##.00%  
			baifenbi = df1.format(fen);
			return baifenbi;
		}
	}

	/**
	 * 计算百分比
	 * @param y
	 * @param z
	 * @param isWithPercent 是否带%号
	 * @param percentOnly 只去数值不带%号
	 * @return
	 */
	public static String myPercent(Number y, Number z, boolean isWithPercent, boolean percentOnly) {
		String baifenbi = "";// 接受百分比的值  
		double baiy = y.doubleValue() * 1.0;
		double baiz = z.doubleValue() * 1.0;
		double fen = 0.00;
		if (baiz != 0) {
			fen = (baiy / baiz);
		}
		if (isWithPercent) {
			DecimalFormat df1 = new DecimalFormat("#0.00%"); // ##.00%  
			baifenbi = df1.format(fen);
			return baifenbi;
		} else {
			if (percentOnly) {
				DecimalFormat df1 = new DecimalFormat("#0.00"); // ##.00%  
				baifenbi = df1.format(fen * 100);
				return baifenbi;
			} else {
				DecimalFormat df1 = new DecimalFormat("#0.00"); // ##.00%  
				baifenbi = df1.format(fen);
				return baifenbi;
			}
		}
	}

	/**
	 * 保留两位小数，四舍五入的一个老土的方法
	 * @param d
	 * @return
	 */
	public static double formatDouble2(double d) {
		return (double) Math.round(d * 100) / 100;
	}

	/**
	 * 自定义格式化金额
	 * @param d
	 * @param scale
	 * @param mode
	 * @return
	 */
	public static double formatDouble(double d, int scale, RoundingMode mode) {
		BigDecimal bg = new BigDecimal(d).setScale(scale, mode);
		return bg.doubleValue();
	}

	/**
	 * 四舍五入
	 * The BigDecimal class provides operations for arithmetic, scale manipulation, rounding, comparison, hashing, and format conversion.
	 * @param d
	 * @return
	 */
	public static double formatDoubleUp(double d) {
		return formatDouble(d, 2, RoundingMode.HALF_UP);
	}

	/**
	 * 格式化输入金额为指定保留数
	 * @param d
	 * @param digits
	 * @param mode
	 * @return
	 */
	public static String formatDouble2Str(double d, int digits, RoundingMode mode) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(digits);
		nf.setRoundingMode(mode);
		return nf.format(d);
	}

	/**
	 * 四舍五入
	 * NumberFormat is the abstract base class for all number formats. 
	 * This class provides the interface for formatting and parsing numbers.
	 * @param d
	 * @return
	 */
	public static String formatDouble2StrUp(double d) {
		double d2 = formatDoubleUp(d);
		return simpleFormatDouble(d2);
	}

	/**
	 * 如果只是用于程序中的格式化数值然后输出，那么这个方法还是挺方便的。
	 * 应该是这样使用：System.out.println(String.format("%.2f", d));
	 * @param d
	 * @return
	 */
	public static String simpleFormatDouble(Number d) {
		return String.format("%.2f", d);
	}
}