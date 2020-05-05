package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.UserFollowMovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/4/28
 * Author: 王冠华
 * Description:
 */
public class MineFollowMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<UserFollowMovieBean.ResultBean> list;

    public MineFollowMovieAdapter(Context context, List<UserFollowMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_mine_follow_movie, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        UserFollowMovieBean.ResultBean bean = list.get(i);
        String director = bean.getDirector();
        String imageUrl = bean.getImageUrl();
        int movieId = bean.getMovieId();
        double score = bean.getScore();
        String starring = bean.getStarring();
        String name = bean.getName();
        ((ViewHolder) viewHolder).name.setText(name);
        ((ViewHolder) viewHolder).director.setText("导演:"+director);
        ((ViewHolder) viewHolder).star.setText("主演:"+starring);
        ((ViewHolder) viewHolder).score.setText("评分:"+score+"分");
        Uri uri = Uri.parse(imageUrl);
        ((ViewHolder) viewHolder).iv.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_mine_movie)
        SimpleDraweeView iv;
        @BindView(R.id.tv_item_mine_movie_name)
        TextView name;
        @BindView(R.id.tv_item_mine_movie_director)
        TextView director;
        @BindView(R.id.tv_item_mine_movie_star)
        TextView star;
        @BindView(R.id.tv_item_mine_movie_score)
        TextView score;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
