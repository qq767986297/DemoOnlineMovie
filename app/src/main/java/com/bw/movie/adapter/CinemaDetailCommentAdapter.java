package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.CinemaDetailCommentBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/8
 * Author: 王冠华
 * Description:
 */
public class CinemaDetailCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<CinemaDetailCommentBean.ResultBean> list;


    public CinemaDetailCommentAdapter(Context context, List<CinemaDetailCommentBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_cinema_detail_comment, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        final CinemaDetailCommentBean.ResultBean bean = list.get(i);
        String commentContent = bean.getCommentContent();
        long commentTime = bean.getCommentTime();
        String commentHeadPic = bean.getCommentHeadPic();
       final int greatNum = bean.getGreatNum();
        String commentUserName = bean.getCommentUserName();
        final int isGreat = bean.getIsGreat();
        ((ViewHolder)viewHolder).name.setText(commentUserName);
        Uri uri = Uri.parse(commentHeadPic);
        ((ViewHolder)viewHolder).iv.setImageURI(uri);
        ((ViewHolder)viewHolder).content.setText(commentContent);
        ((ViewHolder)viewHolder).goodnum.setText("等"+greatNum+"人觉得很赞");
        Date date = new Date(commentTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        String format = simpleDateFormat.format(date);
        ((ViewHolder)viewHolder).time.setText(format);
        ((ViewHolder)viewHolder).good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点赞
                bean.setIsGreat(1);
                monClick.setOnClick(bean.getCommentId());

            }
        });
        if(isGreat==0){
            //未点赞
            ((ViewHolder)viewHolder).good.setImageResource(R.mipmap.dianzan_1);
        }else {
            //点过赞了
            ((ViewHolder)viewHolder).good.setImageResource(R.mipmap.dianzan_2);
        }
    }
    private onClick monClick;
    public interface onClick{
        void setOnClick(int commentId);
    }
    public void Click(onClick onClick){
        monClick=onClick;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_cinema_detail_conmment)
        SimpleDraweeView iv;
        @BindView(R.id.tv_item_cinema_detail_conmment_name)
        TextView name;
        @BindView(R.id.tv_item_cinema_detail_conmment_time)
        TextView time;
        @BindView(R.id.tv_item_cinema_detail_conmment_content)
        TextView content;
        @BindView(R.id.iv_item_cinema_detail_conmment_good)
        ImageView good;
        @BindView(R.id.tv_item_cinema_detail_conmment_goodnum)
        TextView goodnum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
