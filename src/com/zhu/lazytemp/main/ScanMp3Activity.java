package com.zhu.lazytemp.main;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhu.lazytemp.R;
import com.zhu.lazytemp.adapter.Mp3FileAdapter;
import com.zhu.lazytemp.bean.MediaInfo;
import com.zhu.lazytemp.play.IMediaService;
import com.zhu.lazytemp.play.MediaService;
import com.zhu.lazytemp.play.activity.MediaPlayActivity;
/**
 * 扫描手机中的音频文件
 * @author zhu
 * @since 2014-06-20 22:16:57
 *
 */
public class ScanMp3Activity extends Activity implements OnClickListener, OnItemClickListener {
	/** 标题 */
	private TextView tv_title;
	/** 返回 */
	private TextView tv_back;
	/** 显示列表 */
	private ListView lv_show;
	/** 显示的数据 */
	private ArrayList<MediaInfo> mediaList;
	/** 音频列表适配器 */
	private Mp3FileAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scanmp3);
		findViews();
		initData();
		
	}
	/**
	 * 初始化数据
	 */
	private void initData() {
		if(mediaList == null){
			mediaList = new ArrayList<MediaInfo>();
		}
		adapter = new Mp3FileAdapter(getApplicationContext(), mediaList);
		lv_show.setAdapter(adapter);
		lv_show.setOnItemClickListener(this);
		getAudioListFromSDCard();
	}
	/**
	 * 初始化控件
	 */
	private void findViews() {
		tv_title = (TextView)findViewById(R.id.tv_center);
		tv_back = (TextView)findViewById(R.id.tv_left);
		lv_show = (ListView)findViewById(R.id.lv_showmp3);
		tv_title.setText("音频列表");
		tv_back.setOnClickListener(this);
	}
	
	/**
	 * 获取歌曲列表
	 */
	private void getAudioListFromSDCard() {
		mediaList.clear();
		String str[] = { MediaStore.Audio.Media._ID,
				MediaStore.Audio.Media.DISPLAY_NAME,
				MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.DURATION,
				MediaStore.Audio.Media.DATE_MODIFIED };
		Cursor cursor = null;
		try{
			 cursor = getContentResolver().query(
					MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
					str,
					MediaStore.Audio.Media.MIME_TYPE + "=? or "
							+ MediaStore.Audio.Media.MIME_TYPE + "=?",
							new String[] { "audio/mpeg", "audio/x-wav" },
//				MediaStore.Audio.Media.MIME_TYPE + "=?",
//				new String[] { "audio/mpeg"},
							MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
			while (cursor.moveToNext()) {
				MediaInfo mediaInfo = new MediaInfo();
				mediaInfo.setTitle(cursor.getString(1));
				mediaInfo.setUrl(cursor.getString(2));
				mediaInfo.setDuration(toTime(cursor.getInt(3)));
				mediaList.add(mediaInfo);
			}
		}finally{
			if(cursor!=null)
				cursor.close();
		}

	}
	
	
	/**
	 * 时间格式转换
	 * @param: time 毫秒
	 * @return hh:mm:ss
	 * */
	public String toTime(int time) {

		int second = time / 1000;
		int minute = second / 60;
		int hour = minute / 60;
		second = second - minute * 60;
		minute = minute - hour * 60;
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
	
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.tv_left:
			//返回
			onBackPressed();
			break;

		default:
			break;
		}
	
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		MediaInfo mediaInfo = mediaList.get(position);
		//跳转到播放器进行播放
		Intent intent = new Intent(getApplicationContext(), MediaPlayActivity.class);
		intent.putExtra("mediainfo", mediaInfo);
		startActivity(intent );
	}
}
