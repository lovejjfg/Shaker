package com.lovejjfg.shake;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joe on 2018/6/1.
 * Email: lovejjfg@gmail.com
 */
class SupportFragmentHandler implements FragmentsHandler {

    private FragmentManager manager;
    private StringBuilder sb;

    @Override
    public boolean canHandleFragment(Activity context) {
        try {
            boolean canHandle =
                context instanceof FragmentActivity && ((FragmentActivity) context).getSupportFragmentManager()
                    .getFragments().size() > 0;

            if (canHandle) {
                manager = ((FragmentActivity) context).getSupportFragmentManager();
                sb = new StringBuilder();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CharSequence handleFragment(Activity context, List<Class> ignoreClazz) {
        List<Fragment> topFragments = getSupportTopFragments();
        //no fragment
        if (topFragments == null) {
            sb.append(context.getClass().getSimpleName());
            return sb;
        }
        ArrayList<Fragment> names;
        for (Fragment topFragment : topFragments) {
            String fragmentName = topFragment.getClass().getName();
            //Glide ignore
            if (fragmentName.contains(GLIDE_FRAGMENT)) {
                continue;
            }
            //RxPermission ignore
            if (fragmentName.contains(RXPERMISSION_FRAGMENT)) {
                continue;
            }
            //add activity name first
            sb.append(context.getClass().getSimpleName());
            names = new ArrayList<>();
            while (topFragment != null) {
                names.add(0, topFragment);
                topFragment = topFragment.getParentFragment();
            }
            int length = names.size();
            for (int i = 0; i < length; i++) {
                Fragment name = names.get(i);
                if (ignoreClazz != null && ignoreClazz.contains(name.getClass())) {
                    continue;
                }
                sb.append("\n");
                for (int j = 0; j <= i; j++) {
                    sb.append(" ");
                }
                sb.append(">");
                sb.append(name.getClass().getSimpleName());
            }
            sb.append("\n\n");
        }
        if (sb.length() == 0) {
            sb.append(context.getClass().getSimpleName());
        }
        return sb;
    }

    @Nullable
    private List<Fragment> getSupportTopFragments() {
        if (manager == null) {
            return null;
        }
        List<Fragment> fragments = manager.getFragments();
        List<Fragment> topFragments = new ArrayList<>();
        if (fragments == null || fragments.isEmpty()) {
            return null;
        }
        int size = fragments.size();
        for (int i = size - 1; i >= 0; i--) {
            Fragment f = fragments.get(i);
            if (f != null && f.isAdded() && !f.isHidden() && f.getUserVisibleHint()) {
                Fragment t = getSupportTopFragment(f.getChildFragmentManager());
                if (t != null) {
                    topFragments.add(t);
                } else {
                    topFragments.add(f);
                }
            }
        }
        return topFragments;
    }

    @Nullable
    private Fragment getSupportTopFragment(FragmentManager manager) {
        List<Fragment> fragments = manager.getFragments();
        if (fragments == null) {
            return null;
        }
        int size = fragments.size();
        for (int i = size - 1; i >= 0; i--) {
            Fragment f = fragments.get(i);
            if (f.isAdded() && !f.isHidden() && f.getUserVisibleHint()) {
                Fragment tTopFragment = getSupportTopFragment(f.getChildFragmentManager());
                return tTopFragment == null ? f : tTopFragment;
            }
        }
        return null;
    }
}
