package com.zhu.lazytemp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
/**
 * 自动滚动TextView
 * @author zhu
 * @since 2014-06-22 00:31:02
 */
public class ScollerTextView extends TextView {

	public ScollerTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ScollerTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ScollerTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isFocused() {
		return true;
	}
	
}
