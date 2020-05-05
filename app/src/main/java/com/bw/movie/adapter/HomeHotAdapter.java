package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
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
import butterknife.OnClick;

/**
 * Time: 2020/4/22
 * Author: 王冠华
 * Description:
 */
public class HomeHotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomeHotMovieBean.ResultBean> list;



    public HomeHotAdapter(Context context, List<HomeHotMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_home_hot, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        HomeHotMovieBean.ResultBean bean = list.get(0);
        String horizontalImage = bean.getHorizontalImage();
        String name = bean.getName();
        double score = bean.getScore();
        final int movieId = bean.getMovieId();
        ((ViewHolder) viewHolder).name.setText(name);
        ((ViewHolder) viewHolder).score.setText(score + "");
        Glide.with(context).load(horizontalImage).into(((ViewHolder) viewHolder).iv);
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        HomeHotListAdapter adapter = new HomeHotListAdapter(context, list);
        ((ViewHolder) viewHolder).rv.setLayoutManager(manager);
        ((ViewHolder) viewHolder).rv.setAdapter(adapter);
        adapter.setClick(new onClick() {
            @Override
            public void setOnClick(int position) {
                monClick.setOnClick(i);
                EventBus.getDefault().post(movieId);
            }
        });
        ((ViewHolder) viewHolder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monClick.setOnClick(i);
                EventBus.getDefault().post(movieId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private onClick monClick;
    public interface onClick{
        void setOnClick(int position);
    }
    public void setClick(onClick onClick){
        monClick=onClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_home_hot)
        LinearLayout ll;
        @BindView(R.id.iv_item_hot_wid)
        ImageView iv;
        @BindView(R.id.tv_item_hot_wid_name)
        TextView name;
        @BindView(R.id.tv_item_hot_wid_score)
        TextView score;
        @BindView(R.id.bt_item_hot)
        Button bt;
        @BindView(R.id.rv_item_hot)
        RecyclerView rv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
