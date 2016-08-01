package com.app.mygithup.hotrepo.repolist;

import android.os.AsyncTask;

import com.app.mygithup.commons.LogUtils;
import com.app.mygithup.hotrepo.repolist.view.RepoView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/7/27.
 * MVP中的P 用来获取数据 更新视图
 */
public class RepoCommentPresenter {
    private RepoView repoView;
    private int number;

    public RepoCommentPresenter(RepoView repoView) {
        this.repoView = repoView;
    }

    //下拉刷新
    public void refresh() {
        new RefreshTask().execute();
//        GitHubClient gitHubClient = new GitHubClient();
//        GitHubApi gitHubApi = gitHubClient.getGitHubApi();
//        Call<ResponseBody> call = gitHubApi.getRetrofitContributors();
//        call.enqueue(refreshCallBack);
    }

    private final Callback<ResponseBody> refreshCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            repoView.stopRefresh();
            if (response.isSuccessful()) {
                try {
                    ResponseBody body = response.body();
                    if (body == null) {
                        repoView.showMessage("Unknown error...");
                    } else {
                        String data = body.string();
                        LogUtils.e("拿回来的数据" + data);
                        repoView.showContentView();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                repoView.showMessage("error:" + response.code());
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            repoView.stopRefresh();
            repoView.showMessage(t.getMessage());
            repoView.showContentView();
        }
    };

    class RefreshTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayList<String> datas = new ArrayList<String>();
            for (int i = 0; i < 20; i++) {
                datas.add("测试数据 " + (number++));
            }
            repoView.stopRefresh();
            repoView.refreshData(datas);
            repoView.showContentView();
        }
    }

    //上拉加载
    public void loadMore() {
        repoView.showLoadMoreLoading();
        new LoadMoreTask().execute();
    }

    final class LoadMoreTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayList<String> datas = new ArrayList<String>();
            for (int i = 0; i < 20; i++) {
                datas.add("测试数据 " + (number++));
            }
            repoView.addMoreData(datas);
            repoView.hideLoadMore();
        }
    }
}
