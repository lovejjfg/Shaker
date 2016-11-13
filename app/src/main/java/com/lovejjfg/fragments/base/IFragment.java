package com.lovejjfg.fragments.base;

import com.lovejjfg.fragments.Fragment9;

/**
 * Created by Joe on 2016/11/13.
 * Email lovejjfg@gmail.com
 */

public interface IFragment {
    void loadRoot(int containerViewId, BaseFragment root);

    void addToShow(BaseFragment from, BaseFragment to);

    void popTo(Class<? extends BaseFragment> target, boolean includeSelf);


}
