package com.bw.movie.bean;

/**
 * Time: 2020/5/6
 * Author: 王冠华
 * Description:
 */
public class SystemMsgChangeBean {

    /**
     * message : 状态改变成功
     * status : 0000
     */

    private String message;
    private String status;

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
}
