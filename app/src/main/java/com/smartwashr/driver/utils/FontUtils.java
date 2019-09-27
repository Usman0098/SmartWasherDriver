package com.smartwashr.driver.utils;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smartwashr.driver.SmartWashr;


/**
 * Created by zeeshan on 6/5/17.
 */

public class FontUtils {

    public static void applyTypeface(ViewGroup v, Typeface f) {

        if (v != null) {

            int vgCount = v.getChildCount();

            for (int i = 0; i < vgCount; i++) {

                if (v.getChildAt(i) == null) continue;

                if (v.getChildAt(i) instanceof ViewGroup) {

                    applyTypeface((ViewGroup) v.getChildAt(i), f);
                } else {
                    View view = v.getChildAt(i);

                    if (view instanceof TextView) {

                        ((TextView) (view)).setTypeface(f);

                    } else if (view instanceof EditText) {

                        ((EditText) (view)).setTypeface(f);

                    } else if (view instanceof Button) {

                        ((Button) (view)).setTypeface(f);
                    }
                }
            }
        }
    }


    public static void setFont(View v) {
        Typeface tf = Typeface.createFromAsset(SmartWashr.getInstance().getAssets(), "fonts/Raleway-Regular.ttf");

        if (v instanceof TextView) {
            ((TextView) v).setTypeface(tf);
        }

    }


}
