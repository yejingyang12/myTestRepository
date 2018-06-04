package com.sinopec.smcc.cpro.systemcode.util;

import com.sinopec.smcc.cpro.systemcode.constant.CodeConstant;


/**  
 *项目名称： smcc-cpro
 *包名称：   com.sinopec.smcc.cpro.systemcode.util
 *类名称：   ConvertFieldUtil
 *创建人：   zhouyu
 *创建时间： 2018年4月19日 上午11:24:02
 *修改备注:
 */
public class ConvertFieldUtil {

	
	public static String sortField(String field) {
		if(CodeConstant.TABLE_SYSTEMCODE_CREATE_TIME.equalsIgnoreCase(field) ) {
			field = CodeConstant.TABLE_SYSTEMCODE_CREATE_TIME;
		}else {
			
		}
		return field;
	} 
	
	private static String fieldStr(String str) {
		String o = str.replace("_", "");
		return o;
	}
}
