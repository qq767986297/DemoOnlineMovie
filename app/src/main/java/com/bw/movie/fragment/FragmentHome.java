package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activity.FindMovieActivity;
import com.bw.movie.activity.HomeMoreMovieActivity;
import com.bw.movie.activity.MoviesDetailActivity;
import com.bw.movie.adapter.HomeHotAdapter;
import com.bw.movie.adapter.HomeHotListAdapter;
import com.bw.movie.adapter.HomeReleaseAdapter;
import com.bw.movie.adapter.HomeSoonAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.HomeBannerBean;
import com.bw.movie.bean.HomeHotMovieBean;
import com.bw.movie.bean.HomeReleaseMovieBean;
import com.bw.movie.bean.HomeSearchMovieBean;
import com.bw.movie.bean.HomeSoonMovieBean;
import com.bw.movie.bean.MovieReserveBean;
import com.bw.movie.contract.HomeContract;
import com.bw.movie.presenter.HomePresenter;
import com.bw.movie.utils.RetrofiManger;
import com.bw.movie.utils.SPUtils;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Time: 2020/4/20
 * Author: 王冠华
 * Description:
 */
public class FragmentHome extends BaseFragment implements HomeContract.IHomeView, AMapLocationListener, LocationSource {
    @BindView(R.id.iv_home_location)
    ImageView ivLocation;
    @BindView(R.id.iv_home_serach)
    ImageView ivSerach;
    @BindView(R.id.xb_home)
    XBanner xb;
    @BindView(R.id.rv_home_release)
    RecyclerView rvHomeRelease;
    @BindView(R.id.rv_home_sonn)
    RecyclerView rvHomeSonn;
    @BindView(R.id.rv_home_hotmovie)
    RecyclerView rvHomeHotmovie;
    Unbinder unbinder;
    @BindView(R.id.tv_home_more_release)
    TextView tvHomeMoreRelease;
    @BindView(R.id.tv_home_more_soon)
    TextView tvHomeMoreSoon;
    @BindView(R.id.tv_home_more_hot)
    TextView tvHomeMoreHot;
    @BindView(R.id.tv_home_location)
    TextView tvHomeLocation;

    //AMap是地图对象
    private AMap aMap;
    MapView mapView;
    //声明AMapLocationClient类对象，定位发起端
    private AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象，定位参数
    public AMapLocationClientOption mLocationOption = null;
    //声明mListener对象，定位监听器
    private LocationSource.OnLocationChangedListener mListener = null;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    private String city;
    private HomeSoonAdapter adapter;

