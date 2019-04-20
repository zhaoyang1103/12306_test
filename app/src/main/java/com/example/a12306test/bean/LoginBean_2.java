package com.example.a12306test.bean;

import java.util.List;

/**
 * Created by 昭阳 on 2019/4/20.
 */
public class LoginBean_2 {

    /**
     * validateMessagesShowId : _validatorMessage
     * status : true
     * httpstatus : 200
     * data : {"uamflag":"1","message":"非法请求"}
     * messages : []
     * validateMessages : {}
     */

    private String validateMessagesShowId;
    private boolean status;
    private int httpstatus;
    private DataBean data;
    private ValidateMessagesBean validateMessages;
    private List<?> messages;

    public String getValidateMessagesShowId() {
        return validateMessagesShowId;
    }

    public void setValidateMessagesShowId(String validateMessagesShowId) {
        this.validateMessagesShowId = validateMessagesShowId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getHttpstatus() {
        return httpstatus;
    }

    public void setHttpstatus(int httpstatus) {
        this.httpstatus = httpstatus;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ValidateMessagesBean getValidateMessages() {
        return validateMessages;
    }

    public void setValidateMessages(ValidateMessagesBean validateMessages) {
        this.validateMessages = validateMessages;
    }

    public List<?> getMessages() {
        return messages;
    }

    public void setMessages(List<?> messages) {
        this.messages = messages;
    }

    public static class DataBean {
        /**
         * uamflag : 1
         * message : 非法请求
         */

        private String uamflag;
        private String message;

        public String getUamflag() {
            return uamflag;
        }

        public void setUamflag(String uamflag) {
            this.uamflag = uamflag;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class ValidateMessagesBean {
    }
}
