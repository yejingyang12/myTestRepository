package com.sinopec.smcc.cpro.systemcode.constant;

/**
 * @Title CheckConstant.java
 * @Package com.sinopec.smcc.cpro.review.constant
 * @Description: TODO:审核常量表
 * @author zhouyu
 * @date 2018年5月28日下午6:07:42
 * @version V1.0
 */
public class CodeConstant {
  
    /*--------创建时间-------*/
    public final static String TABLE_SYSTEMCODE_CREATE_TIME = "createTime";
    
    /*---------操作状态--------*/
    /**创建**/
    public final static int CREATED_SATAUS = 1;
    /**审核**/
    public final static int APPROVAL_SATAUS = 2;
    
    /*---------审核状态--------*/
    /**待企业安全员管理审核**/
    public final static int HANDLE_COMPANY_APPROVAL = 1; 
    /**待总部安全管理员审核**/
    public final static int HANDLE_Headquarters_APPROVAL = 2;
    /**企业安全员管理员未通过**/
    public final static int HANDLE_COMPANY_NOT_PASS = 3;
    /**总部安全管理员未通过**/
    public final static int HANDLE_Headquarters_NOT_PASS = 4;
    /**归档**/
    public final static int HANDLE_ARCHIVING = 5;
    
    public final static String METHOD_EDIT = "edit";
    public final static String METHOD_ADD = "add";
    
    
}
