package com.example.sxd.thanksgivinghall.bean;

import java.util.Date;
import java.util.List;

/**
 * 获取订单信息
 * Created by sxd on 2018/2/2.
 */
public class CustomerOrderListEntity {
    private String success;
    private String statusMessage;
    private List<Data> data;
    public class Data {
        private String id;
        private String cusname;		// 客户姓名
        private String goods;		// 货物名称
        private String price;		// 单价
        private String goodsnum;		// 进货量
        private String sumprice;		// 总价
        private String ordertype;		// 支付类型
        private String date;		// 日期
        private Date beginDate;		// 开始 日期
        private Date endDate;		// 结束 日期

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCusname() {
            return cusname;
        }

        public void setCusname(String cusname) {
            this.cusname = cusname;
        }

        public String getGoods() {
            return goods;
        }

        public void setGoods(String goods) {
            this.goods = goods;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getGoodsnum() {
            return goodsnum;
        }

        public void setGoodsnum(String goodsnum) {
            this.goodsnum = goodsnum;
        }

        public String getSumprice() {
            return sumprice;
        }

        public void setSumprice(String sumprice) {
            this.sumprice = sumprice;
        }

        public String getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(String ordertype) {
            this.ordertype = ordertype;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Date getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(Date beginDate) {
            this.beginDate = beginDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
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
