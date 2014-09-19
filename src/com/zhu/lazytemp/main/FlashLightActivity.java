package com.zhu.lazytemp.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.IBinder;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import com.zhu.lazytemp.R;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 开启闪光灯
 * @author deshuaizhu
 * @since 2014-9-3 17:51:29
 */
public class FlashLightActivity extends Activity implements SurfaceHolder.Callback {
    /** 开关 */
    private ToggleButton tb_switch;
    private Camera.Parameters parameters;
    private Camera m_Camera;
    /** SurfaceView */
    private SurfaceView surfaceview;
    private SurfaceHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);
        initView();

    }

    private void init() {
        //准备工作
        if ( null == m_Camera )
        {
            m_Camera = Camera.open();
            try {
                m_Camera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initView() {
        tb_switch = (ToggleButton)findViewById(R.id.toggleButton);
        surfaceview = (SurfaceView)findViewById(R.id.sv);
        holder = surfaceview.getHolder();
        holder.addCallback(this);
        tb_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    OpenLightOn();
                }else{
                    CloseLightOff();
                }
            }
        });
    }

    private void OpenLightOn()    {
       init();
        Camera.Parameters parameters = m_Camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        m_Camera.setParameters(parameters);
        m_Camera.startPreview();
    }

    private void CloseLightOff()   {
        if ( m_Camera != null )
        {
            m_Camera.stopPreview();
            m_Camera.release();
            m_Camera = null;
        }
    }

    /**
     * 设置闪光灯的开启和关闭
     * @param isEnable
     * @author linc
     * @date 2012-3-18
     */
   /* private void setFlashlightEnabled(boolean isEnable)
    {
        try
        {
            Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
            IBinder binder = (IBinder) method.invoke(null, new Object[] { "hardware" });

            IHardwareService localhardwareservice = IHardwareService.Stub.asInterface(binder);
            localhardwareservice.setFlashlightEnabled(isEnable);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }*/

    /**
     * 开启打开闪光灯页面
     * @param context 上下文
     */
    public static void startActivity(Context context){
        context.startActivity(new Intent(context,FlashLightActivity.class));
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
       init();
       /* m_Camera.autoFocus( new Camera.AutoFocusCallback (){
            public void onAutoFocus(boolean success, Camera camera) {
            }
        });*/
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if ( m_Camera != null )
        {
            m_Camera.stopPreview();
            m_Camera.release();
            m_Camera = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(m_Camera!=null){
            m_Camera = null;
        }
    }
}
