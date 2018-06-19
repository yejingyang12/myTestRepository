package com.sinopec.smcc.cpro.codeapi.entity;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 
 * 项目名称： security-service
 * 包名称：   com.sinopec.smcc.usmg.entity
 * 类名称：   NoticeController
 * 类描述：   信息系统实体类
 * 创建人：   lixiang
 * 创建时间： 2018年5月29日 下午6:07:53
 * 修改人： 
 * 修改时间:
 * 修改备注:
 */
public class SystemInfoList {
  String code;
  String msg;
  List<SystemInfo> data;
  public class SystemInfo {
    private String id;
    private String systemcode;
    private String systemallname;
    private String systemname;
    private String isbaSysalias;
    private String isbaRangecode;
    private String isbaRangename;
    private String isbaSysbranch;
    private String isbaGroup;
    private String isbaSoftproduct;
    private String isbaUrl;
    private String isbaRank;
    private String bcdCode;
    private String bcdName;
    private String bcdDpcode;
    private String bcdDpname;
    private String bcdDptel;
    private String bcdDpmail;
    private String bcdCpcode;
    private String bcdCpname;
    private String bcdCptel;
    private String bcdCpmail;
    private String iprCode;
    private String iprName;
    private String iprDpcode;
    private String iprDpname;
    private String iprDptel;
    private String iprDpmail;
    private String iprCpcode;
    private String iprCpname;
    private String iprCptel;
    private String iprCpmail;
    private String cuCode;
    private String cuName;
    private String cuPmcode;
    private String cuPmname;
    private String cuPmtel;
    private String cuPmmail;
    private String cuCpcode;
    private String cuCpname;
    private String cuCptel;
    private String cuCpmail;
    private String cusCode;
    private String cusName;
    private String cusPmcode;
    private String cusPmname;
    private String cusPmtel;
    private String cusPmmail;
    private String cusCpcode;
    private String cusCpname;
    private String cusCptel;
    private String cusCpmail;
    private String apCode;
    private String apName;
    private String apMmcode;
    private String apMmname;
    private String apMmtel;
    private String apMmmail;
    private String apCpcode;
    private String apCpname;
    private String apCptel;
    private String apCpmail;
    private String apsCode;
    private String apsName;
    private String apsMmcode;
    private String apsMmname;
    private String apsMmtel;
    private String apsMmmail;
    private String apsCpcode;
    private String apsCpname;
    private String apsCptel;
    private String apsCpmail;
    private String iuCode;
    private String iuName;
    private String iuMmcode;
    private String iuMmname;
    private String iuMmtel;
    private String iuMmmail;
    private String iuCpcode;
    private String iuCpname;
    private String iuCptel;
    private String iuCpmail;
    private String bisUnitnamecollect;
    private String bisUsecount;
    private String updatetime;
    private String createtime;
    private String isdelete;
    private String isinternet;
    private String systemintro;
    private String acceptlevel;
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    public String getSystemcode() {
      return systemcode;
    }
    public void setSystemcode(String systemcode) {
      this.systemcode = systemcode;
    }
    public String getSystemallname() {
      return systemallname;
    }
    public void setSystemallname(String systemallname) {
      this.systemallname = systemallname;
    }
    public String getSystemname() {
      return systemname;
    }
    public void setSystemname(String systemname) {
      this.systemname = systemname;
    }
    public String getIsbaSysalias() {
      return isbaSysalias;
    }
    public void setIsbaSysalias(String isbaSysalias) {
      this.isbaSysalias = isbaSysalias;
    }
    public String getIsbaRangecode() {
      return isbaRangecode;
    }
    public void setIsbaRangecode(String isbaRangecode) {
      this.isbaRangecode = isbaRangecode;
    }
    public String getIsbaRangename() {
      return isbaRangename;
    }
    public void setIsbaRangename(String isbaRangename) {
      this.isbaRangename = isbaRangename;
    }
    public String getIsbaSysbranch() {
      return isbaSysbranch;
    }
    public void setIsbaSysbranch(String isbaSysbranch) {
      this.isbaSysbranch = isbaSysbranch;
    }
    public String getIsbaGroup() {
      return isbaGroup;
    }
    public void setIsbaGroup(String isbaGroup) {
      this.isbaGroup = isbaGroup;
    }
    public String getIsbaSoftproduct() {
      return isbaSoftproduct;
    }
    public void setIsbaSoftproduct(String isbaSoftproduct) {
      this.isbaSoftproduct = isbaSoftproduct;
    }
    public String getIsbaUrl() {
      return isbaUrl;
    }
    public void setIsbaUrl(String isbaUrl) {
      this.isbaUrl = isbaUrl;
    }
    public String getIsbaRank() {
      return isbaRank;
    }
    public void setIsbaRank(String isbaRank) {
      this.isbaRank = isbaRank;
    }
    public String getBcdCode() {
      return bcdCode;
    }
    public void setBcdCode(String bcdCode) {
      this.bcdCode = bcdCode;
    }
    public String getBcdName() {
      return bcdName;
    }
    public void setBcdName(String bcdName) {
      this.bcdName = bcdName;
    }
    public String getBcdDpcode() {
      return bcdDpcode;
    }
    public void setBcdDpcode(String bcdDpcode) {
      this.bcdDpcode = bcdDpcode;
    }
    public String getBcdDpname() {
      return bcdDpname;
    }
    public void setBcdDpname(String bcdDpname) {
      this.bcdDpname = bcdDpname;
    }
    public String getBcdDptel() {
      return bcdDptel;
    }
    public void setBcdDptel(String bcdDptel) {
      this.bcdDptel = bcdDptel;
    }
    public String getBcdDpmail() {
      return bcdDpmail;
    }
    public void setBcdDpmail(String bcdDpmail) {
      this.bcdDpmail = bcdDpmail;
    }
    public String getBcdCpcode() {
      return bcdCpcode;
    }
    public void setBcdCpcode(String bcdCpcode) {
      this.bcdCpcode = bcdCpcode;
    }
    public String getBcdCpname() {
      return bcdCpname;
    }
    public void setBcdCpname(String bcdCpname) {
      this.bcdCpname = bcdCpname;
    }
    public String getBcdCptel() {
      return bcdCptel;
    }
    public void setBcdCptel(String bcdCptel) {
      this.bcdCptel = bcdCptel;
    }
    public String getBcdCpmail() {
      return bcdCpmail;
    }
    public void setBcdCpmail(String bcdCpmail) {
      this.bcdCpmail = bcdCpmail;
    }
    public String getIprCode() {
      return iprCode;
    }
    public void setIprCode(String iprCode) {
      this.iprCode = iprCode;
    }
    public String getIprName() {
      return iprName;
    }
    public void setIprName(String iprName) {
      this.iprName = iprName;
    }
    public String getIprDpcode() {
      return iprDpcode;
    }
    public void setIprDpcode(String iprDpcode) {
      this.iprDpcode = iprDpcode;
    }
    public String getIprDpname() {
      return iprDpname;
    }
    public void setIprDpname(String iprDpname) {
      this.iprDpname = iprDpname;
    }
    public String getIprDptel() {
      return iprDptel;
    }
    public void setIprDptel(String iprDptel) {
      this.iprDptel = iprDptel;
    }
    public String getIprDpmail() {
      return iprDpmail;
    }
    public void setIprDpmail(String iprDpmail) {
      this.iprDpmail = iprDpmail;
    }
    public String getIprCpcode() {
      return iprCpcode;
    }
    public void setIprCpcode(String iprCpcode) {
      this.iprCpcode = iprCpcode;
    }
    public String getIprCpname() {
      return iprCpname;
    }
    public void setIprCpname(String iprCpname) {
      this.iprCpname = iprCpname;
    }
    public String getIprCptel() {
      return iprCptel;
    }
    public void setIprCptel(String iprCptel) {
      this.iprCptel = iprCptel;
    }
    public String getIprCpmail() {
      return iprCpmail;
    }
    public void setIprCpmail(String iprCpmail) {
      this.iprCpmail = iprCpmail;
    }
    public String getCuCode() {
      return cuCode;
    }
    public void setCuCode(String cuCode) {
      this.cuCode = cuCode;
    }
    public String getCuName() {
      return cuName;
    }
    public void setCuName(String cuName) {
      this.cuName = cuName;
    }
    public String getCuPmcode() {
      return cuPmcode;
    }
    public void setCuPmcode(String cuPmcode) {
      this.cuPmcode = cuPmcode;
    }
    public String getCuPmname() {
      return cuPmname;
    }
    public void setCuPmname(String cuPmname) {
      this.cuPmname = cuPmname;
    }
    public String getCuPmtel() {
      return cuPmtel;
    }
    public void setCuPmtel(String cuPmtel) {
      this.cuPmtel = cuPmtel;
    }
    public String getCuPmmail() {
      return cuPmmail;
    }
    public void setCuPmmail(String cuPmmail) {
      this.cuPmmail = cuPmmail;
    }
    public String getCuCpcode() {
      return cuCpcode;
    }
    public void setCuCpcode(String cuCpcode) {
      this.cuCpcode = cuCpcode;
    }
    public String getCuCpname() {
      return cuCpname;
    }
    public void setCuCpname(String cuCpname) {
      this.cuCpname = cuCpname;
    }
    public String getCuCptel() {
      return cuCptel;
    }
    public void setCuCptel(String cuCptel) {
      this.cuCptel = cuCptel;
    }
    public String getCuCpmail() {
      return cuCpmail;
    }
    public void setCuCpmail(String cuCpmail) {
      this.cuCpmail = cuCpmail;
    }
    public String getCusCode() {
      return cusCode;
    }
    public void setCusCode(String cusCode) {
      this.cusCode = cusCode;
    }
    public String getCusName() {
      return cusName;
    }
    public void setCusName(String cusName) {
      this.cusName = cusName;
    }
    public String getCusPmcode() {
      return cusPmcode;
    }
    public void setCusPmcode(String cusPmcode) {
      this.cusPmcode = cusPmcode;
    }
    public String getCusPmname() {
      return cusPmname;
    }
    public void setCusPmname(String cusPmname) {
      this.cusPmname = cusPmname;
    }
    public String getCusPmtel() {
      return cusPmtel;
    }
    public void setCusPmtel(String cusPmtel) {
      this.cusPmtel = cusPmtel;
    }
    public String getCusPmmail() {
      return cusPmmail;
    }
    public void setCusPmmail(String cusPmmail) {
      this.cusPmmail = cusPmmail;
    }
    public String getCusCpcode() {
      return cusCpcode;
    }
    public void setCusCpcode(String cusCpcode) {
      this.cusCpcode = cusCpcode;
    }
    public String getCusCpname() {
      return cusCpname;
    }
    public void setCusCpname(String cusCpname) {
      this.cusCpname = cusCpname;
    }
    public String getCusCptel() {
      return cusCptel;
    }
    public void setCusCptel(String cusCptel) {
      this.cusCptel = cusCptel;
    }
    public String getCusCpmail() {
      return cusCpmail;
    }
    public void setCusCpmail(String cusCpmail) {
      this.cusCpmail = cusCpmail;
    }
    public String getApCode() {
      return apCode;
    }
    public void setApCode(String apCode) {
      this.apCode = apCode;
    }
    public String getApName() {
      return apName;
    }
    public void setApName(String apName) {
      this.apName = apName;
    }
    public String getApMmcode() {
      return apMmcode;
    }
    public void setApMmcode(String apMmcode) {
      this.apMmcode = apMmcode;
    }
    public String getApMmname() {
      return apMmname;
    }
    public void setApMmname(String apMmname) {
      this.apMmname = apMmname;
    }
    public String getApMmtel() {
      return apMmtel;
    }
    public void setApMmtel(String apMmtel) {
      this.apMmtel = apMmtel;
    }
    public String getApMmmail() {
      return apMmmail;
    }
    public void setApMmmail(String apMmmail) {
      this.apMmmail = apMmmail;
    }
    public String getApCpcode() {
      return apCpcode;
    }
    public void setApCpcode(String apCpcode) {
      this.apCpcode = apCpcode;
    }
    public String getApCpname() {
      return apCpname;
    }
    public void setApCpname(String apCpname) {
      this.apCpname = apCpname;
    }
    public String getApCptel() {
      return apCptel;
    }
    public void setApCptel(String apCptel) {
      this.apCptel = apCptel;
    }
    public String getApCpmail() {
      return apCpmail;
    }
    public void setApCpmail(String apCpmail) {
      this.apCpmail = apCpmail;
    }
    public String getApsCode() {
      return apsCode;
    }
    public void setApsCode(String apsCode) {
      this.apsCode = apsCode;
    }
    public String getApsName() {
      return apsName;
    }
    public void setApsName(String apsName) {
      this.apsName = apsName;
    }
    public String getApsMmcode() {
      return apsMmcode;
    }
    public void setApsMmcode(String apsMmcode) {
      this.apsMmcode = apsMmcode;
    }
    public String getApsMmname() {
      return apsMmname;
    }
    public void setApsMmname(String apsMmname) {
      this.apsMmname = apsMmname;
    }
    public String getApsMmtel() {
      return apsMmtel;
    }
    public void setApsMmtel(String apsMmtel) {
      this.apsMmtel = apsMmtel;
    }
    public String getApsMmmail() {
      return apsMmmail;
    }
    public void setApsMmmail(String apsMmmail) {
      this.apsMmmail = apsMmmail;
    }
    public String getApsCpcode() {
      return apsCpcode;
    }
    public void setApsCpcode(String apsCpcode) {
      this.apsCpcode = apsCpcode;
    }
    public String getApsCpname() {
      return apsCpname;
    }
    public void setApsCpname(String apsCpname) {
      this.apsCpname = apsCpname;
    }
    public String getApsCptel() {
      return apsCptel;
    }
    public void setApsCptel(String apsCptel) {
      this.apsCptel = apsCptel;
    }
    public String getApsCpmail() {
      return apsCpmail;
    }
    public void setApsCpmail(String apsCpmail) {
      this.apsCpmail = apsCpmail;
    }
    public String getIuCode() {
      return iuCode;
    }
    public void setIuCode(String iuCode) {
      this.iuCode = iuCode;
    }
    public String getIuName() {
      return iuName;
    }
    public void setIuName(String iuName) {
      this.iuName = iuName;
    }
    public String getIuMmcode() {
      return iuMmcode;
    }
    public void setIuMmcode(String iuMmcode) {
      this.iuMmcode = iuMmcode;
    }
    public String getIuMmname() {
      return iuMmname;
    }
    public void setIuMmname(String iuMmname) {
      this.iuMmname = iuMmname;
    }
    public String getIuMmtel() {
      return iuMmtel;
    }
    public void setIuMmtel(String iuMmtel) {
      this.iuMmtel = iuMmtel;
    }
    public String getIuMmmail() {
      return iuMmmail;
    }
    public void setIuMmmail(String iuMmmail) {
      this.iuMmmail = iuMmmail;
    }
    public String getIuCpcode() {
      return iuCpcode;
    }
    public void setIuCpcode(String iuCpcode) {
      this.iuCpcode = iuCpcode;
    }
    public String getIuCpname() {
      return iuCpname;
    }
    public void setIuCpname(String iuCpname) {
      this.iuCpname = iuCpname;
    }
    public String getIuCptel() {
      return iuCptel;
    }
    public void setIuCptel(String iuCptel) {
      this.iuCptel = iuCptel;
    }
    public String getIuCpmail() {
      return iuCpmail;
    }
    public void setIuCpmail(String iuCpmail) {
      this.iuCpmail = iuCpmail;
    }
    public String getBisUnitnamecollect() {
      return bisUnitnamecollect;
    }
    public void setBisUnitnamecollect(String bisUnitnamecollect) {
      this.bisUnitnamecollect = bisUnitnamecollect;
    }
    public String getBisUsecount() {
      return bisUsecount;
    }
    public void setBisUsecount(String bisUsecount) {
      this.bisUsecount = bisUsecount;
    }
    public String getUpdatetime() {
      return updatetime;
    }
    public void setUpdatetime(String updatetime) {
      this.updatetime = updatetime;
    }
    public String getCreatetime() {
      return createtime;
    }
    public void setCreatetime(String createtime) {
      this.createtime = createtime;
    }
    public String getIsdelete() {
      return isdelete;
    }
    public void setIsdelete(String isdelete) {
      this.isdelete = isdelete;
    }
    public String getIsinternet() {
      return isinternet;
    }
    public void setIsinternet(String isinternet) {
      this.isinternet = isinternet;
    }
    public String getSystemintro() {
      return systemintro;
    }
    public void setSystemintro(String systemintro) {
      this.systemintro = systemintro;
    }
    public String getAcceptlevel() {
      return acceptlevel;
    }
    public void setAcceptlevel(String acceptlevel) {
      this.acceptlevel = acceptlevel;
    }

    public String toString(){
      return JSON.toJSONString(this);
    }
  }
  
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getMsg() {
    return msg;
  }
  public void setMsg(String msg) {
    this.msg = msg;
  }
  public List<SystemInfo> getData() {
    return data;
  }
  public void setData(List<SystemInfo> data) {
    this.data = data;
  }
  public String toString(){
    return JSON.toJSONString(this);
  } 
}