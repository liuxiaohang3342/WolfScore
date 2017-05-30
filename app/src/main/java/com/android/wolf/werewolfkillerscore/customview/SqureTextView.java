package com.android.wolf.werewolfkillerscore.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lxh on 2017/5/28.
 */

public class SqureTextView extends TextView {

    public SqureTextView(Context context) {
        super(context);
    }

    public SqureTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
