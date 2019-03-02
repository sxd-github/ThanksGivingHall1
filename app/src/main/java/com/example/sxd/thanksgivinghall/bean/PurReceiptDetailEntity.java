package com.example.sxd.thanksgivinghall.bean;

import java.util.Date;

/**
 * 获取某一条通知通告详情
 * Created by sxd on 2018/2/2.
 */
public class PurReceiptDetailEntity {
    private String success;
    private String statusMessage;
    private Data data;
    public class Data {
        private String supplier;		// 供应商
        private String goodsName;		// 货物名称
        private String goodsType;		// 进货类型
        private String unitPrice;		// 货物单价（元）
        private String goodsNum;		// 进货数量（个/斤）
        private String totalPrice;		// 总价
        private String date;		// 进货时间
        private String paySum;		// 总价之和
        private String purchasePerson;		// 采购人
        private String payMethod;		// 支付方式


        public String getSupplier() {
            return supplier;
        }

        public void setSupplier(String supplier) {
            this.supplier = supplier;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
        }

        public String getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getPurchasePerson() {
            return purchasePerson;
        }

        public void setPurchasePerson(String purchasePerson) {
            this.purchasePerson = purchasePerson;
        }

        public String getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(String payMethod) {
            this.payMethod = payMethod;
        }
        public String getPaySum() {
            return paySum;
        }

        public void setPaySum(String paySum) {
            this.paySum = paySum;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ToDoNotifyDetailEntity{" +
                "success='" + success + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
