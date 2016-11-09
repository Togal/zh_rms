package com.bms.helper;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * Title:PageHelper
 * Description:分页帮助类
 * @author    zwb
 * @date      2016年7月27日 下午3:10:07
 *
 */
public class PageHelper {
	/**
	 * 获取分页url
	 * @param request
	 * @return
	 */
	public static String pageUrl(HttpServletRequest request){
		//分页url
		String pageUrl = request.getRequestURI();
		if(request.getQueryString() != null){
			pageUrl += "?" + request.getQueryString();
		}
		pageUrl = getPageUrl(pageUrl, "pageNum", 1, "&", "&", "?");
		if(pageUrl.indexOf("?") != -1){//有参数
			pageUrl += "&pageNum=";
		}else{//无参数
			pageUrl += "?pageNum=";
		}
		return pageUrl;
	}
	
	/**
	 * url处理（针对非也带参数查询）
	 * @param str 所要操作的字符串
	 * @param removeStr 移除指定字符串
	 * @param prefixNum 删除移除字符串前几位
	 * @param suffixStr 删除移除字符串到达指定位置
	 * （移除指定字符串后，替换字符串方式只要换第一个出现的字符串）
	 * @param oldStr 移除指定字符串后，需要替换的旧的字符串
	 * @param newStr 移除指定字符串后，需要换成新的字符串
	 * @return 返回是去除指定字符串的字符串
	 */
	public static String getPageUrl(String str, String removeStr, int prefixNum, String suffixStr, String oldStr, String newStr){
		String url = remove(str, removeStr, prefixNum, suffixStr);
		if(oldStr == null || newStr == null)
			return url;
		if(url.indexOf(newStr) != -1)
			return url;
		return url.replaceFirst(oldStr, newStr);
	}
	private static String remove(String str, String removeStr, int prefixNum, String suffixStr){
		int j = str.indexOf(removeStr);
		if(j == -1)
			return str;
		String tempStr = str.substring(j + removeStr.length(), str.length());
		int t = tempStr.indexOf(suffixStr);
		if(t == -1){
			tempStr = "";
		}else{
			tempStr = tempStr.substring(tempStr.indexOf(suffixStr), tempStr.length());
		}
		return str.substring(0, j - prefixNum) + remove(tempStr, removeStr, prefixNum, suffixStr);
	}
}
