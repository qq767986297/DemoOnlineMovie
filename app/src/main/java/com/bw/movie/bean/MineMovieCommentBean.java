package com.bw.movie.bean;

import java.util.List;

/**
 * Time: 2020/5/6
 * Author: 王冠华
 * Description:
 */
public class MineMovieCommentBean {

    /**
     * result : [{"commentTime":1588302851000,"director":"曾国祥","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/sndn/sndn1.jpg","movieId":22,"movieName":"少年的你","movieScore":0,"myCommentContent":"哈哈哈哈","myCommentScore":5,"starring":"周冬雨,易烊千玺,张耀,周也,尹昉"},{"commentTime":1588302541000,"director":"陈凯歌","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/whwdzg/whwdzg1.jpg","movieId":23,"movieName":"我和我的祖国","movieScore":0,"myCommentContent":"","myCommentScore":5,"starring":"黄渤,张译,杜江,葛优,刘昊然,吴京"},{"commentTime":1588302219000,"director":"\r\n李仁港","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/pdz/pdz1.jpg","movieId":25,"movieName":"攀登者","movieScore":0,"myCommentContent":"","myCommentScore":5,"starring":"吴京,章子怡,井柏然,胡歌"},{"commentTime":1588301906000,"director":"\r\n刘伟强","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/zgjz/zgjz1.jpg","movieId":24,"movieName":"中国机长","movieScore":0,"myCommentContent":"","myCommentScore":5,"starring":"张涵予,欧豪,袁泉,张天爱,李沁"},{"commentTime":1588298798000,"director":"文牧野","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/wbsys/wbsys1.jpg","movieId":1,"movieName":"我不是药神","movieScore":0,"myCommentContent":"好看","myCommentScore":4.5,"starring":"徐峥,周一围,王传君,谭卓,章宇,杨新鸣,王砚辉"}]
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
         * commentTime : 1588302851000
         * director : 曾国祥
         * imageUrl : http://mobile.bwstudent.com/images/movie/stills/sndn/sndn1.jpg
         * movieId : 22
         * movieName : 少年的你
         * movieScore : 0
         * myCommentContent : 哈哈哈哈
         * myCommentScore : 5
         * starring : 周冬雨,易烊千玺,张耀,周也,尹昉
         */

        private long commentTime;
        private String director;
        private String imageUrl;
        private int movieId;
        private String movieName;
        private int movieScore;
        private String myCommentContent;
        private int myCommentScore;
        private String starring;

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public int getMovieScore() {
            return movieScore;
        }

        public void setMovieScore(int movieScore) {
            this.movieScore = movieScore;
        }

        public String getMyCommentContent() {
            return myCommentContent;
        }

        public void setMyCommentContent(String myCommentContent) {
            this.myCommentContent = myCommentContent;
        }

        public int getMyCommentScore() {
            return myCommentScore;
        }

        public void setMyCommentScore(int myCommentScore) {
            this.myCommentScore = myCommentScore;
        }

        public String getStarring() {
            return starring;
        }

        public void setStarring(String starring) {
            this.starring = starring;
        }
    }
}
