package com.zhu.lazytemp.play;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.zhu.lazytemp.bean.MediaInfo;
/**
 * 音频播放服务
 * @author zhu
 * @since 2014-06-22 09:58:13
 */
public class MediaService extends Service implements OnPreparedListener {
	
	/** TAG */
	private static final String TAG  = "MediaService";
	/** 音频播放类 */
	private MediaPlayer mediaPlayer;
	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}
	@Override
	public void onCreate() {
		super.onCreate();
		mediaPlayer = new MediaPlayer();
	}
	/**
	 * 开始播放
	 * @param mediaInfo 音频信息
	 */
	private void play(MediaInfo mediaInfo){
		mediaPlayer.reset();
		try {
			mediaPlayer.setDataSource(mediaInfo.getUrl());
			mediaPlayer.prepareAsync();
			mediaPlayer.setOnPreparedListener(this);
		} catch (Exception e) {
			Log.e(TAG, "播放失败！");
		}
	}
	/**
	 * 暂停播放
	 */
	private void pauseOrResume(){
		if(mediaPlayer.isPlaying()){
			mediaPlayer.pause();
		}
	}
	class MyBinder extends Binder implements IMediaService{

		@Override
		public void startPlay(MediaInfo mediaInfo) {
			play(mediaInfo);
		}

		@Override
		public void pause() {
			pauseOrResume();
			
		}

		@Override
		public void stop() {
			// TODO Auto-generated method stub
			
		}
		
	}
	@Override
	public void onPrepared(MediaPlayer mp) {
		mp.start();
	}
	@Override
	public void onDestroy() {
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
			mediaPlayer.release();
		}
		super.onDestroy();
	}

}
