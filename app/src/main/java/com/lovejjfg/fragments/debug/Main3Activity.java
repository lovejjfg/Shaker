package com.lovejjfg.fragments.debug;

import android.app.Activity;
import android.os.Bundle;
import com.lovejjfg.fragments.R;
import com.lovejjfg.shake.Shaker;
import com.lovejjfg.shake.ShakerHelper;

public class Main3Activity extends Activity {

    private Shaker shakeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        shakeHelper = ShakerHelper.instance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        shakeHelper.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        shakeHelper.onStop();
    }
}
