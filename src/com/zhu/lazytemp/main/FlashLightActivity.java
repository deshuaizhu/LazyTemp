package com.zhu.lazytemp.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import com.zhu.lazytemp.R;

/**
 * 开启闪光灯
 * @author deshuaizhu
 * @since 2014-9-3 17:51:29
 */
public class FlashLightActivity extends Activity{
    /** 开关 */
    private ToggleButton tb_switch;
    private Camera mCamera;
    private Camera.Parameters parameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);
        initView();
        init();
    }

    private void init() {
        //准备工作
        mCamera = Camera.open();
        mCamera.startPreview();
        parameters = mCamera.getParameters();
    }

    private void initView() {
        tb_switch = (ToggleButton)findViewById(R.id.toggleButton);
        tb_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    turn_on();
                }else{
                    turn_off();
                }
            }
        });
    }

    /**
     * 关闭闪光灯
     */
    private void turn_off() {
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);//开启
        mCamera.setParameters(parameters);
    }

    /**
     * 打开闪光灯
     */
    private void turn_on() {
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        mCamera.setParameters(parameters);
    }

    /**
     * 开启打开闪光灯页面
     * @param context 上下文
     */
    public static void startActivity(Context context){
        context.startActivity(new Intent(context,FlashLightActivity.class));
    }
}
