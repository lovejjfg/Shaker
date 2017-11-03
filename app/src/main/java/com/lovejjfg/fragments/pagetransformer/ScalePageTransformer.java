package com.lovejjfg.fragments.pagetransformer;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;


/**
 * Created by HanHailong on 15/9/27.
 */
public class ScalePageTransformer implements ViewPager.PageTransformer {

    public static final float MAX_SCALE = 1.2f;
    public static final float MIN_SCALE = 0.6f;
    private ViewPager mViewPager;

    public ScalePageTransformer(ViewPager mViewPager) {
        this.mViewPager = mViewPager;
    }

    @Override
    public void transformPage(View view, float position) {
//        Log.e(TAG, "transformPage: " + position);
        Log.e("TAG", "pageTransform: view:" + view.toString() + ";position::" + position);

        if (position < -1) {
            position = -1;
        } else if (position > 1) {
            position = 1;
        }

        float tempScale = position < 0 ? 1 + position : 1 - position;

        float slope = (MAX_SCALE - MIN_SCALE) / 1;//0.6
        //一个公式
        float scaleValue = MIN_SCALE + tempScale * slope;
        view.setScaleX(scaleValue);
        view.setScaleY(scaleValue);
//        mViewPager.setPageMargin((int) (10*scaleValue));

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            view.getParent().requestLayout();
        }
    }
}
