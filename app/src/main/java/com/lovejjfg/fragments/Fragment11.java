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
public class Fragment11 extends SupportFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String TAG = "____TAG____";

    public Fragment11() {
    }


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment11 newInstance() {
        Fragment11 fragment = new Fragment11();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, "Fragment11");
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.text)
    TextView mText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_11, container, false);
        ButterKnife.bind(this, rootView);
//        mText.setTranslationY(100 * count);
        return rootView;
    }


    @OnClick(R.id.bt_next)
    public void onClick(View v) {
        Log.e(TAG, "onClick: " + v.getId());
//        addToShow(this, Fragment11.newInstance());
        popTo(Fragment10.class, true);
    }

    @Override
    public boolean finishSelf() {
        popTo(Fragment9.class, false);
        return true;
    }

}
