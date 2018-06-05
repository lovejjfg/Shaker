/*
 * Copyright 2017 Joe.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lovejjfg.shake;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Joe on 2017/2/9.
 * Email lovejjfg@gmail.com
 */

public class ShakerHelper implements SensorEventListener, DialogInterface.OnDismissListener, Shaker {
    private AlertDialog dialog;
    private Activity context;
    private SensorManager mSensorManager;
    private static boolean isEnable = true;
    @Nullable
    private static ShakerCallback shakerCallback;
    private View customView;
    private List<FragmentsHandler> fragmentHandlers;

    private ShakerHelper(Activity context) {
        if (!isEnable) {
            return;
        }
        this.context = context;
        initHandlers();
        initDialog(context);
    }

    private void initDialog(Activity context) {
        dialog = new AlertDialog.Builder(context).create();
        customView = null;
        if (shakerCallback != null) {
            try {
                int resource = shakerCallback.initHintViewLayout();
                if (resource != 0 && resource != -1) {
                    customView = LayoutInflater.from(context)
                        .inflate(resource, (ViewGroup) context
                            .getWindow().getDecorView(), false);
                    shakerCallback.onHintViewInflated(dialog, customView);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (customView != null) {
            dialog.setView(customView);
        }
        dialog.setOnDismissListener(this);
    }

    private void initHandlers() {
        ArrayList<FragmentsHandler> list = new ArrayList<>();
        if (shakerCallback != null) {
            List<FragmentsHandler> handlers = shakerCallback.fragmentHandlers();
            if (handlers != null && !handlers.isEmpty()) {
                list.addAll(handlers);
            }
        }
        list.add(new SupportFragmentHandler());
        fragmentHandlers = Collections.unmodifiableList(list);
    }

    public static void init(boolean enable, @Nullable ShakerCallback callback) {
        isEnable = enable;
        if (enable && callback != null) {
            shakerCallback = callback;
        } else {
            shakerCallback = null;
        }
    }

    @SuppressWarnings("unused")
    public static void init(boolean enable) {
        init(enable, null);
    }

    @NonNull
    public static Shaker instance(Activity context) {
        return new ShakerHelper(context);
    }

    @Override
    public void onResume() {
        if (!isEnable) {
            return;
        }
        mSensorManager = ((SensorManager) context.getSystemService(Context.SENSOR_SERVICE));
        if (isEnable && mSensorManager != null) {
            Sensor mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (mAccelerometerSensor != null) {
                mSensorManager.registerListener(this, mAccelerometerSensor, SensorManager.SENSOR_DELAY_UI);
            }
        }
    }

    @Override
    public void onStop() {
        if (!isEnable) {
            return;
        }
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
            mSensorManager = null;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();
        if (type == Sensor.TYPE_ACCELEROMETER) {//accelerometer
            if (shakerCallback != null && shakerCallback.onSenseChanged(event)) {
                handleContent();
                return;
            }
            float[] values = event.values;
            float x = values[0];
            if (!dialog.isShowing() && (x > 18)) {
                handleContent();
            }
        }
    }

    private FragmentsHandler findFragmentHandler() {
        for (FragmentsHandler handler : fragmentHandlers) {
            if (handler.canHandleFragment(context)) {
                return handler;
            }
        }
        return null;
    }

    private void handleContent() {
        FragmentsHandler fragmentHandler = findFragmentHandler();
        CharSequence content;
        if (fragmentHandler != null) {
            content = fragmentHandler.handleFragment(context,
                shakerCallback != null ? shakerCallback.ignoreFragments() : null);
        } else {
            content = context.getClass().getSimpleName();
        }
        setContent(content);
        dialog.show();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void setContent(CharSequence charSequence) {
        if (customView != null) {
            TextView content = customView.findViewWithTag("shaker_content");
            throughOrThrow(content);
            content.setText(charSequence);
        } else {
            dialog.setMessage(charSequence);
        }
    }

    private void throughOrThrow(TextView content) {
        if (content == null) {
            throw new IllegalStateException("you must add TextView with tag 'shaker_content'");
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (isEnable && shakerCallback != null) {
            shakerCallback.onDismiss(context, dialog);
        }
    }
}
