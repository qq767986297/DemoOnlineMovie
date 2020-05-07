package com.bw.movie.bean;

import java.util.List;

/**
 * Time: 2020/5/5
 * Author: 王冠华
 * Description:
 */
public class MineOrderBean {

    /**
     * result : [{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/dzd6qmwj/dzd6qmwj1.jpg","movieId":16,"name":"碟中谍6：全面瓦解","releaseTime":1600704000000,"wantSeeNum":64},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/jmyx/jmyx1.jpg","movieId":14,"name":"解码游戏","releaseTime":1599062400000,"wantSeeNum":49},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/aqgy/aqgy1.jpg","movieId":15,"name":"爱情公寓","releaseTime":1596988800000,"wantSeeNum":32},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/xhssf/xhssf1.jpg","movieId":3,"name":"西虹市首富","releaseTime":1595779200000,"wantSeeNum":19},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/mtyj/mtyj1.jpg","movieId":2,"name":"摩天营救","releaseTime":1595174400000,"wantSeeNum":9},{"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/xbyz/xbyz1.jpg","movieId":5,"name":"邪不压正","releaseTime":1594569600000,"wantSeeNum":7}]
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
         * imageUrl : http://mobile.bwstudent.com/images/movie/stills/dzd6qmwj/dzd6qmwj1.jpg
         * movieId : 16
         * name : 碟中谍6：全面瓦解
         * releaseTime : 1600704000000
         * wantSeeNum : 64
         */

        private String imageUrl;
        private int movieId;
        private String name;
        private long releaseTime;
        private int wantSeeNum;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getWantSeeNum() {
            return wantSeeNum;
        }

        public void setWantSeeNum(int wantSeeNum) {
            this.wantSeeNum = wantSeeNum;
        }
    }
}
