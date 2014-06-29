package com.zhu.lazytemp.bean;

import java.io.Serializable;

/**
 * 音频文件信息
 * @author zhu
 * @since 2014-06-20 23:03:44
 */
public class MediaInfo implements Serializable  {
	private static final long serialVersionUID = 1L;
	/** 歌曲名 */
	private String title;
	/** 专辑名*/
	private String album;
	/** 歌手名 */
	private String artist;
	/** 歌曲存放路径 */
	private String url;
	/** 歌曲时间播放长度 */
	private String duration;
	/** 歌曲占用空间大小 */
	private int size;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
	
}
