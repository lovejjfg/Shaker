package com.lovejjfg.fragments.base;

import android.os.Bundle;

/**
 * Created by Joe on 2016/11/8.
 * Email lovejjfg@gmail.com
 */
public interface IViewDelegate {
    void showToast(String toast);

    void showToast(int StringId);
    //显示dialog
    void showLoadingDialog(String msg);

    void closeLoadingDialog();

    void openKeyBoard();

    void closeKeyBoard();

    void customFinish();
    //直接关闭当前的Activity
    void finishActivity();

    void saveToSharedPrefs(String key, Object value);

    void saveViewData(Bundle bundle);

}
