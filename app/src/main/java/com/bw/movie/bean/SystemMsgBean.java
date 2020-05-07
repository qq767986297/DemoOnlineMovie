package com.bw.movie.bean;

import java.util.List;

/**
 * Time: 2020/5/5
 * Author: 王冠华
 * Description:
 */
public class SystemMsgBean {

    /**
     * result : [{"content":"感谢您注册维度账号,小维希望您使用我们的产品能够获得快乐~","id":2813,"pushTime":1587364533000,"status":0,"title":"系统通知","userId":13946}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * content : 感谢您注册维度账号,小维希望您使用我们的产品能够获得快乐~
         * id : 2813
         * pushTime : 1587364533000
         * status : 0
         * title : 系统通知
         * userId : 13946
         */

        private String content;
        private int id;
        private long pushTime;
        private int status;
        private String title;
        private int userId;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getPushTime() {
            return pushTime;
        }

        public void setPushTime(long pushTime) {
            this.pushTime = pushTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
