package com.example.sxd.thanksgivinghall.bean;

import java.util.List;

/**
 * 获取已发布任务详情以及接收人的回复列表
 * Created by sxd on 2018/2/2.
 */
public class TaskDetailEntity {
    private String success;
    private String statusMessage;
    private Data data;
    public class Data{
        private String taskId;          //任务id
        private String title;          //任务标题
        private String forwardFlag;     //是否为紧急任务
        private String files;          //附件
        private String receNames;     //接收人姓名
        private String sendDate;      // 发送时间
        private String content;      // 发送内容
        private List<Reply_List> replyList;  //接收人的回复列表

        public class Reply_List{
            private String replyDate;      // 回复时间
            private String replyFlag;
            private String sendUser;
            private String content;

            public String getReplyDate() {
                return replyDate;
            }

            public void setReplyDate(String replyDate) {
                this.replyDate = replyDate;
            }

            public String getReplyFlag() {
                return replyFlag;
            }

            public void setReplyFlag(String replyFlag) {
                this.replyFlag = replyFlag;
            }

            public String getSendUser() {
                return sendUser;
            }

            public void setSendUser(String sendUser) {
                this.sendUser = sendUser;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
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

        public String getReceNames() {
            return receNames;
        }

        public void setReceNames(String receNames) {
            this.receNames = receNames;
        }

        public String getSendDate() {
            return sendDate;
        }

        public void setSendDate(String sendDate) {
            this.sendDate = sendDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFiles() {
            return files;
        }

        public void setFiles(String files) {
            this.files = files;
        }

        public List<Reply_List> getReplyList() {
            return replyList;
        }

        public void setReplyList(List<Reply_List> replyList) {
            this.replyList = replyList;
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
        return "TaskDetailEntity{" +
                "success='" + success + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
