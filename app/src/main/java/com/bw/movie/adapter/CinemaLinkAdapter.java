package com.bw.movie.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.CinemaLinkBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/7
 * Author: 王冠华
 * Description:
 */
public class CinemaLinkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<CinemaLinkBean.ResultBean> list;



    public CinemaLinkAdapter(Context context, List<CinemaLinkBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_cinema_cinema, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final CinemaLinkBean.ResultBean bean = list.get(i);
        boolean link = bean.isLink();
        final int id = bean.getId();
        ((ViewHolder) viewHolder).name.setText(bean.getName());
        ((ViewHolder) viewHolder).rlLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setLink(true);
                monClick.setOnClick(id);
            }
        });
        if(link==true){
            ((ViewHolder) viewHolder).rlLink.setBackgroundColor(Color.parseColor("#141931"));
        }else {
            ((ViewHolder) viewHolder).rlLink.setBackgroundColor(Color.parseColor("#171D3A"));
        }
    }
    private onClick monClick;

    public interface onClick {
        void setOnClick(int id);
    }

    public void Click(onClick onClick) {
        monClick = onClick;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_link_name)
        TextView name;
        @BindView(R.id.rl_link)
        RelativeLayout rlLink;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
