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
import com.bw.movie.bean.FindCinemasInfoByRegion;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/8
 * Author: 王冠华
 * Description:
 */
public class SelectCinemaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<FindCinemasInfoByRegion.ResultBean> list;


    public SelectCinemaAdapter(Context context, List<FindCinemasInfoByRegion.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_select_cinema, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        FindCinemasInfoByRegion.ResultBean bean = list.get(i);
        String address = bean.getAddress();
        String logo = bean.getLogo();
        Uri uri = Uri.parse(logo);
        String name = bean.getName();
        final int cinemaId = bean.getCinemaId();
        ((ViewHolder) viewHolder).iv.setImageURI(uri);
        ((ViewHolder) viewHolder).name.setText(name);
        ((ViewHolder) viewHolder).address.setText(address);

        ((ViewHolder) viewHolder).rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monClick!=null){
                    monClick.setClick(cinemaId);
                }
            }
        });
    }
    private onClick monClick;
    public interface onClick{
        void setClick(int cinemaId);
    }
    public void Click(onClick onClick){
        monClick=onClick;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_select_cinema)
        SimpleDraweeView iv;
        @BindView(R.id.tv_item_select_cinema_name)
        TextView name;
        @BindView(R.id.tv_item_select_cinema_address)
        TextView address;
        @BindView(R.id.rl_item_select_cinema)
        RelativeLayout rl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
