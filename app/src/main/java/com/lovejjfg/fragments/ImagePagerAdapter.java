package com.lovejjfg.fragments;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.lovejjfg.fragments.debug.Main3Activity;

/**
 * Created by Joe on 2016/10/11.
 * Email lovejjfg@gmail.com
 */

public class ImagePagerAdapter extends PagerAdapter {
    //        private final ArrayList<Fragment> list;
    private static final int[] imgRes = {
        R.mipmap.a,
//            R.mipmap.style_dzh,
//            R.mipmap.style_jianyue,
//            R.mipmap.style_meishi,
//            R.mipmap.style_oushi,
//            R.mipmap.style_rishi,
//            R.mipmap.style_xiandai,
//            R.mipmap.style_zhongshi
    };

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setLayoutParams(
            new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setImageResource(R.mipmap.a);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(container.getContext(), Main3Activity.class);
                container.getContext().startActivity(i);
            }
        });
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }
}
