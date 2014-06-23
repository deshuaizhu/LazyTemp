package com.zhu.lazytemp;

import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.util.Log;

/**
 * 全局异常处理类
 * @author zhu
 * @since 2014-06-04 20:42:59
 */
public class CustomCrashHandler implements UncaughtExceptionHandler {
	
	/** TAG */
	private static final String CRASH_EXCEPTION = "crash_exception";
	/** 上下文 */
	private Context context;
	/** 系统全局异常处理类 */
	private UncaughtExceptionHandler mUncatchHandler;
	/** 单例 */
	private static CustomCrashHandler instance;
	/** 私有构造方法 */
	private CustomCrashHandler(){}
	/**
	 * 获取实例
	 * @return
	 */
	public static CustomCrashHandler getInst(){
		if(instance==null){
			synchronized (CustomCrashHandler.class) {
				if(instance==null){
					instance = new CustomCrashHandler();
				}
			}
		}
		return instance;
	}
	/**
	 * 初始化自定义异常处理
	 * @param context
	 */
	public void init(Context context){
		this.context = context;
		mUncatchHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}
	
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		
		handleException(ex);
	}
	/**
	 * 处理异常
	 * @param ex
	 */
	private void handleException(Throwable ex) {
		if(ex!=null){
			Log.e(CRASH_EXCEPTION,ex.getLocalizedMessage());
		}
		
	}

}
