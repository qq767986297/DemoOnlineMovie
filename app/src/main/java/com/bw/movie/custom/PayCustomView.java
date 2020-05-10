package com.bw.movie.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;

/**
 * Time: 2020/5/10
 * Author: 王冠华
 * Description:
 */
public class PayCustomView extends LinearLayout {
    public PayCustomView(Context context) {
        super(context);
        init(context);
    }

    public PayCustomView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public void init(Context context){
        View view = View.inflate(context, R.layout.custom_pay, null);
       RadioGroup rg= view.findViewById(R.id.rg);
      final RadioButton rb_wechat= view.findViewById(R.id.rb_wechat);
      final RadioButton rb_ali= view.findViewById(R.id.rb_ali);
      rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup group, int checkedId) {
              monCheck.onCheck(checkedId);
          }
      });

      addView(view);
    }
    private onCheck monCheck;
    public interface onCheck{
        void onCheck(int checkId);
    }
    public void Check(onCheck onCheck){
        monCheck=onCheck;
    }
    public void callBack(int checkId){
        monCheck.onCheck(checkId);
    }
}
