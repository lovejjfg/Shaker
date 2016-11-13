package com.lovejjfg.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lovejjfg.fragments.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Joe on 2016-06-09
 * Email: lovejjfg@gmail.com
 */
public class Fragment9 extends BaseFragment  {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String TAG = "____TAG____";

    public Fragment9() {
    }


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment9 newInstance() {
        Fragment9 fragment = new Fragment9();
        Bundle args = new Bundle();
        count++;
        args.putString(ARG_SECTION_NUMBER, "TAG" + count);
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.text)
    TextView mText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_9, container, false);
        ButterKnife.bind(this, rootView);
        mText.setText(tagName);
        return rootView;
    }


    @OnClick(R.id.bt_next)
    public void onClick(View v) {
        Log.e(TAG, "onClick: " + v.getId());
        addToShow(this,Fragment9.newInstance());
    }

}
