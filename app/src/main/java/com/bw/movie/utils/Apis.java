package com.bw.movie.utils;

import com.bw.movie.bean.AddMovieCommentBean;
import com.bw.movie.bean.CancelFollowMovieBean;
import com.bw.movie.bean.FollowMovieBean;
import com.bw.movie.bean.HomeBannerBean;
import com.bw.movie.bean.HomeHotMovieBean;
import com.bw.movie.bean.HomeReleaseMovieBean;
import com.bw.movie.bean.HomeSearchMovieBean;
import com.bw.movie.bean.HomeSoonMovieBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.MovieCommentBean;
import com.bw.movie.bean.MovieCommentGreatBean;
import com.bw.movie.bean.MovieDetailBean;
import com.bw.movie.bean.MovieReserveBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.SendEmailCodeBean;
import com.bw.movie.bean.UserFollowMovieBean;

import io.reactivex.Observable;
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
}
