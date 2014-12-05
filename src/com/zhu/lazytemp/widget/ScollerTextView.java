package com.zhu.lazytemp.widget;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;
import android.widget.TextView;

public class ScollerTextView extends TextView {

	public ScollerTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ScollerTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ScollerTextView(Context context) {
		super(context);
		init();
	}
	
	@Override
	@ExportedProperty(category = "focus")
	public boolean isFocused() {
		return true;
	}
	
	
	private void init() {
		setSelected(true);
		setSingleLine(true);
		setEllipsize(TruncateAt.MARQUEE);
	}

	

}
