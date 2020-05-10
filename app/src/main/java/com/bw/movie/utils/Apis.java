package com.bw.movie.utils;

import com.bw.movie.bean.AddMovieCommentBean;
import com.bw.movie.bean.AliPayBean;
import com.bw.movie.bean.BuyTicketBean;
import com.bw.movie.bean.CancelFollowMovieBean;
import com.bw.movie.bean.CinemaCanelFollowBean;
import com.bw.movie.bean.CinemaCommentGoodBean;
import com.bw.movie.bean.CinemaDetailBean;
import com.bw.movie.bean.CinemaDetailCommentBean;
import com.bw.movie.bean.CinemaFollowBean;
import com.bw.movie.bean.CinemaLinkBean;
import com.bw.movie.bean.CinemaNearbyBean;
import com.bw.movie.bean.CinemaRecommendBean;
import com.bw.movie.bean.CinemaRegionBean;
import com.bw.movie.bean.CinemaScheduleListBean;
import com.bw.movie.bean.FindCinemasInfoByRegion;
import com.bw.movie.bean.FindDataBean;
import com.bw.movie.bean.FindMovieScheduleBean;
import com.bw.movie.bean.FindNewVersionBean;
import com.bw.movie.bean.FindSeatInfoBean;
import com.bw.movie.bean.FollowMovieBean;
import com.bw.movie.bean.HomeBannerBean;
import com.bw.movie.bean.HomeHotMovieBean;
import com.bw.movie.bean.HomeReleaseMovieBean;
import com.bw.movie.bean.HomeSearchMovieBean;
import com.bw.movie.bean.HomeSoonMovieBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.MineMovieCommentBean;
import com.bw.movie.bean.MineOrderBean;
import com.bw.movie.bean.MovieCommentBean;
import com.bw.movie.bean.MovieCommentGreatBean;
import com.bw.movie.bean.MovieDetailBean;
import com.bw.movie.bean.MovieReserveBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.SendEmailCodeBean;
import com.bw.movie.bean.SystemMsgBean;
import com.bw.movie.bean.SystemMsgChangeBean;
import com.bw.movie.bean.UpLoadHeadPicBean;
import com.bw.movie.bean.UserFeedBackBean;
import com.bw.movie.bean.UserFollowMovieBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Time: 2020/4/17
 * Author: 王冠华
 * Description:
 */
