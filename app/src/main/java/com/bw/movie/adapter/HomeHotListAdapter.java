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
import com.bw.movie.bean.HomeHotMovieBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/4/22
 * Author: 王冠华
 * Description:
 */
public class HomeHotListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomeHotMovieBean.ResultBean> list;


    public HomeHotListAdapter(Context context, List<HomeHotMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_home_hot_list, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        HomeHotMovieBean.ResultBean bean = list.get(i+1);
        String imageUrl = bean.getImageUrl();
        String name = bean.getName();
        final int movieId = bean.getMovieId();
        double score = bean.getScore();
        ((ViewHolder) viewHolder).name.setText(name);
        Glide.with(context).load(imageUrl).into(((ViewHolder) viewHolder).iv);
        ((ViewHolder) viewHolder).score.setText(score + "");
        ((ViewHolder)viewHolder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monClick.setOnClick(i);
                EventBus.getDefault().post(movieId);
            }
        });
    }
    private HomeHotAdapter.onClick monClick;
    public interface onClick{
        void setOnClick(int position);
    }
    public void setClick(HomeHotAdapter.onClick onClick){
        monClick=onClick;
    }
    @Override
    public int getItemCount() {
        return list.size()-1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_home_hot_list)
        LinearLayout ll;

        @BindView(R.id.iv_item_hot_list)
        ImageView iv;
        @BindView(R.id.tv_item_hot_list_score)
        TextView score;
        @BindView(R.id.tv_item_hot_list_name)
        TextView name;
        @BindView(R.id.bt_item_hot_list)
        Button bt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
