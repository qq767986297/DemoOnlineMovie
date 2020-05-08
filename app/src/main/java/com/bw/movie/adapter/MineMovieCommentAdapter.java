package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.MineMovieCommentBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/6
 * Author: 王冠华
 * Description:
 */
public class MineMovieCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MineMovieCommentBean.ResultBean> list;


    public MineMovieCommentAdapter(Context context, List<MineMovieCommentBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_mine_comment_movie, null);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MineMovieCommentBean.ResultBean bean = list.get(i);
        String imageUrl = bean.getImageUrl();
        String director = bean.getDirector();
        int score = bean.getMovieScore();
        String movieName = bean.getMovieName();
        String myCommentContent = bean.getMyCommentContent();
        long times = bean.getCommentTime();
        String starring = bean.getStarring();
        ((ViewHolder) viewHolder).name.setText(movieName);
        ((ViewHolder) viewHolder).scroe.setText("评分:" + score + "分");
        ((ViewHolder) viewHolder).director.setText("导演:" + director);
        ((ViewHolder) viewHolder).star.setText("主演:" + starring);
        Uri uri = Uri.parse(imageUrl);
        ((ViewHolder) viewHolder).iv.setImageURI(uri);
        ((ViewHolder) viewHolder).content.setText(myCommentContent);
        ((ViewHolder) viewHolder).rbScore.setText("(" + score + ")分");
        Date date = new Date(times);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = simpleDateFormat.format(date);
        ((ViewHolder) viewHolder).time.setText(format);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_mine_comment_movie)
        SimpleDraweeView iv;
        @BindView(R.id.tv_item_mine_comment_movie_name)
        TextView name;
        @BindView(R.id.tv_item_mine_comment_movie_director)
        TextView director;
        @BindView(R.id.tv_item_mine_comment_movie_star)
        TextView star;
        @BindView(R.id.tv_item_mine_comment_movie_scroe)
        TextView scroe;
        @BindView(R.id.tv_item_mine_comment_movie_content)
        TextView content;
        @BindView(R.id.rb_item_mine_movie)
        com.bw.movie.custom.RatingBar rb;
        @BindView(R.id.tv_item_mine_comment_movie_time)
        TextView time;
        @BindView(R.id.tv_rb_item_mine_comment_movie_rb_score)
        TextView rbScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
