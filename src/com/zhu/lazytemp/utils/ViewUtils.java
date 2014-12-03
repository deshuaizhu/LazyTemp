package com.zhu.lazytemp.utils;

import android.app.Activity;
import android.view.View;
import com.zhu.lazytemp.annotation.ContentView;
import com.zhu.lazytemp.annotation.FindView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 视图工具类
 * @author zhudeshuai
 * @since 2014-12-3 18:34:27
 */
public class ViewUtils {

    /**
     * activity注入工具
     * @param obj
     * @param activity
     */
	public static void injectObject(Object obj,Activity activity){

        Class<?> aClass = obj.getClass();
        if(aClass.isAnnotationPresent(ContentView.class)){
            //如果activity上存在这个注解
            ContentView annotation = aClass.getAnnotation(ContentView.class);
            int value = annotation.value();

            try {
                Method setContentView = aClass.getMethod("setContentView", int.class);
                setContentView.invoke(obj,value);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Field[] fields = aClass.getDeclaredFields();
        if(fields != null && fields.length > 0){
            for(Field field:fields){

                if(field.isAnnotationPresent(FindView.class)){
                    FindView annotation = field.getAnnotation(FindView.class);
                    int value = annotation.value();
                    View viewById = activity.findViewById(value);
                    if(viewById != null){
                        field.setAccessible(true);
                        try {
                            field.set(obj,viewById);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }



    }
}	
