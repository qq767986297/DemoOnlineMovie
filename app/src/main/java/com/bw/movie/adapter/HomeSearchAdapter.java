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
import com.bw.movie.bean.HomeSearchMovieBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/4/23
 * Author: 王冠华
 * Description:
 */
public class HomeSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomeSearchMovieBean.ResultBean> list;


    public HomeSearchAdapter(Context context, List<HomeSearchMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_home_search, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        HomeSearchMovieBean.ResultBean bean = list.get(i);
        String imageUrl = bean.getImageUrl();
        String name = bean.getName();
        String director = bean.getDirector();
        double score = bean.getScore();
        String starring = bean.getStarring();
        ((ViewHolder) viewHolder).name.setText(name);
        ((ViewHolder) viewHolder).score.setText("评分:" + score);
        ((ViewHolder) viewHolder).star.setText("主演:" + starring + " ");
        ((ViewHolder) viewHolder).director.setText("导演:" + director);
        Glide.with(context).load(imageUrl).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(((ViewHolder) viewHolder).iv);
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
        @BindView(R.id.ll_home_serach)
        LinearLayout ll;

        @BindView(R.id.iv_item_home_serach)
        ImageView iv;
        @BindView(R.id.tv_item_home_search_name)
        TextView name;
        @BindView(R.id.tv_item_home_search_director)
        TextView director;
        @BindView(R.id.tv_item_home_search_star)
        TextView star;
        @BindView(R.id.tv_item_home_search_score)
        TextView score;
        @BindView(R.id.bt_item_release)
        Button bt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
