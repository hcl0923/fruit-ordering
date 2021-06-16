package com.yc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串工具类
 * @author hp
 *
 */
public class StringUtil {
	/**
	 * 自动生成订单编号
	 * @param mno
	 * @return
	 */
	public static String genOid(int mno){
		Date date =new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(date)+mno;
	}
	/**
	 * 字符串分割
	 */

	public static String[] splitString(String str, String regex) {
		if(null==str||"".equals(str)){
			return null;
		}
		return str.split(regex);
	}
}
