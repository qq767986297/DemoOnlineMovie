package com.bw.movie.bean;

/**
 * Time: 2020/5/1
 * Author: 王冠华
 * Description:
 */
public class AddMovieCommentBean {

    /**
     * message : 评论成功
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
