package com.zhu.lazytemp.play.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.zhu.lazytemp.R;
import com.zhu.lazytemp.bean.MediaInfo;
import com.zhu.lazytemp.play.IMediaService;
import com.zhu.lazytemp.play.MediaService;
import com.zhu.lazytemp.utils.ToastUtil;
/**
 * 音频播放界面
 * @author zhu
 * @since 2014-06-24 20:42:07
 */
public class MediaPlayActivity extends Activity implements OnClickListener, OnSeekBarChangeListener {
	/** 暂停或者播放 */
	private ImageView iv_pause_play;
	/** 下一首 */
	private ImageView iv_next;
	/** 上一首 */
	private ImageView iv_pre;
	/** 返回 */
	private TextView tv_back;
	/** 标题 */
	private TextView tv_title;
	/** 音频服务连接类 */
	private MediaServerConnection conn;
	/** 音频服务接口类 */
	private IMediaService mediaService;
	/** 当前播放的媒体信息*/
	private MediaInfo mediaInfo;
	/** 播放进度条 */
	private SeekBar sk_process;
	/** 用于更新进度条的handler*/
	private Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		bindMediaService();
		findViews();
		setLisener();
		initViewData();
	}
	
	/**
	 * 初始化界面数据
	 */
	private void initViewData() {
		mediaInfo = (MediaInfo) getIntent().getSerializableExtra("mediainfo");
	}
	/**
	 * 设置控件监听
	 */
	private void setLisener() {
		
		iv_pause_play.setOnClickListener(this);
		iv_next.setOnClickListener(this);
		iv_pre.setOnClickListener(this);
		tv_back.setOnClickListener(this);
		sk_process.setOnSeekBarChangeListener(this);
	}
	/**
	 * 查找控件id
	 */
	private void findViews() {

		iv_pause_play = (ImageView) findViewById(R.id.ctrl_pause_play);
		iv_next = (ImageView) findViewById(R.id.ctrl_next);
		iv_pre = (ImageView)findViewById(R.id.ctrl_previous);
		tv_back = (TextView) findViewById(R.id.tv_left);
		tv_title = (TextView) findViewById(R.id.tv_center);
		sk_process = (SeekBar) findViewById(R.id.seeker);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ctrl_pause_play:
			//播放暂停
			if(mediaInfo!=null){
				mediaService.playOrPause(mediaInfo);
			}
			break;
		case R.id.ctrl_next:
			//下一首
			
			break;
		case R.id.ctrl_previous:
			//上一首
			
			break;
		case R.id.tv_left:
			//返回
			onBackPressed();
		default:
			break;
		}
		
	}
	
	/**
	 * 绑定音频服务
	 */
	private void bindMediaService() {
		if(conn == null){
			conn = new MediaServerConnection();
		}
		bindService(new Intent(getApplicationContext(), MediaService.class), 
					conn, 
					Context.BIND_AUTO_CREATE);
		
	}
	
	/**
	 * 音频服务连接结果回调
	 * @author zhu
	 * @since 2014-06-22 11:15:19
	 */
	private class MediaServerConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mediaService = (IMediaService) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			ToastUtil.show("绑定服务失败！");
			
		}
		
	}
	
	@Override
	protected void onDestroy() {
		if(conn!=null){
			unbindService(conn);
			conn = null;
		}
		
		super.onDestroy();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		mediaService.seekTo(progress);
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
}
