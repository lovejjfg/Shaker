package com.lovejjfg.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.lovejjfg.sview.SupportActivity;
import com.lovejjfg.sview.SupportFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class MainActivity extends SupportActivity implements SensorEventListener, DialogInterface.OnDismissListener {

    private static final String T1 = "T1";
    private static final String T2 = "T2";
    private static final String T3 = "T3";
    private static final String CURRENT_TAG = "CURRENT_TAG";
    private static final String TAG = "MainActivity";
    private SensorManager mSensorManager;
    private Sensor mAccelerometerSensor;
    private AlertDialog dialog;
    private StringBuilder sb;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dialog = new AlertDialog.Builder(this).create();
        dialog.setOnDismissListener(this);
        sb = new StringBuilder();


        Log.e(TAG, "onSaveInstanceState: 当前没有相关状态！！");
        if (savedInstanceState == null) {
            loadRoot(R.id.fragment_container, Fragment1.newInstance(1));
            loadRoot(R.id.container2, Fragment2.newInstance(1));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //获取 SensorManager 负责管理传感器
        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        if (mSensorManager != null) {
            //获取加速度传感器
            mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (mAccelerometerSensor != null) {
                mSensorManager.registerListener(this, mAccelerometerSensor, SensorManager.SENSOR_DELAY_UI);
            }
        }
    }

    @Override
    protected void onPause() {
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
        }
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e(TAG, "onSaveInstanceState: 保存当前TAG");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        //如果加入了回退栈，那么在返回的时候会先去退栈
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();
        if (type == Sensor.TYPE_ACCELEROMETER) {
            //获取三个方向值
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            if ((Math.abs(x) > 17 || Math.abs(y) > 17 || Math
                    .abs(z) > 17) && !dialog.isShowing()) {
                List<Fragment> topFragments = getTopFragment();
                if (topFragments == null) {
                    sb.append(this.getClass().getName());
                    dialog.setMessage(sb.toString());
                    dialog.show();
                    return;
                }
                sb.append(this.getClass().getName());
                ArrayList<Fragment> names = new ArrayList<>();
                // TODO: 2017/2/8 显示有问题
                for (Fragment topFragment : topFragments) {
                    while (topFragment != null) {
                        names.add(0, topFragment);
                        topFragment = ((SupportFragment)topFragment).parentFragment;
                    }
                    for (Fragment name : names) {
                        sb.append(File.separator).append(name.getClass().getName());
                    }
                }

                dialog.setMessage(sb.toString());
                dialog.show();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        sb.delete(0, sb.length());
    }
}
