package com.lovejjfg.fragments.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lovejjfg.fragments.utils.FragmentsUtil;

/**
 * Created by Joe on 2016/11/13.
 * Email lovejjfg@gmail.com
 */

public class BaseActivity extends AppCompatActivity implements IFragment {

    public FragmentsUtil fragmentsUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentsUtil = new FragmentsUtil(getSupportFragmentManager());
    }

    public FragmentsUtil getFragmentsUtil() {
        return fragmentsUtil;
    }

    @Override
    public void initFragments(Bundle savedInstanceState, BaseFragment fragment) {
        fragmentsUtil.initFragments(savedInstanceState, fragment);
    }

    @Nullable
    @Override
    public BaseFragment getTopFragment() {
        return fragmentsUtil.getTopFragment();
    }

    @Nullable
    @Override
    public BaseFragment findFragment(String className) {
        return fragmentsUtil.findFragment(className);
    }

    @Override
    public void loadRoot(int containerViewId, BaseFragment root) {
        fragmentsUtil.loadRoot(containerViewId, root);
    }

    @Override
    public void addToShow(BaseFragment from, BaseFragment to) {
        fragmentsUtil.addToShow(from, to);
    }

    @Override
    public boolean popTo(Class<? extends BaseFragment> target, boolean includeSelf) {
        return fragmentsUtil.popTo(target, includeSelf);
    }

    @Override
    public void replaceToShow(BaseFragment from, BaseFragment to) {
        fragmentsUtil.replaceToShow(from, to);
    }

    @Override
    public boolean customerFinish() {
        BaseFragment topFragment = getTopFragment();
        if (topFragment == null || !topFragment.customerFinish()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            } else {
                finish();
            }
            return true;
        }
        return true;

    }

    @Override
    public void onBackPressed() {
        BaseFragment topFragment = getTopFragment();
        if (topFragment == null || !topFragment.customerFinish()) {
            super.onBackPressed();
        }
    }
}
