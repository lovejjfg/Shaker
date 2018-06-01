package com.lovejjfg.fragments.base;

import android.app.Activity;
import android.app.Application;
import android.content.DialogInterface;
import android.hardware.SensorEvent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import com.lovejjfg.fragments.R;
import com.lovejjfg.shake.FragmentsHandler;
import com.lovejjfg.shake.ShakerCallback;
import com.lovejjfg.shake.ShakerHelper;
import com.squareup.leakcanary.LeakCanary;
import java.util.List;

/**
 * Created by Joe on 2016/10/14.
 * Email lovejjfg@gmail.com
 */

public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
        ShakerHelper.setEnable(true, new ShakerCallback() {
            @Override
            public List<Class> ignoreFragments() {
                //ArrayList<Class> classes = new ArrayList<>();
                //classes.add(Fragment5.class);
                return null;
            }

            @Override
            public int initHintViewLayout() {
                return 0;
            }

            @Override
            public void onHintViewInflated(final DialogInterface dialogInterface, View view) {
                view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogInterface.dismiss();
                    }
                });
                view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogInterface.dismiss();
                    }
                });
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
                Log.e("TAG", "onDismiss: ......");
            }
        });
    }
}
