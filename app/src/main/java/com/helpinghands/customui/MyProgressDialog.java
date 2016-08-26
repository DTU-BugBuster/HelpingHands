package com.helpinghands.customui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.helpinghands.R;
import com.helpinghands.utils.Logger;

/**
 * Created by aloy on 8/26/2016.
 */
public class MyProgressDialog extends LinearLayout{

    private View rootview;
    private ProgressBar progressBar;
    private TextView title;
    private String progressTitleText;

    public MyProgressDialog(Context context) {
        super(context);
        init(context);
    }

    public MyProgressDialog(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MyProgressDialog,
                0, 0);

        try {
            progressTitleText = a.getString(R.styleable.MyProgressDialog_loadingTitle);
        } finally {
            init(context);
            a.recycle();
        }
    }

    /*public String getProgressTitleText() {
        return progressTitleText;
    }

    public void setProgressTitleText(String progressTitleText) {
        this.progressTitleText = progressTitleText;
        invalidate();
        requestLayout();
    }*/

    private void init(Context context) {
        //do setup work here

        rootview = inflate(context, R.layout.my_progress_dialog, this);
        progressBar=(ProgressBar)rootview.findViewById(R.id.my_progress_bar);
        title = (TextView)rootview.findViewById(R.id.my_title);
        Logger.d("progressTitleText",""+progressTitleText);
        title.setText(progressTitleText);


    }


}
