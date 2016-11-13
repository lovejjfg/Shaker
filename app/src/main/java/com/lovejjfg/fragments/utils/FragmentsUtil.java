package com.lovejjfg.fragments.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.lovejjfg.fragments.base.BaseFragment;

import static com.lovejjfg.fragments.base.BaseFragment.ARG_CONTAINER;

/**
 * Created by Joe on 2016/11/13.
 * Email lovejjfg@gmail.com
 */

public class FragmentsUtil {
    FragmentManager manager;

    public FragmentsUtil(FragmentManager manager) {
        this.manager = manager;
    }

    public void addToShow(BaseFragment from, BaseFragment to) {
        bindContainerId(from.getContainerId(), to);
        FragmentTransaction transaction = manager.beginTransaction();
        String tag = to.getClass().getSimpleName();
        transaction.replace(from.getContainerId(), to, tag)
                .addToBackStack(tag)
//                .hide(from)
//                .show(to)
                .commit();

    }

    public void loadRoot(int containerViewId, BaseFragment root) {
        bindContainerId(containerViewId, root);
        FragmentTransaction transaction = manager.beginTransaction();
        String tag = root.getClass().getSimpleName();
        transaction.add(containerViewId, root, tag)
                .addToBackStack(tag)
                .commit();


    }

    private void bindContainerId(int containerId, BaseFragment to) {
        Bundle args = to.getArguments();
        if (args == null) {
            args = new Bundle();
            to.setArguments(args);
        }
        args.putInt(ARG_CONTAINER, containerId);
    }

}
