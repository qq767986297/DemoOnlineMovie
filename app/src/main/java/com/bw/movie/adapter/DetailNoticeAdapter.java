package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bw.movie.R;
import com.bw.movie.bean.MovieDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/4/25
 * Author: 王冠华
 * Description:
 */
public class DetailNoticeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MovieDetailBean.ResultBean.ShortFilmListBean> list;


    public DetailNoticeAdapter(Context context, List<MovieDetailBean.ResultBean.ShortFilmListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_detail_notice, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        MovieDetailBean.ResultBean.ShortFilmListBean bean = list.get(i);
        String videoUrl = bean.getVideoUrl();
        MediaController controller = new MediaController(context);
        Uri uri = Uri.parse(videoUrl);
        ((ViewHolder)viewHolder).vv.setVideoURI(uri);
        controller.setMediaPlayer(((ViewHolder)viewHolder).vv);
        ((ViewHolder)viewHolder).vv.setMediaController(controller);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vv_item_detail_notice)
        VideoView vv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
