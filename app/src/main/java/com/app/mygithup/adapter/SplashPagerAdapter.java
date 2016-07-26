package com.app.mygithup.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.app.mygithup.activity.pager.Pager0;
import com.app.mygithup.activity.pager.Pager1;
import com.app.mygithup.activity.pager.Pager2;

/**
 * Created by Administrator on 2016/7/26.
 */
public class SplashPagerAdapter extends PagerAdapter {

    private View[] view;

    public SplashPagerAdapter(Context context) {
        view  = new View[]{new Pager0(context),new Pager1(context),new Pager2(context)};
    }
public View getView(int position){
    return view[position];
}
    @Override
    public int getCount() {

        return view.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view  == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(view[position],0);
        return view[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
