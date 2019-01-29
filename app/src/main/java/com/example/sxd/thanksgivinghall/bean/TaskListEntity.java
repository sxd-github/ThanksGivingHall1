package com.example.sxd.thanksgivinghall.bean;

import java.util.List;

/**
 * 获取当前用户已发布的任务列表
 * Created by sxd on 2018/2/2.
 */
public class TaskListEntity {
    private String success;
    private String statusMessage;
    private List<Data> data;
    public class Data{
        private String id;          //任务id
        private String title;          //任务标题
        private String CompleteFlag;     //是否为转发任务
        private String forwardFlag;     //任务完成状态
        private String sendDate;      // 发送时间

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCompleteFlag() {
            return CompleteFlag;
        }

        public void setCompleteFlag(String completeFlag) {
            CompleteFlag = completeFlag;
        }


        public String getForwardFlag() {
            return forwardFlag;
        }

        public void setForwardFlag(String forwardFlag) {
            this.forwardFlag = forwardFlag;
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
        return "TaskListEntity{" +
                "success='" + success + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
