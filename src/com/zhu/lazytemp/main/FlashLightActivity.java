package com.zhu.lazytemp.main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import com.zhu.lazytemp.R;
import com.zhu.lazytemp.utils.ToastUtil;

import java.io.IOException;

/**
 * 开启闪光灯
 * @author deshuaizhu
 * @since 2014-9-3 17:51:29
 */
public class FlashLightActivity extends Activity{
    /** 开关 */
    private ToggleButton tb_switch;
    private Camera m_Camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);
        init();
        initView();

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void init() {
        //准备工作
        if ( null == m_Camera )
        {
            m_Camera = Camera.open();
            try {
                //Android 4.4 必须要加上这样一句才可以打开闪光灯
                m_Camera.setPreviewTexture(new SurfaceTexture(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 检查设备是否有闪光灯
     * @return
     */
    private boolean checkCameraHardware() {
        boolean result = false;
        PackageManager pm = getPackageManager();
        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)
                && pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            // this device has a camera and flash
            result = true;
        }
        return result;
    }

    private void initView() {
        tb_switch = (ToggleButton)findViewById(R.id.toggleButton);
        tb_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!checkCameraHardware()){
                    ToastUtil.show("该设备没有闪光灯！");
                    return;
                }
                if(b){
                    OpenLightOn();
                }else{
                    CloseLightOff();
                }
            }
        });
    }

    private void OpenLightOn()    {
        Camera.Parameters parameters = m_Camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        m_Camera.setParameters(parameters);
        m_Camera.startPreview();
    }

    private void CloseLightOff()   {
        Camera.Parameters parameters = m_Camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        m_Camera.setParameters(parameters);
        m_Camera.startPreview();
    }

    /**
     * 开启打开闪光灯页面
     * @param context 上下文
     */
    public static void startActivity(Context context){
        context.startActivity(new Intent(context,FlashLightActivity.class));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(m_Camera!=null){
            m_Camera.release();
            m_Camera = null;
        }
    }
}
