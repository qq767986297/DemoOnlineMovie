package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.AddMovieCommentBean;
import com.bw.movie.contract.IAddContract;
import com.bw.movie.presenter.AddPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMovieCommentActivity extends BaseActivity implements IAddContract.IView {


    @BindView(R.id.iv_addmoviecomment_back)
    ImageView btBack;
    @BindView(R.id.tv_add_movie_comment_name)
    TextView name;
    @BindView(R.id.tv_add_movie_comment_score)
    TextView score;
    @BindView(R.id.rb_add_movie_comment)
    RatingBar rb;
    @BindView(R.id.et_add_movie_comment_context)
    EditText context;
    @BindView(R.id.bt_add_movie_comment)
    Button bt;
    private int movieId;
    private String et;

    @Override
    protected BasePresenter initPresenter() {
        return new AddPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_add_movie_comment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        movieId = intent.getIntExtra("movieId", 0);
        String movieName = intent.getStringExtra("name");

        name.setText(movieName);
    }
    @OnClick({R.id.iv_addmoviecomment_back, R.id.bt_add_movie_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_addmoviecomment_back:
                finish();
                break;
            case R.id.bt_add_movie_comment:
                et = context.getText().toString();
              if(et!=null){
                  BasePresenter presenter = getPresenter();
                  if(presenter instanceof IAddContract.IPresenter){
                      ((IAddContract.IPresenter)presenter).getAddMovieComment(movieId,et,5.0);
                  }
              }else {
                  Toast.makeText(this, "评论内容不能为空哦", Toast.LENGTH_SHORT).show();
              }
                break;
            default:
                break;
        }
    }
    @Override
    public void onAddMovieComment(AddMovieCommentBean addMovieCommentBean) {
        String message = addMovieCommentBean.getMessage();
        if(message.equals("评论成功")){
            Toast.makeText(this, "评论成功", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "评论失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
