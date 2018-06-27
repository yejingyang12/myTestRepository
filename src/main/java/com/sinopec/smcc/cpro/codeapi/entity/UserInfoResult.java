/**
 * @Title UserInfoResult.java
 * @Package com.sinopec.smcc.cpro.codeapi.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年6月25日下午11:14:09
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.entity;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * @Title UserInfoResult.java
 * @Package com.sinopec.smcc.cpro.codeapi.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年6月25日下午11:14:09
 * @version V1.0
 */
public class UserInfoResult {

  private String success;
  private String code;
  private String msg;
  private String detail;
  
  private UserInfo data;
  
  public class UserInfo{
    
    public class UserOrgs{
      private String address;
      private String appId;
      private String arrayParentCode;
      private String arrayParentId;
      private String attributes;
      private String contact;
      private String createTime;
      private String createUserAccount;
      private String createUserId;
      private String createUserName;
      private String delFlag;
      private String email;
      private String enterCode;
      private String enterName;
      private String fax;
      private String fullName;
      private String hasChild;
      private String isUsed;
      private String limit;
      private String oldParentId;
      private String orderNum;
      private String orgCode;
      private String orgId;
      private String orgIdAndOrgCode;
      private String orgLevelCode;
      private String orgLevelId;
      private String orgLevelName;
      private String orgName;
      private String orgTypeCode;
      private String orgTypeId;
      private String orgTypeName;
      private String orgUsers;
      private String parentCode;
      private String parentId;
      private String parentIdAndCode;
      private String parentOrgName;
      private String phone;
      private String positionCode;
      private String positionDesc;
      private String positionId;
      private String positionIdAndCode;
      private String remark;
      private String reserve1;
      private String reserve2;
      private String shortName;
      private String start;
      private String subOrg;
      private String updateTime;
      private String updateUserAccount;
      private String updateUserId;
      private String updateUserName;
      private String zipCode;
      public String getAddress() {
        return address;
      }
      public void setAddress(String address) {
        this.address = address;
      }
      public String getAppId() {
        return appId;
      }
      public void setAppId(String appId) {
        this.appId = appId;
      }
      public String getArrayParentCode() {
        return arrayParentCode;
      }
      public void setArrayParentCode(String arrayParentCode) {
        this.arrayParentCode = arrayParentCode;
      }
      public String getArrayParentId() {
        return arrayParentId;
      }
      public void setArrayParentId(String arrayParentId) {
        this.arrayParentId = arrayParentId;
      }
      public String getAttributes() {
        return attributes;
      }
      public void setAttributes(String attributes) {
        this.attributes = attributes;
      }
      public String getContact() {
        return contact;
      }
      public void setContact(String contact) {
        this.contact = contact;
      }
      public String getCreateTime() {
        return createTime;
      }
      public void setCreateTime(String createTime) {
        this.createTime = createTime;
      }
      public String getCreateUserAccount() {
        return createUserAccount;
      }
      public void setCreateUserAccount(String createUserAccount) {
        this.createUserAccount = createUserAccount;
      }
      public String getCreateUserId() {
        return createUserId;
      }
      public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
      }
      public String getCreateUserName() {
        return createUserName;
      }
      public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
      }
      public String getDelFlag() {
        return delFlag;
      }
      public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
      }
      public String getEmail() {
        return email;
      }
      public void setEmail(String email) {
        this.email = email;
      }
      public String getEnterCode() {
        return enterCode;
      }
      public void setEnterCode(String enterCode) {
        this.enterCode = enterCode;
      }
      public String getEnterName() {
        return enterName;
      }
      public void setEnterName(String enterName) {
        this.enterName = enterName;
      }
      public String getFax() {
        return fax;
      }
      public void setFax(String fax) {
        this.fax = fax;
      }
      public String getFullName() {
        return fullName;
      }
      public void setFullName(String fullName) {
        this.fullName = fullName;
      }
      public String getHasChild() {
        return hasChild;
      }
      public void setHasChild(String hasChild) {
        this.hasChild = hasChild;
      }
      public String getIsUsed() {
        return isUsed;
      }
      public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
      }
      public String getLimit() {
        return limit;
      }
      public void setLimit(String limit) {
        this.limit = limit;
      }
      public String getOldParentId() {
        return oldParentId;
      }
      public void setOldParentId(String oldParentId) {
        this.oldParentId = oldParentId;
      }
      public String getOrderNum() {
        return orderNum;
      }
      public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
      }
      public String getOrgCode() {
        return orgCode;
      }
      public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
      }
      public String getOrgId() {
        return orgId;
      }
      public void setOrgId(String orgId) {
        this.orgId = orgId;
      }
      public String getOrgIdAndOrgCode() {
        return orgIdAndOrgCode;
      }
      public void setOrgIdAndOrgCode(String orgIdAndOrgCode) {
        this.orgIdAndOrgCode = orgIdAndOrgCode;
      }
      public String getOrgLevelCode() {
        return orgLevelCode;
      }
      public void setOrgLevelCode(String orgLevelCode) {
        this.orgLevelCode = orgLevelCode;
      }
      public String getOrgLevelId() {
        return orgLevelId;
      }
      public void setOrgLevelId(String orgLevelId) {
        this.orgLevelId = orgLevelId;
      }
      public String getOrgLevelName() {
        return orgLevelName;
      }
      public void setOrgLevelName(String orgLevelName) {
        this.orgLevelName = orgLevelName;
      }
      public String getOrgName() {
        return orgName;
      }
      public void setOrgName(String orgName) {
        this.orgName = orgName;
      }
      public String getOrgTypeCode() {
        return orgTypeCode;
      }
      public void setOrgTypeCode(String orgTypeCode) {
        this.orgTypeCode = orgTypeCode;
      }
      public String getOrgTypeId() {
        return orgTypeId;
      }
      public void setOrgTypeId(String orgTypeId) {
        this.orgTypeId = orgTypeId;
      }
      public String getOrgTypeName() {
        return orgTypeName;
      }
      public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
      }
      public String getOrgUsers() {
        return orgUsers;
      }
      public void setOrgUsers(String orgUsers) {
        this.orgUsers = orgUsers;
      }
      public String getParentCode() {
        return parentCode;
      }
      public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
      }
      public String getParentId() {
        return parentId;
      }
      public void setParentId(String parentId) {
        this.parentId = parentId;
      }
      public String getParentIdAndCode() {
        return parentIdAndCode;
      }
      public void setParentIdAndCode(String parentIdAndCode) {
        this.parentIdAndCode = parentIdAndCode;
      }
      public String getParentOrgName() {
        return parentOrgName;
      }
      public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName;
      }
      public String getPhone() {
        return phone;
      }
      public void setPhone(String phone) {
        this.phone = phone;
      }
      public String getPositionCode() {
        return positionCode;
      }
      public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
      }
      public String getPositionDesc() {
        return positionDesc;
      }
      public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc;
      }
      public String getPositionId() {
        return positionId;
      }
      public void setPositionId(String positionId) {
        this.positionId = positionId;
      }
      public String getPositionIdAndCode() {
        return positionIdAndCode;
      }
      public void setPositionIdAndCode(String positionIdAndCode) {
        this.positionIdAndCode = positionIdAndCode;
      }
      public String getRemark() {
        return remark;
      }
      public void setRemark(String remark) {
        this.remark = remark;
      }
      public String getReserve1() {
        return reserve1;
      }
      public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
      }
      public String getReserve2() {
        return reserve2;
      }
      public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
      }
      public String getShortName() {
        return shortName;
      }
      public void setShortName(String shortName) {
        this.shortName = shortName;
      }
      public String getStart() {
        return start;
      }
      public void setStart(String start) {
        this.start = start;
      }
      public String getSubOrg() {
        return subOrg;
      }
      public void setSubOrg(String subOrg) {
        this.subOrg = subOrg;
      }
      public String getUpdateTime() {
        return updateTime;
      }
      public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
      }
      public String getUpdateUserAccount() {
        return updateUserAccount;
      }
      public void setUpdateUserAccount(String updateUserAccount) {
        this.updateUserAccount = updateUserAccount;
      }
      public String getUpdateUserId() {
        return updateUserId;
      }
      public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
      }
      public String getUpdateUserName() {
        return updateUserName;
      }
      public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
      }
      public String getZipCode() {
        return zipCode;
      }
      public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
      }

      public String toString(){
        return JSON.toJSONString(this);
      }
    }
    
    private String account;
    private String accountList;
    private String accountType;
    private String accounts;
    private String appId;
    private String appVersion;
    private String attributes;
    private String availableMark;
    private String birthday;
    private String certificateNo;
    private String createTime;
    private String createUserAccount;
    private String createUserId;
    private String createUserName;
    private String delFlag;
    private String deviceToken;
    private String email;
    private String enterCode;
    private String enterName;
    private String equipOs;
    private String equipVersion;
    private String errorNum;
    private String extensionNum;
    private String idCardNumber;
    private String isComplete;
    private String isInternalStaff;
    private String limit;
    private String lockTime;
    private String locking;
    private String loginEquip;
    private String loginIp;
    private String loginTime;
    private String loginTimes;
    private String orderNum;
    private String orgCode;
    private String orgId;
    private String orgName;
    private String orgPath;
    private String orgtypeid;
    private String parentOrgCode;
    private String parentOrgName;
    private String phone;
    private String positionCode;
    private String positionId;
    private String positionName;
    private String pwd;
    private String regIp;
    private String regTime;
    private String remark;
    private String repwd;
    private String reserve1;
    private String reserve10;
    private String reserve2;
    private String reserve3;
    private String reserve4;
    private String reserve5;
    private String reserve6;
    private String reserve7;
    private String reserve8;
    private String reserve9;
    private String roleId;
    private String sex;
    private String sourceAppId;
    private String sourceUserId;
    private String start;
    private String telephone;
    private String updateTime;
    private String updateUserAccount;
    private String updateUserId;
    private String updateUserName;
    private String userId;
    private String userLevels;
    private String userName;
    private List<UserOrgs> userOrgs; 
    
    public String getAccount() {
      return account;
    }

    public void setAccount(String account) {
      this.account = account;
    }

    public String getAccountList() {
      return accountList;
    }

    public void setAccountList(String accountList) {
      this.accountList = accountList;
    }

    public String getAccountType() {
      return accountType;
    }

    public void setAccountType(String accountType) {
      this.accountType = accountType;
    }

    public String getAccounts() {
      return accounts;
    }

    public void setAccounts(String accounts) {
      this.accounts = accounts;
    }

    public String getAppId() {
      return appId;
    }

    public void setAppId(String appId) {
      this.appId = appId;
    }

    public String getAppVersion() {
      return appVersion;
    }

    public void setAppVersion(String appVersion) {
      this.appVersion = appVersion;
    }

    public String getAttributes() {
      return attributes;
    }

    public void setAttributes(String attributes) {
      this.attributes = attributes;
    }

    public String getAvailableMark() {
      return availableMark;
    }

    public void setAvailableMark(String availableMark) {
      this.availableMark = availableMark;
    }

    public String getBirthday() {
      return birthday;
    }

    public void setBirthday(String birthday) {
      this.birthday = birthday;
    }

    public String getCertificateNo() {
      return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
      this.certificateNo = certificateNo;
    }

    public String getCreateTime() {
      return createTime;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }

    public String getCreateUserAccount() {
      return createUserAccount;
    }

    public void setCreateUserAccount(String createUserAccount) {
      this.createUserAccount = createUserAccount;
    }

    public String getCreateUserId() {
      return createUserId;
    }

    public void setCreateUserId(String createUserId) {
      this.createUserId = createUserId;
    }

    public String getCreateUserName() {
      return createUserName;
    }

    public void setCreateUserName(String createUserName) {
      this.createUserName = createUserName;
    }

    public String getDelFlag() {
      return delFlag;
    }

    public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
    }

    public String getDeviceToken() {
      return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
      this.deviceToken = deviceToken;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getEnterCode() {
      return enterCode;
    }

    public void setEnterCode(String enterCode) {
      this.enterCode = enterCode;
    }

    public String getEnterName() {
      return enterName;
    }

    public void setEnterName(String enterName) {
      this.enterName = enterName;
    }

    public String getEquipOs() {
      return equipOs;
    }

    public void setEquipOs(String equipOs) {
      this.equipOs = equipOs;
    }

    public String getEquipVersion() {
      return equipVersion;
    }

    public void setEquipVersion(String equipVersion) {
      this.equipVersion = equipVersion;
    }

    public String getErrorNum() {
      return errorNum;
    }

    public void setErrorNum(String errorNum) {
      this.errorNum = errorNum;
    }

    public String getExtensionNum() {
      return extensionNum;
    }

    public void setExtensionNum(String extensionNum) {
      this.extensionNum = extensionNum;
    }

    public String getIdCardNumber() {
      return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
      this.idCardNumber = idCardNumber;
    }

    public String getIsComplete() {
      return isComplete;
    }

    public void setIsComplete(String isComplete) {
      this.isComplete = isComplete;
    }

    public String getIsInternalStaff() {
      return isInternalStaff;
    }

    public void setIsInternalStaff(String isInternalStaff) {
      this.isInternalStaff = isInternalStaff;
    }

    public String getLimit() {
      return limit;
    }

    public void setLimit(String limit) {
      this.limit = limit;
    }

    public String getLockTime() {
      return lockTime;
    }

    public void setLockTime(String lockTime) {
      this.lockTime = lockTime;
    }

    public String getLocking() {
      return locking;
    }

    public void setLocking(String locking) {
      this.locking = locking;
    }

    public String getLoginEquip() {
      return loginEquip;
    }

    public void setLoginEquip(String loginEquip) {
      this.loginEquip = loginEquip;
    }

    public String getLoginIp() {
      return loginIp;
    }

    public void setLoginIp(String loginIp) {
      this.loginIp = loginIp;
    }

    public String getLoginTime() {
      return loginTime;
    }

    public void setLoginTime(String loginTime) {
      this.loginTime = loginTime;
    }

    public String getLoginTimes() {
      return loginTimes;
    }

    public void setLoginTimes(String loginTimes) {
      this.loginTimes = loginTimes;
    }

    public String getOrderNum() {
      return orderNum;
    }

    public void setOrderNum(String orderNum) {
      this.orderNum = orderNum;
    }

    public String getOrgCode() {
      return orgCode;
    }

    public void setOrgCode(String orgCode) {
      this.orgCode = orgCode;
    }

    public String getOrgId() {
      return orgId;
    }

    public void setOrgId(String orgId) {
      this.orgId = orgId;
    }

    public String getOrgName() {
      return orgName;
    }

    public void setOrgName(String orgName) {
      this.orgName = orgName;
    }

    public String getOrgPath() {
      return orgPath;
    }

    public void setOrgPath(String orgPath) {
      this.orgPath = orgPath;
    }

    public String getOrgtypeid() {
      return orgtypeid;
    }

    public void setOrgtypeid(String orgtypeid) {
      this.orgtypeid = orgtypeid;
    }

    public String getParentOrgCode() {
      return parentOrgCode;
    }

    public void setParentOrgCode(String parentOrgCode) {
      this.parentOrgCode = parentOrgCode;
    }

    public String getParentOrgName() {
      return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
      this.parentOrgName = parentOrgName;
    }

    public String getPhone() {
      return phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    public String getPositionCode() {
      return positionCode;
    }

    public void setPositionCode(String positionCode) {
      this.positionCode = positionCode;
    }

    public String getPositionId() {
      return positionId;
    }

    public void setPositionId(String positionId) {
      this.positionId = positionId;
    }

    public String getPositionName() {
      return positionName;
    }

    public void setPositionName(String positionName) {
      this.positionName = positionName;
    }

    public String getPwd() {
      return pwd;
    }

    public void setPwd(String pwd) {
      this.pwd = pwd;
    }

    public String getRegIp() {
      return regIp;
    }

    public void setRegIp(String regIp) {
      this.regIp = regIp;
    }

    public String getRegTime() {
      return regTime;
    }

    public void setRegTime(String regTime) {
      this.regTime = regTime;
    }

    public String getRemark() {
      return remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getRepwd() {
      return repwd;
    }

    public void setRepwd(String repwd) {
      this.repwd = repwd;
    }

    public String getReserve1() {
      return reserve1;
    }

    public void setReserve1(String reserve1) {
      this.reserve1 = reserve1;
    }

    public String getReserve10() {
      return reserve10;
    }

    public void setReserve10(String reserve10) {
      this.reserve10 = reserve10;
    }

    public String getReserve2() {
      return reserve2;
    }

    public void setReserve2(String reserve2) {
      this.reserve2 = reserve2;
    }

    public String getReserve3() {
      return reserve3;
    }

    public void setReserve3(String reserve3) {
      this.reserve3 = reserve3;
    }

    public String getReserve4() {
      return reserve4;
    }

    public void setReserve4(String reserve4) {
      this.reserve4 = reserve4;
    }

    public String getReserve5() {
      return reserve5;
    }

    public void setReserve5(String reserve5) {
      this.reserve5 = reserve5;
    }

    public String getReserve6() {
      return reserve6;
    }

    public void setReserve6(String reserve6) {
      this.reserve6 = reserve6;
    }

    public String getReserve7() {
      return reserve7;
    }

    public void setReserve7(String reserve7) {
      this.reserve7 = reserve7;
    }

    public String getReserve8() {
      return reserve8;
    }

    public void setReserve8(String reserve8) {
      this.reserve8 = reserve8;
    }

    public String getReserve9() {
      return reserve9;
    }

    public void setReserve9(String reserve9) {
      this.reserve9 = reserve9;
    }

    public String getRoleId() {
      return roleId;
    }

    public void setRoleId(String roleId) {
      this.roleId = roleId;
    }

    public String getSex() {
      return sex;
    }

    public void setSex(String sex) {
      this.sex = sex;
    }

    public String getSourceAppId() {
      return sourceAppId;
    }

    public void setSourceAppId(String sourceAppId) {
      this.sourceAppId = sourceAppId;
    }

    public String getSourceUserId() {
      return sourceUserId;
    }

    public void setSourceUserId(String sourceUserId) {
      this.sourceUserId = sourceUserId;
    }

    public String getStart() {
      return start;
    }

    public void setStart(String start) {
      this.start = start;
    }

    public String getTelephone() {
      return telephone;
    }

    public void setTelephone(String telephone) {
      this.telephone = telephone;
    }

    public String getUpdateTime() {
      return updateTime;
    }

    public void setUpdateTime(String updateTime) {
      this.updateTime = updateTime;
    }

    public String getUpdateUserAccount() {
      return updateUserAccount;
    }

    public void setUpdateUserAccount(String updateUserAccount) {
      this.updateUserAccount = updateUserAccount;
    }

    public String getUpdateUserId() {
      return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
      this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
      return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
      this.updateUserName = updateUserName;
    }

    public String getUserId() {
      return userId;
    }

    public void setUserId(String userId) {
      this.userId = userId;
    }

    public String getUserLevels() {
      return userLevels;
    }

    public void setUserLevels(String userLevels) {
      this.userLevels = userLevels;
    }

    public String getUserName() {
      return userName;
    }

    public void setUserName(String userName) {
      this.userName = userName;
    }

    public List<UserOrgs> getUserOrgs() {
      return userOrgs;
    }

    public void setUserOrgs(List<UserOrgs> userOrgs) {
      this.userOrgs = userOrgs;
    }

    public String toString(){
      return JSON.toJSONString(this);
    }
  }

  public String getSuccess() {
    return success;
  }

  public void setSuccess(String success) {
    this.success = success;
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

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public UserInfo getData() {
    return data;
  }

  public void setData(UserInfo data) {
    this.data = data;
  }
  
  public String toString(){
    return JSON.toJSONString(this);
  }
}
