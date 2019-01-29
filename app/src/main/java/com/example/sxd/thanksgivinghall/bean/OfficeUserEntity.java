package com.example.sxd.thanksgivinghall.bean;

import java.util.List;

/**
 * 获取部门及部门用户
 * Created by sxd on 2018/2/2.
 */
public class OfficeUserEntity {
    private String success;
    private String statusMessage;
    private List<Data> data;
    public class Data{
        private String name;
        private String pId; //父级id
        private String id;  //id
        private String type;// "人员"/"部门"
        private String userId;// 用户id

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getpId() {
            return pId;
        }

        public void setpId(String pId) {
            this.pId = pId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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
        return "OfficeUserEntity{" +
                "success='" + success + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                ", data= " + data +
                '}';
    }
}
