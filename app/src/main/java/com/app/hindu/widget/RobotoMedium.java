package com.app.hindu.widget;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 914893 on 1/10/15.
 */
public class RobotoMedium extends TextView {
    public RobotoMedium(Context context) {
        super(context);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),
                String.format("%s.ttf", "ionicons"));
        setTypeface(typeface);
        setPaintFlags(getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);

    }

    public RobotoMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),
                String.format("%s.ttf", "Roboto-Medium"));
        setTypeface(typeface);
        setPaintFlags(getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }

    public RobotoMedium(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
