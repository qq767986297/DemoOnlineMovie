package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.HomeReleaseMovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/4/23
 * Author: 王冠华
 * Description:
 */
public class HomeMoreReleaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomeReleaseMovieBean.ResultBean> list;



    public HomeMoreReleaseAdapter(Context context, List<HomeReleaseMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_home_more_release, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        HomeReleaseMovieBean.ResultBean bean = list.get(i);
        String imageUrl = bean.getImageUrl();
        final int movieId = bean.getMovieId();
        String name = bean.getName();
        String director = bean.getDirector();
        String starring = bean.getStarring();
        double score = bean.getScore();
        ((ViewHolder) viewHolder).name.setText(name);
        ((ViewHolder) viewHolder).director.setText("导演:" + director);
        ((ViewHolder) viewHolder).star.setText("主演:" + starring);
        ((ViewHolder) viewHolder).score.setText("评分:" + score);
        // Glide.with(context).load(imageUrl).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(((ViewHolder)viewHolder).iv);
        Uri uri = Uri.parse(imageUrl);
        ((ViewHolder) viewHolder).iv.setImageURI(uri);
        ((ViewHolder) viewHolder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monClick.setOnClick(i);
                EventBus.getDefault().post(movieId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private HomeHotAdapter.onClick monClick;

    public interface onClick {
        void setOnClick(int position);
    }

    public void setClick(HomeHotAdapter.onClick onClick) {
        monClick = onClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_home_more_release)
        LinearLayout ll;
        @BindView(R.id.iv_item_more_release)
        SimpleDraweeView iv;
        @BindView(R.id.tv_item_home_more_release_name)
        TextView name;
        @BindView(R.id.tv_item_home_more_release_director)
        TextView director;
        @BindView(R.id.tv_item_home_more_release_star)
        TextView star;
        @BindView(R.id.tv_item_home_more_release_score)
        TextView score;
        @BindView(R.id.bt_item_more_release)
        Button release;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
