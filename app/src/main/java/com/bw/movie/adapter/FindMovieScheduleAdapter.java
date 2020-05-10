package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.FindMovieScheduleBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/9
 * Author: 王冠华
 * Description:
 */
public class FindMovieScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<FindMovieScheduleBean.ResultBean> list;


    public FindMovieScheduleAdapter(Context context, List<FindMovieScheduleBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_findmovieschedule, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
       final FindMovieScheduleBean.ResultBean  bean = list.get(i);
        String beginTime = bean.getBeginTime();

        String endTime = bean.getEndTime();
        String hall = bean.getScreeningHall();
        ((ViewHolder)viewHolder).startTime.setText(beginTime);
        ((ViewHolder)viewHolder).endTime.setText(endTime);
        ((ViewHolder)viewHolder).name.setText(hall);
        ((ViewHolder)viewHolder).rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_findmovieschedu_name)
        TextView name;
        @BindView(R.id.tv_findmovieschedu_start_time)
        TextView startTime;
        @BindView(R.id.tv_findmovieschedu_end_time)
        TextView endTime;

        @BindView(R.id.rl_findmovieschedule)
        RelativeLayout rl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
