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
import com.bw.movie.bean.MovieCommentBean;
import com.bw.movie.custom.RatingBar;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/4/25
 * Author: 王冠华
 * Description:
 */
public class DetailCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MovieCommentBean.ResultBean> list;



    public DetailCommentAdapter(Context context, List<MovieCommentBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_detail_comment, null);
        ViewHodel hodel = new ViewHodel(view);
        return hodel;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        final MovieCommentBean.ResultBean bean = list.get(i);
        String content = bean.getCommentContent();
        String headPic = bean.getCommentHeadPic();
        long commentTime = bean.getCommentTime();
        String userName = bean.getCommentUserName();
        final double score = bean.getScore();
        int greatNum = bean.getGreatNum();
        int replyNum = bean.getReplyNum();

        Date date = new Date(commentTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        ((ViewHodel) viewHolder).name.setText(userName);
        Uri uri = Uri.parse(headPic);
        ((ViewHodel) viewHolder).ivhead.setImageURI(uri);
        ((ViewHodel) viewHolder).tvcontext.setText(content);
        ((ViewHodel) viewHolder).time.setText(format);
        ((ViewHodel) viewHolder).countnum.setText("等" + replyNum + "人进行了回复");
        ((ViewHodel) viewHolder).goodnum.setText(greatNum + "");
        ((ViewHodel) viewHolder).score.setText(score+"分");
        ((ViewHodel) viewHolder).good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monGreat.setOnClick(bean.getCommentId());
                ((ViewHodel) viewHolder).good.setImageResource(R.mipmap.dianzan_2);
            }
        });
    }
    private onGreat monGreat;
    public interface onGreat{
        void setOnClick(int i);
    }
    public void onClick(onGreat onGreat){
        monGreat=onGreat;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        @BindView(R.id.star_detail)
        RatingBar starDetail;
        @BindView(R.id.tv_item_detail_comment_score)
        TextView score;
        @BindView(R.id.iv_item_detail_comment)
        SimpleDraweeView ivhead;
        @BindView(R.id.tv_item_detail_comment_name)
        TextView name;
        @BindView(R.id.tv_item_detail_comment_time)
        TextView time;
        @BindView(R.id.tv_item_detail_comment_context)
        TextView tvcontext;
        @BindView(R.id.iv_item_detail_comment_good)
        ImageView good;
        @BindView(R.id.tv_item_detail_comment_goodnum)
        TextView goodnum;
        @BindView(R.id.tv_item_detail_comment_commentcount)
        TextView countnum;

        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
