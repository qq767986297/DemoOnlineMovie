package com.bw.movie.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import com.bw.movie.base.App;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Time: 2020/4/17
 * Author: 王冠华
 * Description:
 */
public class RetrofiManger {
    private static String BASEURL="http://mobile.bwstudent.com/movieApi/";
    private Apis apis;

    private RetrofiManger() {
        initRetrofi();
    }
    private  static  class SingleInstance{
        private static final RetrofiManger INSTANCE=new RetrofiManger();
    }
    public static RetrofiManger getInstance(){
        return SingleInstance.INSTANCE;
    }
    private void initRetrofi(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HeaderIntercepter())
                .addInterceptor(httpLoggingInterceptor);
        OkHttpClient build = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(build)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apis = retrofit.create(Apis.class);
    }

    public Apis getApis() {
        return apis;
    }

    public boolean isNetWork(Context context){
        ConnectivityManager cm= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info!=null){
            return true;
        }else {
            return false;
        }
    }
    public class HeaderIntercepter implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String userId = SPUtils.getString(App.getContext(), "login", "userId");
            String sessionId = SPUtils.getString(App.getContext(), "login", "sessionId");
            String email = SPUtils.getString(App.getContext(), "login", "email");
            if(TextUtils.isEmpty(userId)||TextUtils.isEmpty(sessionId)){
                Request build = request.newBuilder()
                        .addHeader("ak", "0110010010000")
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .build();
                return chain.proceed(build);
            }
            Request build = request.newBuilder()
                    .addHeader("userId", userId)
                    .addHeader("sessionId", sessionId)
                    .addHeader("ak", "0110010010000")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            return chain.proceed(build);
        }
    }
    public RequestBody getRequsetBody(List<File> files, HashMap<String,String> map){
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String,String> entry:map.entrySet()){
            Log.i("xxx","key = "+entry.getKey()+"value = "+entry.getValue());
            builder.addFormDataPart(entry.getKey(),entry.getValue()+"");
        }

        for (int i = 0; i <files.size(); i++){
            builder.addFormDataPart("image",files.get(i).getName(),RequestBody.create(MediaType.parse("image/jepg"),files.get(i)));
        }

        return builder.build();
    }

}
