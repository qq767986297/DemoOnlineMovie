package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.CinemaScheduleListBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/8
 * Author: 王冠华
 * Description:
 */
public class CinemaScheduAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<CinemaScheduleListBean.ResultBean> list;


    public CinemaScheduAdapter(Context context, List<CinemaScheduleListBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_cinema_schedu, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CinemaScheduleListBean.ResultBean bean = list.get(i);
        String imageUrl = bean.getImageUrl();
        Uri uri = Uri.parse(imageUrl);
        String name = bean.getName();
        String director = bean.getDirector();
        int movieId = bean.getMovieId();
        double score = bean.getScore();
        String starring = bean.getStarring();
        ((ViewHolder)viewHolder).iv.setImageURI(uri);
        ((ViewHolder)viewHolder).director.setText("导演:"+director);
        ((ViewHolder)viewHolder).name.setText(name);
        ((ViewHolder)viewHolder).score.setText("评分:"+score+"分");
        ((ViewHolder)viewHolder).star.setText("主演:"+starring);

    }
//    public interface onClick{
//        void setClick(int );
//    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_schedu_release)
        SimpleDraweeView iv;
        @BindView(R.id.tv_item_schedu_name)
        TextView name;
        @BindView(R.id.tv_item_schedu_director)
        TextView director;
        @BindView(R.id.tv_item_schedu_star)
        TextView star;
        @BindView(R.id.tv_item_schedu_score)
        TextView score;
        @BindView(R.id.bt_item_schedu_release)
        Button bt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