    @Override
    protected BasePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        mapView = view.findViewById(R.id.mapView);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        if (aMap == null) {
            aMap = mapView.getMap();
            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);//设置了定位的监听
            aMap.setLocationSource(this);
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        }
        location();

        if(city!=null){
            tvHomeLocation.setText(city);
        }else{
            tvHomeLocation.setText("北京");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void initData() {
        boolean netWork = RetrofiManger.getInstance().isNetWork(getActivity());
        if (netWork) {
            BasePresenter presenter = getPresenter();
            if (presenter instanceof HomeContract.IHomePresenter) {
                ((HomeContract.IHomePresenter) presenter).getBanner();
                ((HomeContract.IHomePresenter) presenter).getHomeReleaseMovie(1, 4);
                ((HomeContract.IHomePresenter) presenter).getHomeSoonMovie(1, 3);
                ((HomeContract.IHomePresenter) presenter).getHomeHotMovie(1, 4);
            }
        } else {
            Toast.makeText(getActivity(), "无网络", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.iv_home_location, R.id.iv_home_serach, R.id.tv_home_more_release, R.id.tv_home_more_soon, R.id.tv_home_more_hot})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_home_location:
                break;
            case R.id.iv_home_serach:
                Intent intent = new Intent(getActivity(), FindMovieActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_home_more_release:
                Intent intent1 = new Intent(getActivity(), HomeMoreMovieActivity.class);
                intent1.putExtra("num", 0);
                startActivity(intent1);

                break;
            case R.id.tv_home_more_soon:
                Intent intent2 = new Intent(getActivity(), HomeMoreMovieActivity.class);
                intent2.putExtra("num", 1);
                startActivity(intent2);
                break;
            case R.id.tv_home_more_hot:
                Intent intent3 = new Intent(getActivity(), HomeMoreMovieActivity.class);
                intent3.putExtra("num", 2);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void init(Integer id) {
        Intent intent = new Intent(getActivity(), MoviesDetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void onBannerSuccess(HomeBannerBean homeBannerBean) {
        final List<HomeBannerBean.ResultBean> result = homeBannerBean.getResult();
        xb.setBannerData(result);
        xb.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                HomeBannerBean.ResultBean bean = result.get(position);
                String imageUrl = bean.getImageUrl();
                Glide.with(getActivity()).load(imageUrl).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into((ImageView) view);
            }
        });
    }

    @Override
    public void onHomeReleaseMovieSuccess(HomeReleaseMovieBean homeReleaseMovieBean) {
        final List<HomeReleaseMovieBean.ResultBean> list = homeReleaseMovieBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rvHomeRelease.setLayoutManager(manager);
        HomeReleaseAdapter adapter = new HomeReleaseAdapter(getActivity(), list);
        rvHomeRelease.setAdapter(adapter);
        adapter.setClick(new HomeHotAdapter.onClick() {
            @Override
            public void setOnClick(int position) {
                HomeReleaseMovieBean.ResultBean bean = list.get(position);
                int movieId = bean.getMovieId();
                init(movieId);
            }
        });
    }

    @Override
    public void onHomeSoonMovieSuccess(HomeSoonMovieBean homeSoonMovieBean) {
        final List<HomeSoonMovieBean.ResultBean> list = homeSoonMovieBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvHomeSonn.setLayoutManager(manager);
        adapter = new HomeSoonAdapter(getActivity(), list);
        rvHomeSonn.setAdapter(adapter);
        adapter.setClick(new HomeHotAdapter.onClick() {
            @Override
            public void setOnClick(int position) {
                HomeSoonMovieBean.ResultBean bean = list.get(position);
                int movieId = bean.getMovieId();
                init(movieId);
            }
        });
        adapter.setbtClick(new HomeSoonAdapter.OnbtClick() {
            @Override
            public void setOnbtClick(int movieId) {
                BasePresenter presenter = getPresenter();
                if (presenter instanceof HomeContract.IHomePresenter) {
                    ((HomeContract.IHomePresenter) presenter).getMovieReserve(movieId);
                }
            }
        });
    }

    @Override
    public void onHomeHotMovieSuccess(HomeHotMovieBean homeHotMovieBean) {
        final List<HomeHotMovieBean.ResultBean> list = homeHotMovieBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        HomeHotAdapter adapter = new HomeHotAdapter(getActivity(), list);
        rvHomeHotmovie.setLayoutManager(manager);
        rvHomeHotmovie.setAdapter(adapter);
        HomeHotListAdapter adapter1 = new HomeHotListAdapter(getActivity(), list);
        adapter.setClick(new HomeHotAdapter.onClick() {
            @Override
            public void setOnClick(int position) {
                HomeHotMovieBean.ResultBean bean = list.get(position);
                int movieId = bean.getMovieId();
                init(movieId);
            }
        });

    }

    @Override
    public void onHomeSerachMovieSuccess(HomeSearchMovieBean homeSearchMovieBean) {

    }

    @Override
    public void onMovieReserve(MovieReserveBean movieReserveBean) {
        String message = movieReserveBean.getMessage();
        adapter.notifyDataSetChanged();
        if (message.equals("预约成功")) {
            Toast.makeText(getActivity(), "预约成功", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity(), "预约失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }


    public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }


    public void deactivate() {
        mListener = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    //添加图钉
                    //  aMap.addMarker(getMarkerOptions(amapLocation));
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    //Toast.makeText(getContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                    city = aMapLocation.getCity();
                    //Toast.makeText(getContext(), ""+city1+"11", Toast.LENGTH_SHORT).show();
                    tvHomeLocation.setText(city + "");
                    SPUtils.putString(getActivity(),"city","city",city);
                    isFirstLoc = false;
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                Toast.makeText(getContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

}
