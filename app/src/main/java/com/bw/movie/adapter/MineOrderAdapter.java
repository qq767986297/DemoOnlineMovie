package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.MineOrderBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/5
 * Author: 王冠华
 * Description:
 */
public class MineOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MineOrderBean.ResultBean> list;

    public MineOrderAdapter(Context context, List<MineOrderBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_mine_order, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MineOrderBean.ResultBean bean = list.get(i);
        String imageUrl = bean.getImageUrl();
        Uri uri = Uri.parse(imageUrl);
        long releaseTime = bean.getReleaseTime();
        int wantSeeNum = bean.getWantSeeNum();
        Date date = new Date(releaseTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
        String format = simpleDateFormat.format(date);
        ((ViewHolder)viewHolder).iv.setImageURI(uri);
        ((ViewHolder)viewHolder).name.setText(bean.getName());
        ((ViewHolder)viewHolder).want.setText(wantSeeNum+"人想看");
        ((ViewHolder)viewHolder).time.setText(format+"上映");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_order)
        SimpleDraweeView iv;
        @BindView(R.id.tv_item_mine_order_name)
        TextView name;
        @BindView(R.id.tv_item_mine_order_time)
        TextView time;
        @BindView(R.id.tv_item_mine_order_want)
        TextView want;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
