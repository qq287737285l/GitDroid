package com.app.mygithup.hotrepo.repolist.view;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface RepoLoadMoreView {
    void showLoadMoreLoading();

    void hideLoadMore();

    void showLoadMoreErro(String erroMsg);

    void addMoreData(List<String> datas);
}
