package com.example.sxd.thanksgivinghall.bean;

import java.util.Date;
import java.util.List;

/**
 * 获取供应商信息
 * Created by sxd on 2018/2/2.
 */
public class SupplierListEntity {
    private String success;
    private String statusMessage;
    private List<Data> data;
    public class Data{
        private String id;          //通知通告id
        private String supName;		// 供应商名称
        private String regCapital;		// 注册资本
        private String legalPerson;		// 法人
        private String supNature;		// 企业性质
        private String supUrl;		// 网址
        private String supAddress;		// 地址
        private Date setTime;		// 成立时间
        private String zipCode;		// 邮编
        private String contact;		// 联系人
        private String supEmail;		// 电子邮箱
        private String supTel;		// 联系电话
        private String faxNum;		// 传真号码
        private String bankName;		// 开户银行
        private String bankNum;		// 银行卡号
        private String creditRating;		// 资信等级
        private String businessScope;		// 经营范围

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSupName() {
            return supName;
        }

        public void setSupName(String supName) {
            this.supName = supName;
        }

        public String getRegCapital() {
            return regCapital;
        }

        public void setRegCapital(String regCapital) {
            this.regCapital = regCapital;
        }

        public String getLegalPerson() {
            return legalPerson;
        }

        public void setLegalPerson(String legalPerson) {
            this.legalPerson = legalPerson;
        }

        public String getSupNature() {
            return supNature;
        }

        public void setSupNature(String supNature) {
            this.supNature = supNature;
        }

        public String getSupUrl() {
            return supUrl;
        }

        public void setSupUrl(String supUrl) {
            this.supUrl = supUrl;
        }

        public String getSupAddress() {
            return supAddress;
        }

        public void setSupAddress(String supAddress) {
            this.supAddress = supAddress;
        }

        public Date getSetTime() {
            return setTime;
        }

        public void setSetTime(Date setTime) {
            this.setTime = setTime;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getSupEmail() {
            return supEmail;
        }

        public void setSupEmail(String supEmail) {
            this.supEmail = supEmail;
        }

        public String getSupTel() {
            return supTel;
        }

        public void setSupTel(String supTel) {
            this.supTel = supTel;
        }

        public String getFaxNum() {
            return faxNum;
        }

        public void setFaxNum(String faxNum) {
            this.faxNum = faxNum;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankNum() {
            return bankNum;
        }

        public void setBankNum(String bankNum) {
            this.bankNum = bankNum;
        }

        public String getCreditRating() {
            return creditRating;
        }

        public void setCreditRating(String creditRating) {
            this.creditRating = creditRating;
        }

        public String getBusinessScope() {
            return businessScope;
        }

        public void setBusinessScope(String businessScope) {
            this.businessScope = businessScope;
        }
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SupplierListEntity{" +
                "success='" + success + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
