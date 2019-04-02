package com.proyek.rahmanjai.eatit.utils;

/**
 * Created by Rishabh Gupta on 29-03-2019
 */

import android.view.View;
import android.view.ViewGroup;


public class LoadingUtil {
    public static void enableDisableView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;

            for (int idx = 0; idx < group.getChildCount(); idx++) {
                enableDisableView(group.getChildAt(idx), enabled);
            }
        }
    }
}
