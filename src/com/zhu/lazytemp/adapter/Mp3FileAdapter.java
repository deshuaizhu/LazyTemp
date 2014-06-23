package com.zhu.lazytemp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhu.lazytemp.R;
import com.zhu.lazytemp.bean.MediaInfo;
/**
 * 音频文件列表适配器
 * @author zhu
 * @since 2014-06-21 23:20:43
 */
public class Mp3FileAdapter extends BaseAdapter {
	
	/** 上下文 */
	private Context context;
	/** 显示数据 */
	private ArrayList<MediaInfo> showMediaList;
	
	public Mp3FileAdapter(Context context,ArrayList<MediaInfo> showMediaList) {
		super();
		this.context = context;
		this.showMediaList = showMediaList;
	}

	@Override
	public int getCount() {
		return showMediaList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MediaInfo mediaInfo = showMediaList.get(position);
		ViewHolder holder = null;
		if(convertView == null){
			convertView = View.inflate(context, R.layout.item_mp3file_list, null);
			holder = ViewHolder.findViews(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_name.setText(TextUtils.isEmpty(mediaInfo.getArtist())?mediaInfo.getTitle():mediaInfo.getArtist()+" - "+mediaInfo.getTitle());
		holder.tv_time.setText(mediaInfo.getDuration());
		return convertView;
	}
	/**
	 * 控件缓存
	 * @author zhu
	 *
	 */
	static class ViewHolder {
		private TextView tv_name;
		private TextView tv_time;
		
		public static ViewHolder findViews(View view){
			ViewHolder holder = new ViewHolder();
			holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
			holder.tv_time = (TextView) view.findViewById(R.id.tv_time);
			return holder;
		}
	}

}
