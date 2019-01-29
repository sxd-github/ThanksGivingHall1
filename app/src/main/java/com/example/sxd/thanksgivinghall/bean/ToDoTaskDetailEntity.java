package com.example.sxd.thanksgivinghall.bean;

import java.util.List;

/**
 * 获取待办任务详情以及回复列表
 * Created by sxd on 2018/2/2.
 */
public class ToDoTaskDetailEntity {
    private String success;
    private String statusMessage;
    private Data data;
    public class Data{
        private String files;          //附件
        private String title;          //任务标题
        private String forwardFlag;     //是否为紧急任务
        private String content;        // 任务内容
        private String sendUser; //发送人
        private String sendDate;      // 发送时间
        private String recordId;      // 发送时间

        private List<Reply_List> replyList;
        public class Reply_List{
            private String sendUser;
            private String replyDate;
            private String replyFlag;  //'0' 未完成 '1' 完成 '2' 无法完成
            private String replyContent;

            public String getSendUser() {
                return sendUser;
            }

            public void setSendUser(String sendUser) {
                this.sendUser = sendUser;
            }

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

            public String getReplyContent() {
                return replyContent;
            }

            public void setReplyContent(String replyContent) {
                this.replyContent = replyContent;
            }
        }

        public String getFiles() {
            return files;
        }

        public void setFiles(String files) {
            this.files = files;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
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
        return "ToDoTaskDetailEntity{" +
                "success='" + success + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
