package com.idgcapital.ufund.common.util;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 
 * @author tiger
 * @version 0.1.0
 * @since 字符串工具类
 */
public class StringUtil {

	public static boolean isDecimal(String str) {
		if (str == null || "".equals(str))
			return false;
		Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
		return pattern.matcher(str).matches();
	}

	// 整型判断
	public static boolean isInteger(String str) {
		if (str == null)
			return false;
		Pattern pattern = Pattern.compile("[0-9]+");
		return pattern.matcher(str).matches();
	}

	public static String arrayToString(Object... args) {
		StringBuilder stringBuilder = new StringBuilder("");
		for (Object obj : args) {
			stringBuilder.append(",").append(obj);
		}
		return stringBuilder.substring(1);
	}

	/**
	 * 生成32位随机字符串
	 * 
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	/**
	 * 判断是否为空字符串或对象
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isBlankOrNull(Object obj) {
		if (obj != null && obj.toString().length() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 将使用分号分隔的字符串转换成SQL中in语句的条件
	 * 
	 * @param original
	 * @return
	 */
	public static String getSQLOfInFromCheckBox(String original) {

		original = original.replaceAll(";", "','");
		original = "('" + original + "')";

		return original;
	}

	/**
	 * 将使用分号分隔的字符串转换成SQL中in语句的条件
	 * 
	 * @param original
	 * @return
	 */
	public static String getSQLOfInIntFromCheckBox(String original) {

		original = original.replaceAll(";", ",");
		original = "(" + original + ")";

		return original;
	}

	public static String likeString(String likeStr) {
		return new StringBuffer("%").append(likeStr).append("%").toString();
	}

	public static String assertSelectALl(String sql) {
		return String.format("select * from ( %s ) as temp", sql);
	}

	public static String assertSelectCntALl(String sql) {
		return String.format("select count(*) as count from ( %s ) as temp", sql);
	}
	
    public static boolean sqlInjTest(String str) {
          if(str == null){return true;}
         
	      String inj_str = "':and:exec:insert:select:delete:update:count:*:%:=:chr:mid:master:truncate:char:declare:;:or:-:+";
	      String inj_stra[] = inj_str.split(":");
	      for (int i = 0; i < inj_stra.length; i++) {
	        if (str.indexOf(inj_stra[i])!=-1) {
	          return false;
	        }
	      }
	      return true;
	}

	//自动补全0
	public static String autoCompleteZero(String str,int length){
		int lengthGap = length - str.length();
		String zero = "";
		for(int i=0;i<lengthGap;i++){
			zero+="0";
		}
		return zero+str;
	}
	 
}
