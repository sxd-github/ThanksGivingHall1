package com.example.sxd.thanksgivinghall.bean;

import java.util.List;

/**
 * 获取当前用户的待办任务列表
 * Created by sxd on 2018/2/2.
 */
public class ToDoTaskListEntity {
    private String success;
    private String statusMessage;
    private List<Data> data;
    public class Data{
        private String recordId;          //任务id
        private String title;          //任务标题
        private String forwardFlag;     //是否为转发
        private String sendUser; //发送人
        private String sendDate;      // 发送时间

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getForwardFlag() {
            return forwardFlag;
        }

        public void setForwardFlag(String forwardFlag) {
            this.forwardFlag = forwardFlag;
        }

        public String getSendUser() {
            return sendUser;
        }

        public void setSendUser(String sendUser) {
            this.sendUser = sendUser;
        }

        public String getSendDate() {
            return sendDate;
        }

        public void setSendDate(String sendDate) {
            this.sendDate = sendDate;
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
        return "ToDoTaskListEntity{" +
                "success='" + success + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
