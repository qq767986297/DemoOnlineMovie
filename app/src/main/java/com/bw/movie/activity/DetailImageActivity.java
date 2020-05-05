package com.bw.movie.activity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.photodraweeview.OnPhotoTapListener;
import me.relex.photodraweeview.PhotoDraweeView;

public class DetailImageActivity extends BaseActivity {


    @BindView(R.id.photoview)
    PhotoDraweeView iv;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detail_image;
    }

    @Override
    protected void initView() {
        iv.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String img = intent.getStringExtra("img");
        if(!TextUtils.isEmpty(img)){
            PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
            //设置图片
            controller.setUri(img);
            controller.setOldController(iv.getController());
            controller.setControllerListener(new BaseControllerListener<ImageInfo>(){
                @Override
                public void onFinalImageSet(String id,  ImageInfo imageInfo, Animatable animatable) {
                    super.onFinalImageSet(id, imageInfo, animatable);
                    if(imageInfo==null||iv==null){
                        return;
                    }
                    iv.update(imageInfo.getWidth(),imageInfo.getHeight());
                }
            });
            iv.setController(controller.build());
        }else {
            Toast.makeText(this, "图片加载失败", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
