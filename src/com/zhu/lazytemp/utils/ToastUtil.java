package com.zhu.lazytemp.utils;

import com.zhu.lazytemp.LazyTempApplication;

import android.widget.Toast;

/**
 * Toast简单显示工具
 * @author zhu
 * @since 2014-07-24 20:51:02
 */
public class ToastUtil {
	/**
	 * 显示字符串
	 * @param showMsg
	 */
	public static void show(String showMsg){
		Toast.makeText(LazyTempApplication.getInstance(), showMsg, 0).show();
	}
}
