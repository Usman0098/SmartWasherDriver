package com.smartwashr.driver.utils;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.smartwashr.driver.SmartWashr;


/**
 * Created by zeeshan on 6/16/17.
 */

public class RippleEffect {
    ;

    public static void applyRippleEffect(View v, String OverlayColor) {
        try {
            new MaterialRippleLayout(SmartWashr.getInstance()).on(v)
                    .rippleColor(Color.parseColor(OverlayColor))
                    .rippleOverlay(true)
                    .rippleAlpha(0.2f)
                    .rippleDuration(200)
                    .rippleBackground(Color.TRANSPARENT)
                    .create();
        } catch (NullPointerException ex) {

        } catch (Exception e){

        }
    }

    public static void applyRippleEffect(View v, String OverlayColor, int radius) {
        try {
            new MaterialRippleLayout(SmartWashr.getInstance()).on(v)
                    .rippleColor(Color.parseColor(OverlayColor))
                    .rippleOverlay(true)
                    .rippleAlpha(0.2f)
                    .rippleDuration(200)
                    .rippleRoundedCorners(radius)
                    .rippleBackground(Color.TRANSPARENT)
                    .create();
        } catch (NullPointerException ex) {

        }
    }


    public static void applyRippleEffectsOnLayout(ViewGroup v) {
        try {

            if (v != null) {
                int vgCount = v.getChildCount();
                for (int i = 0; i < vgCount; i++) {

                    if (v.getChildAt(i) == null) continue;

                    if (v.getChildAt(i) instanceof ViewGroup) {
                        applyRippleEffectsOnLayout((ViewGroup) v.getChildAt(i));
                    } else {
                        View view = v.getChildAt(i);
                        if (view instanceof TextView | view instanceof EditText) {
                            applyRippleEffect(view, "#bdbdbd");
                        } else if (view instanceof Button | view instanceof ImageView) {
                            applyRippleEffect(view, "#bdbdbd");
                        }

                    }

                }

            }
        } catch (NullPointerException e) {
            Log.d("RippleEfftect", " = >" + String.valueOf(e));
        }
    }
}
