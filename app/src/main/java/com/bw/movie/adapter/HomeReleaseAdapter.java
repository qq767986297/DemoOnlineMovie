package com.bw.movie.adapter;

import android.content.Context;
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
import com.bw.movie.bean.HomeReleaseMovieBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/4/22
 * Author: 王冠华
 * Description:
 */
public class HomeReleaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomeReleaseMovieBean.ResultBean> list;


    public HomeReleaseAdapter(Context context, List<HomeReleaseMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_home_release, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        HomeReleaseMovieBean.ResultBean bean = list.get(i);
        String imageUrl = bean.getImageUrl();
        String name = bean.getName();
        ((ViewHolder) viewHolder).tv.setText(name);
        Glide.with(context).load(imageUrl).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(((ViewHolder) viewHolder).iv);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_home_release)
        LinearLayout ll;

        @BindView(R.id.iv_home_release)
        ImageView iv;
        @BindView(R.id.tv_home_release)
        TextView tv;
        @BindView(R.id.bt_item_release)
        Button bt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
