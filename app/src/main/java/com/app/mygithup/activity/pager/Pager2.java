package com.app.mygithup.activity.pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.app.mygithup.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by Administrator on 2016/7/26.
 */
public class Pager2 extends FrameLayout {
    private ImageView ivBubble1, ivBubble2, ivBubble3;

    public Pager2(Context context) {
        super(context);
        initUI();
    }

    private void initUI() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_2, this, true);
        ivBubble1 = (ImageView) findViewById(R.id.ivBubble1);
        ivBubble2 = (ImageView) findViewById(R.id.ivBubble2);
        ivBubble3 = (ImageView) findViewById(R.id.ivBubble3);

        //先设置不可见，为了后面让他动画显示
        ivBubble1.setVisibility(View.GONE);
        ivBubble2.setVisibility(View.GONE);
        ivBubble3.setVisibility(View.GONE);
    }

    public void showAnimation() {
        if (ivBubble1.getVisibility() != View.VISIBLE) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    ivBubble1.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.FadeInLeft).duration(500).playOn(ivBubble1);
                }
            }, 50);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    ivBubble2.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.BounceInRight).duration(500).playOn(ivBubble2);
                }
            }, 550);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    ivBubble3.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.RotateInUpLeft).duration(500).playOn(ivBubble3);
                }
            }, 1050);

        }
    }
}
