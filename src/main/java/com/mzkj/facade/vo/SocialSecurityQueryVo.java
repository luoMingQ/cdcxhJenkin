package com.mzkj.facade.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mzkj.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明： 社保工单查询vo
 * 创建人：CDCXH
 * 创建时间：2019-05-13
 */
@ApiModel(value = "SocialSecurity查询对象", description = "SocialSecurity查询")
public class SocialSecurityQueryVo {
    private String socialSecurityId;
    private String companyName;
    private String customer;
    private String registeredArea;
    private String customerPhone;
    private String address;
    private String fees;
    private String saler;
    private String buyStartMonth;
    private String isCreditCard;
    private String openAccount;
    private String backAccount;
    private String buyType;
    private String personalInformation;
    private String isLegalPersonBuy;
    private String legalPersonCertificate;
    private String isClerkStopBuyInsurance;
    private String identityCardNumber;
    private String createUser;
    private String createDate;
    private String updateUser;
    private String updateDate;
    private String tenantId;
    private String dataPrivilegeCode;
    private String procInstId;
    private String businessId;
    private String customerId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value="第几页",name="pageNum",example="1")
    private Integer pageNum = 1;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value="每页数量",name="pageSize",example="10")
    private Integer pageSize = 10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }
    public String getSocialSecurityId() {
        return socialSecurityId;
    }

    public void setSocialSecurityId(String socialSecurityId) {
        this.socialSecurityId = socialSecurityId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getRegisteredArea() {
        return registeredArea;
    }

    public void setRegisteredArea(String registeredArea) {
        this.registeredArea = registeredArea;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getSaler() {
        return saler;
    }

    public void setSaler(String saler) {
        this.saler = saler;
    }

    public String getBuyStartMonth() {
        return buyStartMonth;
    }

    public void setBuyStartMonth(String buyStartMonth) {
        this.buyStartMonth = buyStartMonth;
    }

    public String getIsCreditCard() {
        return isCreditCard;
    }

    public void setIsCreditCard(String isCreditCard) {
        this.isCreditCard = isCreditCard;
    }

    public String getOpenAccount() {
        return openAccount;
    }

    public void setOpenAccount(String openAccount) {
        this.openAccount = openAccount;
    }

    public String getBackAccount() {
        return backAccount;
    }

    public void setBackAccount(String backAccount) {
        this.backAccount = backAccount;
    }

    public String getBuyType() {
        return buyType;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }

    public String getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(String personalInformation) {
        this.personalInformation = personalInformation;
    }

    public String getIsLegalPersonBuy() {
        return isLegalPersonBuy;
    }

    public void setIsLegalPersonBuy(String isLegalPersonBuy) {
        this.isLegalPersonBuy = isLegalPersonBuy;
    }

    public String getLegalPersonCertificate() {
        return legalPersonCertificate;
    }

    public void setLegalPersonCertificate(String legalPersonCertificate) {
        this.legalPersonCertificate = legalPersonCertificate;
    }

    public String getIsClerkStopBuyInsurance() {
        return isClerkStopBuyInsurance;
    }

    public void setIsClerkStopBuyInsurance(String isClerkStopBuyInsurance) {
        this.isClerkStopBuyInsurance = isClerkStopBuyInsurance;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getDataPrivilegeCode() {
        return dataPrivilegeCode;
    }

    public void setDataPrivilegeCode(String dataPrivilegeCode) {
        this.dataPrivilegeCode = dataPrivilegeCode;
    }


}

