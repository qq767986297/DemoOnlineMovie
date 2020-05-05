package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.MovieDetailBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/4/24
 * Author: 王冠华
 * Description:
 */
public class DetailIntroduceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MovieDetailBean.ResultBean.MovieActorBean> list;


    public DetailIntroduceAdapter(Context context, List<MovieDetailBean.ResultBean.MovieActorBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_detail_introduce, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MovieDetailBean.ResultBean.MovieActorBean bean = list.get(i);
        String name = bean.getName();
        String photo = bean.getPhoto();
        String role = bean.getRole();
        Uri uri = Uri.parse(photo);
        ((ViewHolder)viewHolder).iv.setImageURI(uri);
        ((ViewHolder)viewHolder).name.setText(name);
        ((ViewHolder)viewHolder).role.setText(role);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_detail_actor)
        SimpleDraweeView iv;
        @BindView(R.id.tv_item_detail_actor_name)
        TextView name;
        @BindView(R.id.tv_item_detail_actor_role)
        TextView role;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
