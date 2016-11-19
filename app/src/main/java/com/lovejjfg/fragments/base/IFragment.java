package com.lovejjfg.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Joe on 2016/11/13.
 * Email lovejjfg@gmail.com
 */

public interface IFragment {
    void initFragments(Bundle savedInstanceState, BaseFragment fragment);

    @Nullable
    BaseFragment getTopFragment();

    @Nullable
    BaseFragment findFragment(String className);

    void loadRoot(int containerViewId, BaseFragment root);

    void addToShow(BaseFragment from, BaseFragment to);

    boolean popTo(Class<? extends BaseFragment> target, boolean includeSelf);

    void replaceToShow(BaseFragment from, BaseFragment to);

    boolean customerFinish();

}
