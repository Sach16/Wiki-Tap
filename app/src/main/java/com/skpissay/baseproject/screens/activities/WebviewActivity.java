package com.skpissay.baseproject.screens.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.skpissay.baseproject.BaseApp;
import com.skpissay.baseproject.R;
import com.skpissay.baseproject.assist.Constants;
import com.skpissay.baseproject.baseclasses.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class WebviewActivity extends BaseActivity{

    @Nullable
    @BindView(R.id.web_view)
    WebView mWebView;

    @Nullable
    @BindView(R.id.back_iv)
    ImageView backIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);
        ButterKnife.bind(this);

        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.setWebViewClient(new MyBrowser());
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        //  mWebView.getSettings().setUseWideViewPort(true);
        //  mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.setWebViewClient(new MyBrowser());
//        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        mWebView.loadUrl(String.format(BaseApp.BASE_URL+"?curid=%s", getIntent().getStringExtra(Constants.WIKIPAGE_ID)));
    }

    @Override
    protected void handleUIMessage(Message pObjMessage) {

    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }

    private class MyBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //TODO make the changes here once we get LIVE URL

            view.loadUrl(url);

            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
           // mProgressDialog.show();
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
          //  mProgressDialog.cancel();


            super.onPageFinished(view, url);
        }
    }

    @Optional
    @OnClick(R.id.back_iv)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                super.onBackPressed();
                break;
            default:
                super.onClick(view);
                break;
        }
    }
}
