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
	void startPlay(MediaInfo mediaInfo);
	/**
	 * 暂停播放
	 */
	void pause();
	/**
	 * 停止播放
	 */
	void stop();
}
