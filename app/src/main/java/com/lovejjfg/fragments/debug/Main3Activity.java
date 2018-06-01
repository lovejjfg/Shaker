package com.lovejjfg.fragments.debug;

import android.app.Activity;
import android.os.Bundle;
import com.lovejjfg.fragments.R;
import com.lovejjfg.shake.ShakerHelper;
import com.lovejjfg.shake.Shaker;

public class Main3Activity extends Activity {

    private Shaker shakeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        shakeHelper = ShakerHelper.init(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        shakeHelper.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shakeHelper.onPause();
    }
}
