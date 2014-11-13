package com.zhu.lazytemp.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.zhu.lazytemp.R;

/**
 * Created by zhudeshuai on 2014/11/13.
 */
public class TestLinearLayoutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_linearlayot);
    }

    /**
     * 启动该页面
     * @param context
     */
    public static void startActivity(Context context){
        context.startActivity(new Intent(context,TestLinearLayoutActivity.class));
    }
}
