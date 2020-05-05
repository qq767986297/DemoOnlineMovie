package com.bw.movie.custom;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bw.movie.R;

/**
 * Time: 2020/4/24
 * Author: 王冠华
 * Description:
 */
public class CustomMoreSerachView extends LinearLayout {
    public CustomMoreSerachView(Context context) {
        super(context);
        init(context);
    }

    public CustomMoreSerachView(Context context,AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public void init(Context context){
        View view = View.inflate(context, R.layout.custom_more_serach, null);
       EditText editText= view.findViewById(R.id.et_custom_more_search);
       editText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
        addView(view);
    }
}
