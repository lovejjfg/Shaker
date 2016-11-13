package com.lovejjfg.fragments.base;

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
    public void loadRoot(int containerViewId, BaseFragment root) {
        fragmentsUtil.loadRoot(containerViewId, root);
    }

    @Override
    public void addToShow(BaseFragment from, BaseFragment to) {
        fragmentsUtil.addToShow(from, to);
    }

    @Override
    public void popTo(Class<? extends BaseFragment> target, boolean includeSelf) {
        fragmentsUtil.popTo(target, includeSelf);
    }
}
