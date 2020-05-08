package com.bw.movie.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Time: 2020/4/20
 * Author: 王冠华
 * Description:
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("xxx","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("xxx","onCreate");
    }

    private P presenter;
    //设置控件加载完成标识
    public boolean viewInflateFinshed;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("xxx","onCreateView");
        View view = View.inflate(getActivity(), getLayout(), null);
        ButterKnife.bind(this,view);
        presenter = initPresenter();
        initView(view);
        //控件加载完成
        viewInflateFinshed=true;
        initData();
//       doCanSee();
        return view;
    }
    //判断是否用户可见
    public void doCanSee(){
        if(getUserVisibleHint()){
            initData();
        }
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        //如果还没加载过数据并且用户切换到此fragment
//        if(viewInflateFinshed&&isVisibleToUser){
//            //加载数据
//            initData();
//        }
//    }

    public P getPresenter() {
        return presenter;
    }

    protected abstract P initPresenter();
    protected abstract int getLayout();
    protected abstract void initView(View view);
    protected abstract void initData();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("xxx","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("xxx","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("xxx","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("xxx","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("xxx","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("xxx","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.datachView();
            presenter=null;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("xxx","onDetach");
    }
}
