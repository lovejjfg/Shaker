package com.lovejjfg.shake;

import android.app.Activity;
import android.content.DialogInterface;
import android.hardware.SensorEvent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import java.util.List;

/**
 * Created by joe on 2018/5/31.
 * Email: lovejjfg@gmail.com
 */
public interface ShakerCallback {
    /**
     * When you'd like to ignore some fragments to show in the shaker dialog, you should call this method.
     *
     * @return the ignore list of Fragment.
     */
    @Nullable
    List<Class> ignoreFragments();

    /**
     * If you don't want to some Activities have the shaker ability,you may call this method to add them to the
     * disable list.
     *
     * @return the disable show shaker dialog Activities.
     */
    @Nullable
    List<Class> disableActivities();

    /**
     * If you'd like to have you own shaker dialog,you should call this method to add you layout resource id.
     *
     * @return the specified layout resource.
     */
    @LayoutRes
    int initHintViewLayout();

    /**
     * When your specified view is inflated to the dialog,this method will callback. so that you can dell with the
     * views.
     *
     * @param dialog the shaker dialog
     * @param view the view inflated by method {@link #initHintViewLayout()}
     */

    void onHintViewInflated(DialogInterface dialog, View view);

    /**
     * When the Sensor result is changed,this method will callback.
     *
     * @param event the Sensor event
     * @return return true if you want to show the shaker dialog, otherwise not.
     */
    boolean onSenseChanged(SensorEvent event);

    /**
     * If you'd like to handle the shaker dialog content by yourself, you may need to add your
     * {@link FragmentsHandler} and call this method to add to {@link ShakerHelper}
     *
     * @return yourself list of {@link FragmentsHandler}
     */

    List<FragmentsHandler> fragmentHandlers();

    /**
     * When shaker dialog is dismiss,this method will callback,so that you can do something when the dialog is dismiss.
     *
     * @param context current Activity
     * @param dialog current dialog
     */
    void onDismiss(@NonNull Activity context, DialogInterface dialog);
}
