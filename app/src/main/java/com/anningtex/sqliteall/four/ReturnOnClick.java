package com.anningtex.sqliteall.four;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author Administrator
 */
public class ReturnOnClick implements OnClickListener {
    Activity context;

    public ReturnOnClick(Context context) {
        this.context = (Activity) context;
    }

    @Override
    public void onClick(View v) {
        context.finish();
    }
}
