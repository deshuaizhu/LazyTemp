package com.zhu.lazytemp.main;

import com.zhu.lazytemp.R;
import com.zhu.lazytemp.annotation.ContentView;
import com.zhu.lazytemp.utils.ViewUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 滚动TextView测试页面
 * @author zhudeshuai
 * @since 2014-12-4 17:27:03
 *
 */
@ContentView(value = R.layout.activity_scroller_textview)
public class ScollerTextViewActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.injectObject(this, this);
	}
	
	public static void startActivity(Context context){
		context.startActivity(new Intent(context, ScollerTextViewActivity.class));
	}
}
