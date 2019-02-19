package com.example.sxd.thanksgivinghall.bean;

import java.util.List;

/**
 * 获取供应商信息
 * Created by sxd on 2018/2/2.
 */
public class CustomerDetailEntity {
    private String success;
    private String statusMessage;
    private Data data;
    public class Data {
        private String id;
        private String username;        // 客户姓名
        private String nation;        // 民族
        private String sex;        // 性别
        private String source;        // 客户来源
        private String tel;        // 手机号码
        private String year;        // 年
        private String month;        // 月
        private String day;        // 日

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }


        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
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
        return "SupplierListEntity{" +
                "success='" + success + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
