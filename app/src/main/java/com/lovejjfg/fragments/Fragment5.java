package com.lovejjfg.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.lovejjfg.sview.SupportFragment;


/**
 * Created by Joe on 2016-06-09
 * Email: lovejjfg@gmail.com
 */
public class Fragment5 extends SupportFragment implements View.OnClickListener {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String TAG = Fragment5.class.getSimpleName();
    private static final String ARG_SECTION_NUMBER = "section_number";

    public Fragment5() {
    }


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     * @param sectionNumber
     */
    public static Fragment5 newInstance(String sectionNumber) {
        Fragment5 fragment = new Fragment5();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        Log.e(TAG, "onAttach: " + TAG);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: " + TAG);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: " + tagName);
        View rootView = inflater.inflate(R.layout.fragment_5, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume: " + TAG);
        super.onResume();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart: " + TAG);
        super.onStart();
    }

    @Override
    public void onPause() {
        Log.e(TAG, "onPause: " + TAG);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: " + TAG);
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "onDestroyView: " + TAG);
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.e(TAG, "onDetach: " + TAG);
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick: fragment5" + v.getId());
    }
}
