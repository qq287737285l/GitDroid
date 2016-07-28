package com.app.mygithup.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.app.mygithup.R;
import com.app.mygithup.commons.LogUtils;
import com.app.mygithup.components.FootView;
import com.app.mygithup.myInterface.RepoView;
import com.app.mygithup.presenter.RepoCommentPresenter;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by Administrator on 2016/7/27.
 */
public class HotRepoCommentFragment extends Fragment implements RepoView {
    @BindView(R.id.lvRepos)
    ListView lvRepos;
    @BindView(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @BindView(R.id.emptyView)
    TextView emptyView;
    @BindView(R.id.errorView)
    TextView errorView;

    private ArrayAdapter<String> adapter;
    private RepoCommentPresenter presenter;
    private FootView footView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        presenter = new RepoCommentPresenter(this);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, new ArrayList<String>());
        lvRepos.setAdapter(adapter);
        initRefresh();
        LogUtils.e("1");

        initLoadMore();
        LogUtils.e("2");
    }

    private void initRefresh() {
        //记录上次刷新时间
        ptrClassicFrameLayout.setLastUpdateTimeRelateObject(this);
        //关闭时间
        ptrClassicFrameLayout.setDurationToCloseHeader(1500);
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refresh();
            }
        });
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        //设置下拉后显示的字
        header.initWithString("A new World is coming");
//        //边距左上右下
        header.setPadding(10, 60, 10, 60);
        ptrClassicFrameLayout.setHeaderView(header);
        ptrClassicFrameLayout.addPtrUIHandler(header);
        ptrClassicFrameLayout.setBackgroundResource(R.color.colorRefresh);
    }

    private void initLoadMore() {
        footView = new FootView(getContext());
        LogUtils.e("3");

        Mugen.with(lvRepos, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
                LogUtils.e("4");

                presenter.loadMore();
            }
            @Override
            public boolean isLoading() {
                LogUtils.e("5");

                return lvRepos.getFooterViewsCount() > 0 && footView.isLoading();
            }
            @Override
            public boolean hasLoadedAllItems() {
                LogUtils.e("6");

                return lvRepos.getFooterViewsCount() > 0 && footView.isComplete();
            }
        }).start();
        LogUtils.e("main");

    }

    @Override
    public void showContentView() {
        ptrClassicFrameLayout.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void stopRefresh() {
        ptrClassicFrameLayout.refreshComplete();
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void refreshData(List<String> list) {
        adapter.clear();
        adapter.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadMoreLoading() {
        LogUtils.e("7");

        if (lvRepos.getFooterViewsCount() == 0) {
            lvRepos.addFooterView(footView);
        }
        footView.showLoading();
    }

    @Override
    public void hideLoadMore() {
        LogUtils.e("8");

        lvRepos.removeFooterView(footView);
    }

    @Override
    public void showLoadMoreErro(String erroMsg) {
        if (lvRepos.getFooterViewsCount() == 0) {
            lvRepos.addFooterView(footView);
        }
        footView.showError(erroMsg);
    }

    @Override
    public void addMoreData(List<String> datas) {
        LogUtils.e("9");

        adapter.addAll(datas);
        adapter.notifyDataSetChanged();
    }
}
