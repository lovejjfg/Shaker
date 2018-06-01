package com.lovejjfg.shake;

import android.app.Activity;
import android.content.DialogInterface;
import android.hardware.SensorEvent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import java.util.List;

/**
 * Created by joe on 2018/5/31.
 * Email: lovejjfg@gmail.com
 */
public interface ShakerCallback {
    List<Class> ignoreFragments();

    @LayoutRes
    int initHintViewLayout();

    void onHintViewInflated(DialogInterface dialog, View view);

    boolean onSenseChanged(SensorEvent event);

    List<FragmentsHandler> fragmentHandlers();

    void onDismiss(@NonNull Activity context, DialogInterface dialog);
}
