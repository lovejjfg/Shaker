package com.lovejjfg.shake;

import android.app.Activity;
import java.util.List;

/**
 * Created by joe on 2018/6/1.
 * Email: lovejjfg@gmail.com
 */
public interface FragmentsHandler {
    String GLIDE_FRAGMENT = "SupportRequestManagerFragment";
    String RXPERMISSION_FRAGMENT = "RxPermissionsFragment";

    boolean canHandleFragment(Activity context);

    CharSequence handleFragment(Activity context, List<Class> ignoreClazz);
}
