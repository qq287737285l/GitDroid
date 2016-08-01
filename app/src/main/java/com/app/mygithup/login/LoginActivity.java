package com.app.mygithup.login;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.app.mygithup.MainActivity;
import com.app.mygithup.R;
import com.app.mygithup.commons.ActivityUtils;
import com.app.mygithup.commons.LogUtils;
import com.app.mygithup.net.GitHubApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.gifImageView)
    GifImageView gifImageView;

    private ActivityUtils utils;
    private LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utils = new ActivityUtils(this);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new LoginPresenter(this);
        initwebview();
    }

    private void initwebview() {
        //清除缓存
        CookieManager manager = CookieManager.getInstance();
        manager.removeAllCookie();
        //传入加载地址
        webView.loadUrl(GitHubApi.AUTH_RUL);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.setWebChromeClient(chromeClient);
        webView.setWebViewClient(viewClient);
    }

    private WebChromeClient chromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress >= 100) {
                gifImageView.setVisibility(View.GONE);
            }
        }
    };
    private WebViewClient viewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Uri uri = Uri.parse(url);
            if (GitHubApi.CALL_BACK.equals(uri.getScheme())) {
                String code = uri.getQueryParameter("code");
                presenter.login(code);
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    };

    @Override
    public void showProgress() {
        gifImageView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showMessage(String msg) {
        utils.showToast(msg);

    }

    @Override
    public void resetWeb() {
        initwebview();
    }

    @Override
    public void navigateToMain() {
        utils.startActivity(MainActivity.class);
        LogUtils.e("跳转Main完成");
        finish();
    }
}
