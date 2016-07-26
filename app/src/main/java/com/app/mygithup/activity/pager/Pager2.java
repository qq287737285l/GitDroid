package com.app.mygithup.activity.pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.app.mygithup.R;

/**
 * Created by Administrator on 2016/7/26.
 */
public class Pager2 extends FrameLayout {
    public Pager2(Context context) {
        super(context);
        initUI();
    }

    private void initUI() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_2,this,true);
    }
}
