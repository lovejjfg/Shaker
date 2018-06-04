package com.lovejjfg.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.lovejjfg.shake.ShakerHelper;
import com.lovejjfg.shake.Shaker;
import com.lovejjfg.sview.SupportActivity;

import butterknife.ButterKnife;


public class MainActivity extends SupportActivity {

    private static final String T1 = "T1";
    private static final String T2 = "T2";
    private static final String T3 = "T3";
    private static final String CURRENT_TAG = "CURRENT_TAG";
    private static final String TAG = "MainActivity";
    private Shaker shakeHelper;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        shakeHelper = ShakerHelper.instance(this);


        Log.e(TAG, "onSaveInstanceState: 当前没有相关状态！！");
        if (savedInstanceState == null) {
            loadRoot(R.id.fragment_container, Fragment1.newInstance(1));
//            loadRoot(R.id.container2, Fragment2.newInstance(1));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取 SensorManager 负责管理传感器
        shakeHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shakeHelper.onStop();
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

}
