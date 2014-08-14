package com.zhu.lazytemp.play;

import com.zhu.lazytemp.bean.MediaInfo;

/**
 * 音频服务提供的方法
 * @author zhu
 * @since 2014-06-22 11:00:08
 */
public interface IMediaService {
	/**
	 * 开始播放
	 */
	void play(MediaInfo mediaInfo);
	/**
	 * 暂停播放
	 */
	void pause();
	
	/**
	 * 继续播放
	 */
	void resume();
	/**
	 * 停止播放
	 */
	void stop();
	/**
	 * 判断是否正在播放
	 * @return
	 */
	boolean isPlaying();
	
	/**
	 * 获取当前位置
	 * @return
	 */
	int getCurPos();
	/**
	 * 获取总长度
	 * @return
	 */
	int getDur();
	/**
	 * seekto
	 */
	void seekTo(int pos);
}