public interface Apis {
    //获取邮箱验证码
    @POST("user/v2/sendOutEmailCode")
    @FormUrlEncoded
    Observable<SendEmailCodeBean>getRegEmailCode(@Field("email") String email);
    //注册
    @POST("user/v2/register")
    @FormUrlEncoded
    Observable<RegisterBean> getRegsiter(@Field("nickName") String nickName
            , @Field("pwd") String pwd
            , @Field("email") String email
            , @Field("code") String code);
    //登录
    @POST("user/v2/login")
    @FormUrlEncoded
    Observable<LoginBean>getLogin(@Field("email") String email, @Field("pwd") String pwd);
    //首页轮播图
    @GET("tool/v2/banner")
    Observable<HomeBannerBean>getBanner();
    //首页热门电影
    @GET("movie/v2/findHotMovieList")
    Observable<HomeHotMovieBean>getHotMovie(@Query("page") int page, @Query("count") int count);
    //首页正在热映电影
    @GET("movie/v2/findReleaseMovieList")
    Observable<HomeReleaseMovieBean>getReleaseMovie(@Query("page") int page, @Query("count") int count);
    //首页即将上映电影
    @GET("movie/v2/findComingSoonMovieList")
    Observable<HomeSoonMovieBean>getSoonMovie(@Query("page") int page, @Query("count") int count);
    //首页搜索电影
    @GET("movie/v2/findMovieByKeyword")
    Observable<HomeSearchMovieBean>getHomeSerachMovie(@Query("keyword")String keyword,@Query("page")int page,@Query("count")int count);
    //查询电影详情
    @GET("movie/v2/findMoviesDetail")
    Observable<MovieDetailBean>getDetail(@Query("movieId")int movieId);
    //根据电影id查询评论
    @GET("movie/v2/findAllMovieComment")
    Observable<MovieCommentBean>getMovieComment(@Query("movieId") int movieId,@Query("page")int page,@Query("count")int count);
    @POST("movie/v2/verify/reserve")
    @FormUrlEncoded
    Observable<MovieReserveBean>getMovieReserve(@Field("movieId")int movieId);
    //电影详情页关注电影成功
    @GET("movie/v1/verify/followMovie")
    Observable<FollowMovieBean>getFollowMovie(@Query("movieId")int movieId);
    //电影详情页关注电影取消
    @GET("movie/v1/verify/cancelFollowMovie")
    Observable<CancelFollowMovieBean>getCancelFollowMovie(@Query("movieId")int movieId);
    //我的关注电影
    @GET("user/v2/verify/findUserFollowMovieList")
    Observable<UserFollowMovieBean>getUserFollowMovie(@Query("page")int page,@Query("count")int count);
    //添加电影评论
    @POST("movie/v1/verify/movieComment")
    @FormUrlEncoded
    Observable<AddMovieCommentBean>getAddMovieComment(@Field("movieId")int movieId,@Field("commentContent")String content,@Field("score")double score);
    //电影评论点赞
    @POST("movie/v1/verify/movieCommentGreat")
    @FormUrlEncoded
    Observable<MovieCommentGreatBean>getMovieCommentGreat(@Field("commentId")int commentId);
    //用户预约电影查询
    @GET("user/v2/verify/findUserReserve")
    Observable<MineOrderBean>getUserOrder();
    //用户意见反馈
    @POST("tool/v1/verify/recordFeedBack")
    @FormUrlEncoded
    Observable<UserFeedBackBean>getFeedBack(@Field("content")String content);
    //系统推送消息
    @GET("tool/v1/verify/findAllSysMsgList")
    Observable<SystemMsgBean>getSystemMsg(@Query("page")int page,@Query("count")int count);
    //系统推送消息状态改变
    @GET("tool/v1/verify/changeSysMsgStatus")
    Observable<SystemMsgChangeBean>getSystemMsgChange(@Query("id")int id);
    //用户电影评论列表
    @GET("user/v2/verify/findMyMovieCommentList")
    Observable<MineMovieCommentBean>getMineMovieComment(@Query("page")int page,@Query("count")int count);
    //推荐影院
    @GET("cinema/v1/findRecommendCinemas")
    Observable<CinemaRecommendBean>getCinemaRecommend(@Query("page")int page,@Query("count")int count);
    //附近影院
    @GET("cinema/v1/findNearbyCinemas")
    Observable<CinemaNearbyBean>getCinemaNearby(@Query("longitude")String longitude,@Query("latitude")String latitude,@Query("page")int page,@Query("count")int count);
    //影院地区
    @GET("tool/v2/findRegionList")
    Observable<CinemaRegionBean>getCinemaRegion();
    //影院地区
    @GET("cinema/v2/findCinemaByRegion")
    Observable<CinemaLinkBean>getCinemaLink(@Query("regionId")int regionId);
    //影院详情页
    @GET("cinema/v1/findCinemaInfo")
    Observable<CinemaDetailBean>getCinemaDetail(@Query("cinemaId")int cinemaId);
    //影院详情页评论列表
    @GET("cinema/v1/findAllCinemaComment")
    Observable<CinemaDetailCommentBean>getCinemaDetailComment(@Query("cinemaId")int cinemaId,@Query("page")int page,@Query("count")int count);
    //影院关注
    @GET("cinema/v1/verify/followCinema")
    Observable<CinemaFollowBean>getCinemaFollow(@Query("cinemaId")int cinemaId);
    //影院取消关注
    @GET("cinema/v1/verify/cancelFollowCinema")
    Observable<CinemaCanelFollowBean>getCinemaCanelFollow(@Query("cinemaId")int cinemaId);
    //影院评论点赞
    @POST("cinema/v1/verify/cinemaCommentGreat")
    @FormUrlEncoded
    Observable<CinemaCommentGoodBean>getCinemaGood(@Field("commentId")int commentId);
    //查询电影院下排期
    @GET("cinema/v2/findCinemaScheduleList")
    Observable<CinemaScheduleListBean>getCinemaSchedu(@Query("cinemaId")int cinemaId,@Query("page")int page,@Query("count")int count);
    //查询一周排期时间
    @GET("tool/v2/findDateList")
    Observable<FindDataBean>getFindData();
    //根据电影id,区域id 查询播放影院信息
    @GET("movie/v2/findCinemasInfoByRegion")
    Observable<FindCinemasInfoByRegion> getCinemasInfoByRegion(@Query("movieId") int movieId, @Query("regionId") int regionId,
                                                                @Query("page")int page, @Query("count")int count);
    //根据影厅id 查询座位信息
    @GET("movie/v2/findSeatInfo")
    Observable<FindSeatInfoBean> getfindSeatInfo(@Query("hallId")int hallId);
    //根据电影ID和影院ID查询电影排期列表
    @GET("movie/v2/findMovieSchedule")
    Observable<FindMovieScheduleBean> getfindMovieSchedule(@Query("movieId")int movieId, @Query("cinemaId")int cinemaId);
    //头像上传
    @POST("user/v1/verify/uploadHeadPic")
    Observable<UpLoadHeadPicBean> getUpLoadHeadPicBean(@Body RequestBody body);
    //查询新版本
    @GET("tool/v1/findNewVersion")
    Observable<FindNewVersionBean>getFindNewVersion();
    //电影票下单
    @POST("movie/v2/verify/buyMovieTickets")
    @FormUrlEncoded
    Observable<BuyTicketBean>getBuyTicket(@Field("scheduleId")int scheduleId,@Field("seat")String seat,@Field("sign")String sign);
    //支付
    @POST("movie/v2/verify/pay")
    @FormUrlEncoded
    Observable<AliPayBean>getPay(@Field("payType")int payType,@Field("orderId")String orderId);
}
