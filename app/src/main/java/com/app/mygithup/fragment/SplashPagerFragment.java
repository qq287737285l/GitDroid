package com.app.mygithup.fragment;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.app.mygithup.R;
import com.app.mygithup.activity.pager.Pager2;
import com.app.mygithup.adapter.SplashPagerAdapter;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2016/7/26.
 */
public class SplashPagerFragment extends Fragment {
    private ViewPager viewPager;
    private SplashPagerAdapter adapter;
    private CircleIndicator indicator;//viewPager线下面的三个圆点
    private FrameLayout frameLayout, layoutPhone;//整个页面的布局和手机图片
    private ImageView ivPhoneFont;//字体透明0-1
    private int colorGreen, colorRed, colorYellow;//背景改变所需要的三种颜色


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        layoutPhone = (FrameLayout) view.findViewById(R.id.layoutPhone);
        ivPhoneFont = (ImageView) view.findViewById(R.id.ivPhoneFont);
        frameLayout = (FrameLayout) view.findViewById(R.id.content);

        colorGreen = getResources().getColor(R.color.colorGreen);
        colorRed = getResources().getColor(R.color.colorRed);
        colorYellow = getResources().getColor(R.color.colorYellow);

        adapter = new SplashPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(colorChange);//页面颜色改变的监听
        viewPager.addOnPageChangeListener(phoneViewChange);//手机动画的监听
    }

    private ViewPager.OnPageChangeListener colorChange = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            final ArgbEvaluator argbEvaluator = new ArgbEvaluator();
            if (position == 0) {
                int color = (int) argbEvaluator.evaluate(positionOffset, colorGreen, colorRed);
                frameLayout.setBackgroundColor(color);
            } else if (position == 1) {
                int color = (int) argbEvaluator.evaluate(positionOffset, colorRed, colorYellow);
                frameLayout.setBackgroundColor(color);
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private ViewPager.OnPageChangeListener phoneViewChange = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            if (position == 0) {
                //缩放动画
                float scale = 0.3f + 0.7f * positionOffset;//保证开始看得见0.3f,而且不移出屏幕
                layoutPhone.setScaleX(scale);
                layoutPhone.setScaleY(scale);
                //平移动画,320改为获取屏幕的宽高以及控键的宽高，不能写死数字
                //
                WindowManager wm = getActivity().getWindowManager();
                int with = wm.getDefaultDisplay().getWidth();
                int trans = (int) ((positionOffset - 1) * with * 0.3);
//                int trans = (int)(positionOffset*with*0.8);

                layoutPhone.setTranslationX(trans);
                //透明动画
                ivPhoneFont.setAlpha(positionOffset);
            } else if (position == 1) {
                layoutPhone.setTranslationX(-positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if(position == 2){
                Pager2 pager2 = (Pager2) adapter.getView(position);
                pager2.showAnimation();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
