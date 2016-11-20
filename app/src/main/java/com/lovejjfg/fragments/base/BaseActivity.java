package com.lovejjfg.fragments.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

    @Override
    protected void onRestart() {
        if (fragmentsUtil == null) {
            Log.e("TAG", "onRestart: 创建fragmentsUtil");
            fragmentsUtil = new FragmentsUtil(getSupportFragmentManager());
        }
        super.onRestart();
    }

    public FragmentsUtil getFragmentsUtil() {
        return fragmentsUtil;
    }

    @Override
    public void initFragments(Bundle savedInstanceState, BaseFragment fragment) {
        if (fragmentsUtil == null) {
            fragmentsUtil = new FragmentsUtil(getSupportFragmentManager());
        }
        fragmentsUtil.initFragments(savedInstanceState, fragment);
    }

    @Nullable
    @Override
    public BaseFragment getTopFragment() {
        return fragmentsUtil != null ? fragmentsUtil.getTopFragment() : null;
    }

    @Nullable
    @Override
    public BaseFragment findFragment(String className) {
        return fragmentsUtil != null ? fragmentsUtil.findFragment(className) : null;
    }

    @Override
    public void loadRoot(int containerViewId, BaseFragment root) {
        if (fragmentsUtil != null) {
            fragmentsUtil.loadRoot(containerViewId, root);
        }
    }

//    @Override
    public void loadRoots(int containerViewId, int position, BaseFragment... roots) {
        if (fragmentsUtil != null) {
            fragmentsUtil.loadRoots(containerViewId, position, roots);
        }
    }

    @Override
    public void addToShow(BaseFragment from, BaseFragment to) {
        if (fragmentsUtil != null) {
            fragmentsUtil.addToShow(from, to);
        }
    }

    @Override
    public boolean popTo(Class<? extends BaseFragment> target, boolean includeSelf) {
        return fragmentsUtil != null && fragmentsUtil.popTo(target, includeSelf);
    }

    @Override
    public void replaceToShow(BaseFragment from, BaseFragment to) {
        if (fragmentsUtil != null) {
            fragmentsUtil.replaceToShow(from, to);
        }
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
