package com.zhu.lazytemp;

import android.app.Application;
/**
 * LazyTempApplication
 * @author zhu
 * @since 2014-06-04 20:32:37
 */
public class LazyTempApplication extends Application {
	/** Application 实例 */
	private static LazyTempApplication instance;
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		//全局异常捕获
//		CustomCrashHandler.getInst().init(this);
	}
	/**
	 * 获取Application对象，提供上下文信息
	 * @return
	 */
	public static LazyTempApplication getInstance() {
		return instance;
	}
	
}
