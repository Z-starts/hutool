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

import java.util.UUID;

import com.xiaoleilu.hutool.util.StrUtil;


/**
 * @author zxx
 * 2015年7月22日
 */
public class ZStrUtil extends StrUtil {

	/**
	 * 生成UUID，去掉减号字符
	 * @return 
	 */
	public static String getUUID(boolean withMinus) {
		if (withMinus)
			return UUID.randomUUID().toString();
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}

	/**
	 * 生成UUID，去掉减号字符
	 * @return 
	 */
	public static String getUUID() {
		return getUUID(false);
	}
}
