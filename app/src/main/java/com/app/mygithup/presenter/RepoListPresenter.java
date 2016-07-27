package com.app.mygithup.presenter;

import android.os.AsyncTask;

import com.app.mygithup.fragment.HotRepoCommentFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/27.
 * MVP中的P 用来获取数据 更新视图
 */
public class RepoListPresenter {
    private HotRepoCommentFragment hotRepoCommentFragment;
    private int number;

    public RepoListPresenter(HotRepoCommentFragment hotRepoCommentFragment) {
        this.hotRepoCommentFragment = hotRepoCommentFragment;
    }
    public void refresh(){
        new RefreshTask().execute();
    }
    class RefreshTask extends AsyncTask<Void,Void,Void>{

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
            for(int i=0; i<20; i++){
                datas.add("测试数据 " + (number++));
            }
            hotRepoCommentFragment.stopRefresh();
            hotRepoCommentFragment.refreshData(datas);
            hotRepoCommentFragment.showContentView();
        }
    }
}
