package com.yc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * �ַ���������
 * @author hp
 *
 */
public class StringUtil {
	/**
	 * �Զ����ɶ������
	 * @param mno
	 * @return
	 */
	public static String genOid(int mno){
		Date date =new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(date)+mno;
	}
	/**
	 * �ַ����ָ�
	 */

	public static String[] splitString(String str, String regex) {
		if(null==str||"".equals(str)){
			return null;
		}
		return str.split(regex);
	}
}
