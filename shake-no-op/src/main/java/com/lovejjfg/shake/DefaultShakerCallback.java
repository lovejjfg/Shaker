package com.lovejjfg.shake;

import android.app.Activity;
import android.content.DialogInterface;
import android.hardware.SensorEvent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import java.util.List;

/**
 * Created by joe on 2018/6/5.
 * Email: lovejjfg@gmail.com
 */
public abstract class DefaultShakerCallback implements ShakerCallback {
    @Nullable
    @Override
    public List<Class> ignoreFragments() {
        return null;
    }

    @Override
    public int initHintViewLayout() {
        return 0;
    }

    @Nullable
    @Override
    public List<Class> disableActivities() {
        return null;
    }

    @Override
    public void onHintViewInflated(DialogInterface dialog, View view) {

    }

    @Override
    public boolean onSenseChanged(SensorEvent event) {
        return false;
    }

    @Override
    public List<FragmentsHandler> fragmentHandlers() {
        return null;
    }

    @Override
    public void onDismiss(@NonNull Activity context, DialogInterface dialog) {

    }
}
