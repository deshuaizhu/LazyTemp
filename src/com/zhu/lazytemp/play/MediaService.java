package com.zhu.lazytemp.play;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
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
		if(mediaPlayer == null){
			mediaPlayer = new MediaPlayer();
		}
		if(mediaInfo!=null){
			mediaPlayer.reset();
			try {
				mediaPlayer.setDataSource(mediaInfo.getUrl());
				mediaPlayer.prepareAsync();
				mediaPlayer.setOnPreparedListener(this);
			} catch (Exception e) {
				Log.e(TAG, "播放失败！");
			}
		}
	}
	/**
	 * 暂停
	 */
	private void pause(){
		if(mediaPlayer!=null){
			mediaPlayer.pause();
		}
	}
	/**
	 * 继续播放
	 */
	private void resume(){
		if(mediaPlayer!=null){
			mediaPlayer.start();
		}
	}
	/**
	 * 停止播放
	 */
	private void stop(){
		if(mediaPlayer!=null){
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}
	/**
	 * 返回媒体总长度
	 * @return
	 */
	private int getDuration(){
		if(mediaPlayer!=null){
			return mediaPlayer.getDuration();
		}
		return -1;
	}
	
	/**
	 * 返回当前正在播放的位置
	 * @return
	 */
	private int getCurPosition(){
		if(mediaPlayer!=null){
			return mediaPlayer.getCurrentPosition();
		}
		return -1;
	}
	/**
	 * seek to
	 * @param pos
	 */
	private void seekToPos(int pos){
		if(mediaPlayer!=null){
			mediaPlayer.seekTo(pos);
		}
	}
	
	
	/**
	 * 数据传输介质
	 * @author DeshuaiZhu
	 *
	 */
	class MyBinder extends Binder implements IMediaService{

		@Override
		public void pause() {
			MediaService.this.pause();
		}

		@Override
		public void stop() {
			MediaService.this.stop();
		}

		@Override
		public void seekTo(int pos) {
			seekToPos(pos);
			
		}

		@Override
		public void play(MediaInfo mediaInfo) {
			
			MediaService.this.play(mediaInfo);
			
		}

		@Override
		public void resume() {
			MediaService.this.resume();
		}

		@Override
		public boolean isPlaying() {
			return mediaPlayer.isPlaying();
		}

		@Override
		public int getCurPos() {
			return MediaService.this.getCurPosition();
		}

		@Override
		public int getDur() {
			return MediaService.this.getDuration();
		}

		

	}
	@Override
	public void onPrepared(MediaPlayer mp) {
		mp.start();
	}
	@Override
	public void onDestroy() {
		if(mediaPlayer!=null){
			 if(mediaPlayer.isPlaying()){
				 mediaPlayer.stop();
			 }
			mediaPlayer.release();
		}
		super.onDestroy();
	}

}
