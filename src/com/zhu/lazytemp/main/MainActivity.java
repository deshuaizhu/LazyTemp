package com.zhu.lazytemp.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.zhu.lazytemp.R;
import com.zhu.lazytemp.adapter.IndexDataAdapter;
/**
 * 首页
 * @author zhu
 * @since 2014-06-05 20:57:28
 *
 */
public class MainActivity extends Activity {
	/** 首页列表 */
	private ListView lv;
	/** 模拟数据适配器 */
	private IndexDataAdapter adapter;
	private String[] data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setBackgroundDrawable(null);
		setContentView(R.layout.activity_main);
		findViews();
		initData();
		setLisener();
	}
	
	/**
	 * 初始化数据
	 */
	private void initData() {
		data = new String[]{"自定义注解","扫描手机中音频文件","手电筒","线性布局测试"};
		adapter = new IndexDataAdapter(this,data);
		lv.setAdapter(adapter);
		
	}
	/**
	 * 设置监听器
	 */
	private void setLisener() {
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				switch (position) {
				case 0:
					//自定义注解
					
					
					break;
				case 1:
					//扫描音频文件
					startActivity(new Intent(getApplicationContext(), ScanMp3Activity.class));
					break;
				case 2:
					//手电筒(开启闪光灯)
                    FlashLightActivity.startActivity(MainActivity.this);
					break;
                case 3:
                    //线性布局测试
                    TestLinearLayoutActivity.startActivity(MainActivity.this);
                    break;
				default:
					break;
				}
			}
		});
	}
	/**
	 * 查找控件
	 */
	private void findViews() {
		lv = (ListView)findViewById(R.id.lv);
		
	}

}
