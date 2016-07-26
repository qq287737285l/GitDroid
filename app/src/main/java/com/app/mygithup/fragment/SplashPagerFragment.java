package com.app.mygithup.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.mygithup.R;
import com.app.mygithup.adapter.SplashPagerAdapter;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2016/7/26.
 */
public class SplashPagerFragment extends Fragment {
    private ViewPager viewPager;
    private SplashPagerAdapter adapter;
    private CircleIndicator indicator;

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
        adapter = new SplashPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);

    }


}
