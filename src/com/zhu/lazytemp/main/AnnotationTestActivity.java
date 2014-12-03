package com.zhu.lazytemp.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.zhu.lazytemp.R;
import com.zhu.lazytemp.annotation.ContentView;
import com.zhu.lazytemp.annotation.FindView;
import com.zhu.lazytemp.utils.ViewUtils;

/**
 * Created by zhudeshuai on 2014/12/3.
 *
 */
@ContentView(value = R.layout.activity_annotation)
public class AnnotationTestActivity extends Activity implements View.OnClickListener {
    @FindView(value = R.id.button)
    private Button btn;
    @FindView(value = R.id.textView)
    private TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.injectObject(this,this);
       btn.setOnClickListener(this);
    }



    public static void startActivity(Context context){
        context.startActivity(new Intent(context,AnnotationTestActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                tv.setText("可以通过注解找到控件了！");
                break;
        }
    }
}