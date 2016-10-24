package com.example.tt.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tt.fragments.base.BaseFragment;
import com.example.tt.fragments.widget.CurveLayout;
import com.example.tt.fragments.widget.CurveView;
import com.example.tt.fragments.widget.ScrollAbleViewPager;
import com.example.tt.fragments.widget.TouchCircleView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.tt.fragments.pagetransformer.MyListView.TAG;

/**
 * Created by Joe on 2016-06-09
 * Email: lovejjfg@gmail.com
 */
public class Fragment3 extends BaseFragment implements View.OnClickListener, TouchCircleView.OnLoadingListener {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ViewPager.PageTransformer pageTransformer;
    private String[] names;
    private ArrayList<Fragment> fragments;
    private ViewPagerAdapter mAdapter;
    private int currentTop;

    public Fragment3() {
    }


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment3 newInstance(int sectionNumber) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.tab)
    TabLayout mTab;
    @Bind(R.id.view_pager)
    ScrollAbleViewPager mViewPager;

    @Bind(R.id.ts)
    CurveView mCurveView;
    //    @Bind(R.id.curve_container)
//    CurveLayout mContainer;
    @Bind(R.id.bottom_sheet)
    CurveLayout mBoottom;
    private int mCurveViewHeight;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_3, container, false);
        rootView.findViewById(R.id.tv_add).setOnClickListener(this);
        rootView.findViewById(R.id.tv_delete).setOnClickListener(this);
        rootView.findViewById(R.id.tv_clear).setOnClickListener(this);
        ButterKnife.bind(this, rootView);

//        mViewPager.setPageMarginDrawable(R.mipmap.ic_launcher);
//        pageTransformer = new TranslatePagerTransformer();
        mViewPager.setPageTransformer(false, pageTransformer);
        names = getResources().getStringArray(R.array.names);
        fragments = initFragment();
//        mCurveView.setTarget(mViewPager);
//        mContainer.addmDispatcher(mCurveView);
        mAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setScrollable(mBoottom.isExpanded());
        mTab.setupWithViewPager(mViewPager);
        ListFragment.setCurveLayout(mBoottom);
        mBoottom.registerCallback(new CurveLayout.Callbacks() {
            private int dy;

            @Override
            public void onSheetExpanded() {
                Log.e(TAG, "onSheetExpanded: ");
                mCurveView.onDispatchUp();
                mCurveView.setTranslationY(0);
                mCurveView.setTranslationY(0);
                mCurveView.setScaleX(1.f);
                mCurveView.setScaleY(1.f);
                mViewPager.setScrollable(true);
                dy = 0;
            }

            @Override
            public void onSheetNarrowed() {
                Log.e(TAG, "onSheetNarrowed: ");
                mCurveView.onDispatchUp();
                mCurveView.setTranslationY(0);
                mCurveView.setScaleX(1.f);
                mCurveView.setScaleY(1.f);
                mViewPager.setScrollable(false);
                dy = 0;
            }

            @Override
            public void onSheetPositionChanged(int sheetTop, float currentX, int dy, boolean userInteracted) {
                Log.e(TAG, "onSheetPositionChanged: " + dy);
                if (mCurveViewHeight == 0) {
                    mCurveViewHeight = mCurveView.getHeight();
                }
                if (currentTop == 0) {
                    currentTop = sheetTop;
                }
                this.dy += sheetTop - currentTop;
                currentTop = sheetTop;
                Log.e(TAG, "onSheetPositionChanged:dydydydy " + this.dy);
                Log.e(TAG, "onSheetPositionChanged:dxdxdxdxdx " + currentX);

                float fraction = 1 - sheetTop * 1.0f / mCurveViewHeight;
                Log.e(TAG, "onSheetPositionChanged: fraction:" + fraction);
                Log.e(TAG, "onSheetPositionChanged: setTranslationY:" + this.dy * 0.2f);
                if (fraction >= 0 && !mBoottom.isExpanded()) {//向上拉
                    mCurveView.setTranslationY(this.dy * 0.2f);
                } else if (fraction < 0 && !mBoottom.isExpanded()) {//向下拉
                    mCurveView.onDispatch(currentX, this.dy);
                    mCurveView.setScaleX(1 - fraction * 0.5f);
                    mCurveView.setScaleY(1 - fraction * 0.5f);
                }
            }
        });

//        setUpIndicatorWidth();
        return rootView;
    }


    @NonNull
    private ArrayList<Fragment> initFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>(8);
        fragments.add(ListFragment.newInstance(Constants.TYPE_NORMAL));
        fragments.add(ListFragment.newInstance(Constants.TYPE_BIG_IMG));
        fragments.add(ListFragment.newInstance(Constants.TYPE_NORMAL));
        fragments.add(ListFragment.newInstance(Constants.TYPE_BIG_IMG));
        fragments.add(ListFragment.newInstance(Constants.TYPE_NORMAL));
        fragments.add(ListFragment.newInstance(Constants.TYPE_BIG_IMG));
        fragments.add(ListFragment.newInstance(Constants.TYPE_NORMAL));
        fragments.add(ListFragment.newInstance(Constants.TYPE_BIG_IMG));
        return fragments;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_clear) {
            fragments.clear();
        } else if (i == R.id.tv_add) {
            ListFragment fragment1 = ListFragment.newInstance(Constants.TYPE_NORMAL);
            fragment1.setUpdate(true);
            fragments.add(0, fragment1);
        } else if (i == R.id.tv_delete) {
            ListFragment remove = (ListFragment) fragments.remove(0);
            remove.setUpdate(true);
        }
        mAdapter.setFragments(fragments);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() == fragments.size() ? fragments.size() - 1 : mViewPager.getCurrentItem());
        mTab.setupWithViewPager(mViewPager);
    }

    @Override
    public void onProgressStateChange(int state, boolean hide) {

    }

    @Override
    public void onProgressLoading() {

    }

    @Override
    public void onGoBackHome() {
        mBoottom.dismiss();
    }

//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//    private void setUpIndicatorWidth()  {
//        try {
//            Class<?> tablayout = mTab.getClass();
//            Field tabStrip = tablayout.getDeclaredField("mTabStrip");
//            tabStrip.setAccessible(true);
//            LinearLayout ll_tab= (LinearLayout) tabStrip.get(mTab);
//            for (int i = 0; i < ll_tab.getChildCount(); i++) {
//                View child = ll_tab.getChildAt(i);
//                child.setPadding(0,0,0,0);
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1);
//                child.setLayoutParams(params);
//                child.invalidate();
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//    }

}
