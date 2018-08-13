package com.sinopec.smcc.cpro.api.entity;
/**
* @Title UsmgParams.java
* @Package com.sinopec.smcc.cpro.api.entity
* @Description: TODO:
* @author dongxu
* @date 2018年8月10日上午11:31:04
* @version V1.0
 */
public class UsmgParams {

	  //每页记录数
    private int pageSize=10;
    //当前页数
    private int currPage=1;

  public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
    
}
