package com.lovejjfg.fragments.base;

/**
 * Created by Joe on 2016/11/13.
 * Email lovejjfg@gmail.com
 */

public interface IFragment {
    void loadRoot(int containerViewId, BaseFragment root);

    void addToShow(BaseFragment from, BaseFragment to);
}
