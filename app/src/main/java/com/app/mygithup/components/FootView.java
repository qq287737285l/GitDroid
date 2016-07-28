package com.app.mygithup.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.mygithup.R;
import com.app.mygithup.commons.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/28.
 * 实现上拉功能的视图
 */
public class FootView extends FrameLayout {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.tv_error)
    TextView tvError;

    private static final int STATE_LOADING = 0;
    private static final int STATE_COMPLETE = 1;
    private static final int STATE_ERROR= 2;

    private int state = STATE_LOADING;
    public FootView(Context context) {
        super(context);
        init();
        LogUtils.e("foot2");
    }
    private void init() {
        LogUtils.e("foot1");
        LayoutInflater.from(getContext()).inflate(R.layout.content_load_footer, this, true);
        ButterKnife.bind(this);
    }
    //判断是否正在加载中
    public boolean isLoading(){
        return state == STATE_LOADING;
    }
    //判断是否加载完成
    public boolean isComplete(){
        return state == STATE_COMPLETE;
    }
    //加载中
    public void showLoading(){
        state = STATE_LOADING;
        progressBar.setVisibility(View.VISIBLE);
        tvComplete.setVisibility(View.GONE);
        tvError.setVisibility(View.GONE);
    }
    //加载完成
    public void showComplete(){
        state = STATE_COMPLETE;
        tvComplete.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        tvError.setVisibility(View.GONE);
    }
    //加载失败
    public void showError(String erroMsg){
        state = STATE_ERROR;
        tvError.setVisibility(View.VISIBLE);
        tvComplete.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }
    public void setErrorClickListener(OnClickListener onClickListener){
        tvError.setOnClickListener(onClickListener);
    }
}
