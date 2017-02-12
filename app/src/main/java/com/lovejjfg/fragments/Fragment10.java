package com.lovejjfg.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lovejjfg.sview.SupportFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Joe on 2016-06-09
 * Email: lovejjfg@gmail.com
 */
public class Fragment10 extends SupportFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String TAG = "____TAG____";

    public Fragment10() {
    }


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment10 newInstance() {
        Fragment10 fragment = new Fragment10();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, "Fragment10");
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.text)
    TextView mText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_10, container, false);
        ButterKnife.bind(this, rootView);
//        mText.setTranslationY(100 * count);
        return rootView;
    }


    @OnClick(R.id.bt_next)
    public void onClick(View v) {
        Log.e(TAG, "onClick: " + v.getId());
        addToShow(this, Fragment11.newInstance());
    }

}
