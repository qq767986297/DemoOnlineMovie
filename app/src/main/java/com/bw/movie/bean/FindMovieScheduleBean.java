package com.bw.movie.bean;

import java.util.List;

public class FindMovieScheduleBean {

    /**
     * result : [{"beginTime":"19:20:00","endTime":"21:18:00","fare":0.07,"hallId":12,"id":136,"screeningHall":"9厅"},{"beginTime":"20:30:00","endTime":"22:28:00","fare":0.07,"hallId":10,"id":137,"screeningHall":"情侣厅"},{"beginTime":"11:50:00","endTime":"14:02:00","fare":0.07,"hallId":8,"id":138,"screeningHall":"4D厅"}]
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
         * beginTime : 19:20:00
         * endTime : 21:18:00
         * fare : 0.07
         * hallId : 12
         * id : 136
         * screeningHall : 9厅
         */

        private String beginTime;
        private String endTime;
        private double fare;
        private int hallId;
        private int id;
        private String screeningHall;

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public double getFare() {
            return fare;
        }

        public void setFare(double fare) {
            this.fare = fare;
        }

        public int getHallId() {
            return hallId;
        }

        public void setHallId(int hallId) {
            this.hallId = hallId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getScreeningHall() {
            return screeningHall;
        }

        public void setScreeningHall(String screeningHall) {
            this.screeningHall = screeningHall;
        }
    }
}
