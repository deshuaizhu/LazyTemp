package com.zhu.lazytemp;

import android.app.Application;
/**
 * LazyTempApplication
 * @author zhu
 * @since 2014-06-04 20:32:37
 */
public class LazyTempApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		//全局异常捕获
//		CustomCrashHandler.getInst().init(this);
	}
}
