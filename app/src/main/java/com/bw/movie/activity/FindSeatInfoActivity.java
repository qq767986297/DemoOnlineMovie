package com.bw.movie.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bw.movie.R;
import com.bw.movie.adapter.FindMovieScheduleAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.AliPayBean;
import com.bw.movie.bean.BuyTicketBean;
import com.bw.movie.bean.FindCinemasInfoByRegion;
import com.bw.movie.bean.FindMovieScheduleBean;
import com.bw.movie.bean.FindSeatInfoBean;
import com.bw.movie.contract.IPayContract;
import com.bw.movie.contract.ISelectContract;
import com.bw.movie.custom.SeatTable;
import com.bw.movie.presenter.SelectPresenter;
import com.bw.movie.utils.MD5;
import com.bw.movie.utils.SPUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindSeatInfoActivity extends BaseActivity implements ISelectContract.IView{


    private static final int SDK_PAY_FLAG = 1;
    @BindView(R.id.iv_fh)
    ImageView iv;
    @BindView(R.id.tv_name)
    TextView name;
    @BindView(R.id.st)
    SeatTable st;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.bt_xz)
    Button bt_pay;
    @BindView(R.id.iv_ym)
    SimpleDraweeView ivbg;
    @BindView(R.id.rb_wechat)
    RadioButton rbWechat;
    @BindView(R.id.rb_ali)
    RadioButton rbAli;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.rl_pay)
    RelativeLayout rlPay;

    private int movieId;
    private double fare;
    private int hallId;
    private String hall;
    String a;
    int i = 1;
    int flag;
    private String jia;

    @Override
    protected BasePresenter initPresenter() {
        return new SelectPresenter(this);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_find_seat_info;
    }

    @Override
    protected void initView() {
            String str="139461movie";
        jia = MD5.jia(str);
        Log.i("MD5", jia +"");
    }

    @Override
    protected void initData() {
        String string = SPUtils.getString(this, "img", "img");
        Uri uri = Uri.parse(string);
        ivbg.setImageURI(uri);
        movieId = SPUtils.getInt(this, "movieId", "movieId");
        BasePresenter presenter = getPresenter();
        if (presenter instanceof ISelectContract.IPresenter) {
            Intent intent = getIntent();
            int cinemaId = intent.getIntExtra("cinemaId", 0);
            ((ISelectContract.IPresenter) presenter).getFindMovieSchedule(movieId, cinemaId);
        }
    }

    @Override
    public void onShowSeat(FindSeatInfoBean findSeatInfoBean) {
        int maxh = 0;
        int maxl = 0;
        List<FindSeatInfoBean.ResultBean> result1 = findSeatInfoBean.getResult();
        for (int i = 0; i < result1.size(); i++) {
            if (maxh < Integer.valueOf(result1.get(i).getRow())) {
                maxh = Integer.valueOf(result1.get(i).getRow());
            }
            if (maxl < Integer.valueOf(result1.get(i).getSeat())) {
                maxl = Integer.valueOf(result1.get(i).getSeat());
            }
        }
        //  le.setData(Integer.valueOf(result.get(i).getRow()), Integer.valueOf(result.get(i).getSeat()));
        st.setScreenName(hall);//设置屏幕名称
        st.setMaxSelected(3);//设置最多选中
        st.setSeatChecker(new SeatTable.SeatChecker() {
            @Override
            public boolean isValidSeat(int row, int column) {
                if (column == 2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 4 && column == 6) {
                    return true;
                } else if (row == 6 && column == 6) {
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {

                a = row + "-" + column + ",";
                fare += fare;
                bt_pay.setText("￥" + fare);
            }

            @Override
            public void unCheck(int row, int column) {

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }
        });
        st.setData(maxh, maxl);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void init(FindMovieScheduleBean.ResultBean bean) {
        fare = bean.getFare();
        hallId = bean.getHallId();
        hall = bean.getScreeningHall();

        BasePresenter presenter = getPresenter();
        if (presenter instanceof ISelectContract.IPresenter) {
            Intent intent = getIntent();
            int cinemaId = intent.getIntExtra("cinemaId", 0);
            ((ISelectContract.IPresenter) presenter).getShowSeat(hallId);
        }
    }

    @Override
    public void onFindMovieSchedule(FindMovieScheduleBean findMovieScheduleBean) {
        List<FindMovieScheduleBean.ResultBean> list = findMovieScheduleBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        FindMovieScheduleAdapter adapter = new FindMovieScheduleAdapter(this, list);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_fh, R.id.bt_xz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fh:
                finish();
                break;
            case R.id.bt_xz:
                i++;
                if (i % 2 == 0) {
                    rlPay.setVisibility(View.VISIBLE);
                    rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            if(rbWechat.getId()==checkedId){
                                bt_pay.setText("微信支付"+fare+"元");
                                flag=1;
                            }
                            if(rbAli.getId()==checkedId){
                                bt_pay.setText("支付宝支付"+fare+"元");

                                flag=2;
                            }
                        }
                    });
                } else if(i%2!=0){
                        //如果=1,则调用微信支付
                    if(flag==1){
//                        BasePresenter presenter = getPresenter();
//                        if(presenter instanceof IPayContract.IPresenter){
//                            ((IPayContract.IPresenter)presenter).getBuyTicket(1,a,jia);
                        Toast.makeText(this, "微信支付", Toast.LENGTH_SHORT).show();
//                        }
                    }else {
                        //如果=2,则调用支付宝支付
                        Toast.makeText(this, "支付宝支付", Toast.LENGTH_SHORT).show();
                        BasePresenter presenter = getPresenter();
                        if(presenter instanceof ISelectContract.IPresenter){
                            ((ISelectContract.IPresenter)presenter).getBuyTicket(1,a,jia);
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
    //电影票下单
    @Override
    public void onBuyTicket(BuyTicketBean buyTicketBean) {
        String orderId = buyTicketBean.getOrderId();
        String message = buyTicketBean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        BasePresenter presenter = getPresenter();
        if(presenter instanceof ISelectContract.IPresenter){
            ((ISelectContract.IPresenter)presenter).getPay(2,orderId);
        }
    }
    Handler handler=new Handler();
    //支付
    @Override
    public void onPay(AliPayBean aliPayBean) {
        String message = aliPayBean.getMessage();
        String result = aliPayBean.getResult();
        final String orderInfo = result;   // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(FindSeatInfoActivity.this);
                Map<String,String> result = alipay.payV2(orderInfo,true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
    @Override
    public void onCinemaByRegion(FindCinemasInfoByRegion findCinemasInfoByRegion) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
