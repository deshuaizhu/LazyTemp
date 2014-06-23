package com.zhu.lazytemp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhu.lazytemp.R;
/**
 * 首页模拟数据适配器
 * @author zhu
 * @since 2014-06-05 20:55:09
 */
public class IndexDataAdapter extends BaseAdapter {
	
	/** 要展示的数据 */
	private String[] mData;
	/** 上下文 */
	private Context mContext;
	
	public IndexDataAdapter(Context context,String[] data) {
		this.mContext = context;
		this.mData = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = View.inflate(mContext, R.layout.item_index_list, null);
		((TextView)convertView.findViewById(R.id.main_tv_title)).setText(mData[position]);
		return convertView;
	}

}
