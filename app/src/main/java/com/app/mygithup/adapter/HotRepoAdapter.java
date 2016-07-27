package com.app.mygithup.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.mygithup.fragment.HotRepoCommentFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public class HotRepoAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> title;
    public HotRepoAdapter(FragmentManager fm) {
        super(fm);
    }
    public HotRepoAdapter(FragmentManager fm,List<Fragment> list,List<String> title) {
        super(fm);
        this.list = list;
        this.title = title;
    }

    public List<Fragment> getList() {
        return list;
    }

    public void setList(List<Fragment> list) {
        this.list = list;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
//        return list.get(position);
        return new HotRepoCommentFragment();
    }

    @Override
    public int getCount() {
//        return list.size();
        return 10;
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        return title.get(position);
        return "title"+position;
    }
}
