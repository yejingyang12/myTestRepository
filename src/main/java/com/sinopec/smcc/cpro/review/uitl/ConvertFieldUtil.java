package com.sinopec.smcc.cpro.review.uitl;

import com.sinopec.smcc.cpro.review.constant.CheckConstant;

/**  
 *项目名称： smcc-secb
 *包名称：   com.sinopec.smcc.secb.notice.util
 *类名称：   ConvertFieldUtil
 *类描述：
 *创建人：   liyafeng
 *创建时间： 2018年4月19日 上午11:24:02
 *修改人：
 *修改时间:
 *修改备注:
 */
public class ConvertFieldUtil {

	
	public static String sortField(String field) {
		if(CheckConstant.TABLE_CHECK_CREATE_TIME.equalsIgnoreCase(field) ) {
			field = CheckConstant.TABLE_CHECK_CREATE_TIME;
		}else {
			
		}
		return field;
	} 
}
