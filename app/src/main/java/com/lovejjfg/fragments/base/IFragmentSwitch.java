package com.lovejjfg.fragments.base;

import android.os.Bundle;

/**
 * Created by Joe on 2016/11/8.
 * Email lovejjfg@gmail.com
 */

/*package*/ interface IFragmentSwitch {

    /**
     * 关闭当前碎片
     */
    void switchFragment(Class<? extends IViewDelegate> fragCls, Bundle bundle);

    /**
     * 跳转至指定碎片清空之上的碎片
     */
    void customFinish();

    /**
     * 查询在栈内有没有
     */
    void clearTopChangeFragment();//int fragmentParam, final Bundle bundle, boolean isUpdateReturn);

    /**
     * 获得栈顶SupportFragment
     */
    BaseFragment getTopFragment();

//    boolean hasFragmentAddPop(String fragmentName);
}
