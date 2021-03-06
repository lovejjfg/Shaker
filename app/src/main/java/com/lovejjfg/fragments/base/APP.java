package com.lovejjfg.fragments.base;

import android.app.Application;
import android.content.DialogInterface;
import android.view.View;
import com.lovejjfg.fragments.R;
import com.lovejjfg.fragments.debug.Main3Activity;
import com.lovejjfg.shake.DefaultShakerCallback;
import com.lovejjfg.shake.FragmentsHandler;
import com.lovejjfg.shake.ShakerHelper;
import com.squareup.leakcanary.LeakCanary;
import java.util.ArrayList;
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
            // You should not setCallback your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app setCallback code...
        ShakerHelper.init(this, new DefaultShakerCallback() {
            @Override
            public List<Class> disableActivities() {
                ArrayList<Class> classes = new ArrayList<>();
                classes.add(Main3Activity.class);
                return classes;
            }

            @Override
            public List<FragmentsHandler> fragmentHandlers() {
                return super.fragmentHandlers();
            }

            @Override
            public int initHintViewLayout() {
                return R.layout.dialog_shake;
            }

            @Override
            public void onHintViewInflated(final DialogInterface dialog, View view) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
