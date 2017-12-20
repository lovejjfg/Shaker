package com.lovejjfg.fragments.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Joe on 2017/4/13.
 * Email lovejjfg@gmail.com
 */

public class FixedViewPager extends ViewPager {
    private ArrayList<Integer> childCenterXAbs = new ArrayList<>();
    private SparseIntArray childIndex = new SparseIntArray();

    public FixedViewPager(Context context) {
        super(context);
    }

    public FixedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //https://github.com/pinguo-zhouwei/MZBannerView/blob/master/mzbanner/src/main/java/com/zhouwei/mzbanner/CustomViewPager.java
    @Override
    protected int getChildDrawingOrder(int childCount, int n) {
        if (n == 0 || childIndex.size() != childCount) {
            childCenterXAbs.clear();
            childIndex.clear();
            int viewCenterX = getViewCenterX(this);
            for (int i = 0; i < childCount; ++i) {
                int indexAbs = Math.abs(viewCenterX - getViewCenterX(getChildAt(i)));
                //两个距离相同，后来的那个做自增，从而保持abs不同
                if (childIndex.get(indexAbs) != 0) {
                    ++indexAbs;
                }
                childCenterXAbs.add(indexAbs);
                childIndex.append(indexAbs, i);
            }
            Collections.sort(childCenterXAbs);//1,0,2  0,1,2
        }
        //那个item距离中心点远一些，就先draw它。（最近的就是中间放大的item,最后draw）
        return childIndex.get(childCenterXAbs.get(childCount - 1 - n));
    }

    private int getViewCenterX(View view) {
        int[] array = new int[2];
        view.getLocationOnScreen(array);
        return array[0] + view.getWidth() / 2;
    }
}
