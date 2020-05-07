package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.SystemMsgBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/5
 * Author: 王冠华
 * Description:
 */
public class MineSystemMsgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<SystemMsgBean.ResultBean> list;


    public MineSystemMsgAdapter(Context context, List<SystemMsgBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_systemmsg, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        final SystemMsgBean.ResultBean bean = list.get(i);
        String content = bean.getContent();
        long pushTime = bean.getPushTime();
        int status = bean.getStatus();
        final int id = bean.getId();

        if(status==0){
            ((ViewHolder) viewHolder).iv.setVisibility(View.VISIBLE);
        }
        String title = bean.getTitle();
        ((ViewHolder) viewHolder).content.setText(content);
        ((ViewHolder) viewHolder).title.setText(title);
        Date date = new Date(pushTime);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        ((ViewHolder) viewHolder).time.setText(format);
        ((ViewHolder) viewHolder).rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               bean.setStatus(1);
              //  EventBus.getDefault().postSticky(id);
                ((ViewHolder) viewHolder).iv.setVisibility(View.GONE);
            }
        });

    }
    private onClick monClick;
    public interface onClick{
        void setClick(int i);
    }
    public void onChange(onClick onClick){
        monClick=onClick;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_msg_status)
        SimpleDraweeView iv;
        @BindView(R.id.tv_item_msg_title)
        TextView title;
        @BindView(R.id.tv_item_msg_content)
        TextView content;
        @BindView(R.id.tv_item_msg_time)
        TextView time;
        @BindView(R.id.rl_msg)
        RelativeLayout rl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
