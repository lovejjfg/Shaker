package com.lovejjfg.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.lovejjfg.sview.SupportActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends SupportActivity {

    private FragmentManager manager;
    @Bind(R.id.tab1)
    TextView tv1;
    @Bind(R.id.tab2)
    TextView tv2;
    @Bind(R.id.tab3)
    TextView tv3;
    //    @Bind(R.id.rg_container)
//    RadioGroup radioGroup;
    //    @Bind(R.id.view_pager)
//    ViewPager mViewPager;
    private static final String T1 = "T1";
    private static final String T2 = "T2";
    private static final String T3 = "T3";
    private static final String CURRENT_TAG = "CURRENT_TAG";
    private static final String TAG = "MainActivity";
    private BaseFragment f1;
    private BaseFragment f2;
    private BaseFragment f3;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        manager = getSupportFragmentManager();

        Log.e(TAG, "onSaveInstanceState: 当前没有相关状态！！");
        if (savedInstanceState == null) {
            fragmentsUtil.loadRoot(R.id.fragment_container, Fragment9.newInstance());

        }


    }

    private void onClick(int checkedId) {
        switch (checkedId) {
            case R.id.tab1:
                manager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .show(f1)
                        .hide(f3)
                        .hide(f2)
                        .commit();
                break;
            case R.id.tab2:
                manager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
//                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .show(f2)
                        .hide(f1)
                        .hide(f3)
                        .commit();
                break;
            case R.id.tab3:
                manager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
//                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .show(f3)
                        .hide(f1)
                        .hide(f2)
                        .commit();
                break;
        }
    }


    @Override
    public void onClick(View v) {
        onClick(v.getId());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e(TAG, "onSaveInstanceState: 保存当前TAG");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        savedInstanceState.clear();

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        //如果加入了回退栈，那么在返回的时候会先去退栈
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        ListFragment.setCurveLayout(null);
        super.onDestroy();
    }
}
