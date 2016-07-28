package com.app.mygithup.myInterface;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 * MVP中的V，用来设置视图
 */
public interface RepoCommentView {
    void showContentView();
    void showErrorView();
    void showEmptyView();
    void stopRefresh();
    void showMessage(String msg);
    void refreshData(List<String> list);
}
