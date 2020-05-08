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
import com.bw.movie.bean.CinemaRegionBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/7
 * Author: 王冠华
 * Description:
 */
public class CinemaRegionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<CinemaRegionBean.ResultBean> list;


    public CinemaRegionAdapter(Context context, List<CinemaRegionBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_cinema_region, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final CinemaRegionBean.ResultBean bean = list.get(i);
        String regionName = bean.getRegionName();
        final int regionId = bean.getRegionId();
        boolean region = bean.isRegion();
        ((ViewHolder) viewHolder).name.setText(regionName);
        ((ViewHolder) viewHolder).name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monClick.setOnClick(regionId);
                bean.setRegion(true);
            }
        });
        if (region == true) {
            ((ViewHolder) viewHolder).rl.setBackgroundColor(Color.parseColor("#141931"));
        } else {
            ((ViewHolder) viewHolder).rl.setBackgroundColor(Color.parseColor("#1A203F"));
        }
    }

    private onClick monClick;

    public interface onClick {
        void setOnClick(int regionId);
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
        @BindView(R.id.rl_region)
        RelativeLayout rl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
