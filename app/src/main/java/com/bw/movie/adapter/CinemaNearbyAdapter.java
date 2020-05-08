package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.CinemaNearbyBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/7
 * Author: 王冠华
 * Description:
 */
public class CinemaNearbyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<CinemaNearbyBean.ResultBean> list;


    public CinemaNearbyAdapter(Context context, List<CinemaNearbyBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_cinema_nearby, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        CinemaNearbyBean.ResultBean bean = list.get(i);
        String address = bean.getAddress();
        int distance = bean.getDistance();
        final int id = bean.getId();
        String name = bean.getName();
        String logo = bean.getLogo();
        Uri uri = Uri.parse(logo);
        ((ViewHolder) viewHolder).iv.setImageURI(uri);
        ((ViewHolder) viewHolder).name.setText(name);
        ((ViewHolder) viewHolder).km.setText(distance / 1000.0 + "km");
        ((ViewHolder) viewHolder).address.setText(address);
        ((ViewHolder) viewHolder).rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monClick.setClick(id);
            }
        });
    }

    private onClick monClick;

    public interface onClick {
        void setClick(int id);
    }

    public void Click(onClick onClick) {
        monClick = onClick;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_cinema_nearby_cinema)
        SimpleDraweeView iv;
        @BindView(R.id.tv_item_cinema_nearby_name)
        TextView name;
        @BindView(R.id.tv_item_cinema_nearby_address)
        TextView address;
        @BindView(R.id.tv_item_cinema_nearby_km)
        TextView km;
        @BindView(R.id.rl_item_cinema_nearby)
        RelativeLayout rl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
