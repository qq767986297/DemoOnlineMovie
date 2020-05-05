package com.bw.movie.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.HomeSoonMovieBean;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/4/22
 * Author: 王冠华
 * Description:
 */
public class HomeSoonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomeSoonMovieBean.ResultBean> list;

    public HomeSoonAdapter(Context context, List<HomeSoonMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_home_soon, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        HomeSoonMovieBean.ResultBean bean = list.get(i);
        String imageUrl = bean.getImageUrl();
       final int movieId = bean.getMovieId();
        String name = bean.getName();
        long releaseTime = bean.getReleaseTime();
        Date date = new Date(releaseTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        int wantSeeNum = bean.getWantSeeNum();
        ((ViewHolder) viewHolder).name.setText(name);
        ((ViewHolder) viewHolder).time.setText(format);
        ((ViewHolder) viewHolder).want.setText(wantSeeNum + "人想看");
        Glide.with(context).load(imageUrl).into(((ViewHolder) viewHolder).iv);
        int reserve = bean.getWhetherReserve();
        if(reserve==1){
             ((ViewHolder) viewHolder).btItemSoon.setText("已预约");
            ((ViewHolder) viewHolder).btItemSoon.setBackgroundColor(Color.parseColor("#d3af56"));
        }else {
            ((ViewHolder) viewHolder).btItemSoon.setText("预约");
        }
        ((ViewHolder) viewHolder).btItemSoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soonOnbtClick.setOnbtClick(movieId);
            }
        });
        ((ViewHolder) viewHolder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monClick.setOnClick(i);
                EventBus.getDefault().post(list.get(i).getMovieId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private HomeHotAdapter.onClick monClick;

    public interface onClick {
        void setOnClick(int position);
    }

    public void setClick(HomeHotAdapter.onClick onClick) {
        monClick = onClick;
    }
    private OnbtClick soonOnbtClick;

    public interface OnbtClick {
        void setOnbtClick(int movieId);
    }

    public void setbtClick(OnbtClick onbtClick) {
        soonOnbtClick = onbtClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_home_soon)
        LinearLayout ll;

        @BindView(R.id.iv_item_soon)
        ImageView iv;
        @BindView(R.id.tv_item_soon_name)
        TextView name;
        @BindView(R.id.tv_item_soon_time)
        TextView time;
        @BindView(R.id.tv_item_soon_want)
        TextView want;
        @BindView(R.id.bt_item_soon)
        Button btItemSoon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
