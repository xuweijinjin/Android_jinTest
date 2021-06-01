package com.jinwode.jintest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jin.jinutility.jinactivity.BaseHomeActivity;
import com.jin.jinutility.jinapplication.JinDeviceMsg;
import com.jin.jinutility.jinwebview.AndroidtoJs;
import com.jinwode.jintest.utility.JinLog;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class WebViewTest extends BaseHomeActivity
{

    final int CHOOSE_PHOTO = 88;
    //一定是在后面， SetContentView 后才能进行赋值。 同类型的 尽量放到一起，这样好找些。
    @BindView(R.id.webViewMain)
    WebView webViewMain;
    @BindView(R.id.webViewSecond)
    WebView webViewSecond;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.buttonClose)
    Button buttonClose;
    @BindView(R.id.buttonOpenDialog)
    Button buttonOpenDialog;
    @BindView(R.id.buttonOpenNoraml)
    Button buttonOpenNoraml;
    private Uri uri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        uri = data.getData();
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        JinLog.i(this.toString());
        if (savedInstanceState != null)
        {
            Object obj = savedInstanceState.get("temp");
            if (obj != null)
            {
                Toast.makeText(this, obj.toString(), Toast.LENGTH_SHORT).show();

            }

        }

        JinLog.d(this.toString() + "--onCreate  了");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.constrainttest);

        //在这里进行绑定。
        ButterKnife.bind(this);

        Uri url = Uri.parse("https://www.baidu.com");

        Intent intent = getIntent();
        Uri urlGet = intent.getData();
        if (urlGet != null) url = urlGet;
        JinLog.d("接收到的网页信息： ", "host = " + url.getHost() + " path = " + url.getPath() + " query = " + url.getQuery());
        Bundle bundle = intent.getExtras();
        if (bundle != null)
        {
            String string = bundle.getString("jin");
            int i = (int) bundle.get("jinAge");
            boolean b = bundle.getBoolean("isBOY");
        }


        setWebView(webViewMain, url.toString());
        setWebView(webViewSecond, "file:///android_asset/index.html");
        String type = BuildConfig.BUILD_TYPE;


    }


    void setWebView(WebView webView, String loadUrl)
    {




        // Debug 模式下开启远程调试
        //电脑端可以弹出弹窗。android 怎么弹出。
        if (BuildConfig.DEBUG)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            {
                WebView.setWebContentsDebuggingEnabled(true);
            }
        }


        //对webView 要进行一些初始化设置。
        WebSettings webSettings = webView.getSettings();

        // 开启支持localstorage
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheMaxSize(1024*1024*8);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
      //  Toast.makeText(this,appCachePath,Toast.LENGTH_LONG).show();
        webView.getSettings().setAppCachePath(appCachePath);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);

        //支持缩放
        webSettings.setSupportZoom(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // webview 直接 与 JS交互。
        webSettings.setJavaScriptEnabled(true);
        JinDeviceMsg deviceMsg = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            deviceMsg = new JinDeviceMsg(this);
        }
        webSettings.setUserAgentString(webSettings.getUserAgentString() + " phoneInformation/" + deviceMsg.toString());
        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：name 就是你在javaScript中直接通过这个名字来调用
        webView.addJavascriptInterface(new AndroidtoJs(this), "jin");//AndroidtoJS类对象映射到js的test对象
        //设置缓存方式
        webSettings.setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);// Don't use the network, load from the  cache.
        //是否Js 能否访问本地文件夹。
        webSettings.setAllowFileAccess(true);




        webView.loadUrl(loadUrl);
        webView.setWebViewClient(new WebViewClient()
        {

            /**
             * Notify the host application that an HTTP error has been received from the server while
             * loading a resource.  HTTP errors have status codes &gt;= 400.  This callback will be called
             * for any resource (iframe, image, etc.), not just for the main page. Thus, it is recommended
             * to perform minimum required work in this callback. Note that the content of the server
             * response may not be provided within the {@code errorResponse} parameter.
             *
             * @param view          The WebView that is initiating the callback.
             * @param request       The originating request.
             * @param errorResponse Information about the error occurred.
             */
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse)
            {
                super.onReceivedHttpError(view, request, errorResponse);
            }


            /**
             * Notify the host application that the WebView will load the resource
             * specified by the given url.
             *
             * @param view The WebView that is initiating the callback.
             * @param url  The url of the resource the WebView will load.
             */
            @Override
            public void onLoadResource(WebView view, String url)
            {
                super.onLoadResource(view, url);
            }

            /**
             * Notifies the host application that the WebView received an HTTP
             * authentication request. The host application can use the supplied
             * {@link HttpAuthHandler} to set the WebView's response to the request.
             * The default behavior is to cancel the request.
             *
             * @param view    the WebView that is initiating the callback
             * @param handler the HttpAuthHandler used to set the WebView's response
             * @param host    the host requiring authentication
             * @param realm   the realm for which authentication is required
             * @see WebView#getHttpAuthUsernamePassword
             */
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm)
            {
                super.onReceivedHttpAuthRequest(view, handler, host, realm);
            }


            /**
             * Give the host application a chance to take control when a URL is about to be loaded in the
             * current WebView. If a WebViewClient is not provided, by default WebView will ask Activity
             * Manager to choose the proper handler for the URL. If a WebViewClient is provided, returning
             * {@code true} causes the current WebView to abort loading the URL, while returning
             * {@code false} causes the WebView to continue loading the URL as usual.
             *
             * <p class="note"><b>Note:</b> Do not call {@link WebView#loadUrl(String)} with the request's
             * URL and then return {@code true}. This unnecessarily cancels the current load and starts a
             * new load with the same URL. The correct way to continue loading a given URL is to simply
             * return {@code false}, without calling {@link WebView#loadUrl(String)}.
             *
             * <p class="note"><b>Note:</b> This method is not called for POST requests.
             *
             * <p class="note"><b>Note:</b> This method may be called for subframes and with non-HTTP(S)
             * schemes; calling {@link WebView#loadUrl(String)} with such a URL will fail.
             *
             * @param view    The WebView that is initiating the callback.
             * @param request Object containing the details of the request.
             * @return {@code true} to cancel the current load, otherwise return {@code false}.
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                view.loadUrl("https://dx185.com");
                return true;
            }

            /**
             * Notify the host application of a resource request and allow the
             * application to return the data.  If the return value is {@code null}, the WebView
             * will continue to load the resource as usual.  Otherwise, the return
             * response and data will be used.
             *
             * <p>This callback is invoked for a variety of URL schemes (e.g., {@code http(s):}, {@code
             * data:}, {@code file:}, etc.), not only those schemes which send requests over the network.
             * This is not called for {@code javascript:} URLs, {@code blob:} URLs, or for assets accessed
             * via {@code file:///android_asset/} or {@code file:///android_res/} URLs.
             *
             * <p>In the case of redirects, this is only called for the initial resource URL, not any
             * subsequent redirect URLs.
             *
             * <p class="note"><b>Note:</b> This method is called on a thread
             * other than the UI thread so clients should exercise caution
             * when accessing private data or the view system.
             *
             * <p class="note"><b>Note:</b> When Safe Browsing is enabled, these URLs still undergo Safe
             * Browsing checks. If this is undesired, whitelist the URL with {@link
             * WebView#setSafeBrowsingWhitelist} or ignore the warning with {@link #onSafeBrowsingHit}.
             *
             * @param view    The {@link WebView} that is requesting the
             *                resource.
             * @param request Object containing the details of the request.
             * @return A {@link WebResourceResponse} containing the
             * response information or {@code null} if the WebView should load the
             * resource itself.
             */
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request)
            {
                return super.shouldInterceptRequest(view, request);

//
//                String htmlPage = "<html>\n" +
//                        "<title>千度</title>\n" +
//                        "<body>\n" +
//                        "<a href=\"www.taobao.com\">千度</a>,比百度知道的多10倍\n" +
//                        "</body>\n" +
//                        "<html>";
//                InputStream inputStream = new WebViewClient().getLocalHtmlPageStream(htmlPage, null);
//                WebResourceResponse response = view.getWebResourceResponse(inputStream, WebViewClientInterceptor.UTF_8);
//
//                return response;
            }
        });


        //在本 webview中 加载新页面。否则就用默认的游览器app 跳出你的app了。
        //在这里重新定义的 js方法。
        //js 默认的 alert，confirm， 这些都是不支持的。 你需要在你的webView 设置 WebChromeClient 。
        webView.setWebChromeClient(new WebChromeClient()
        {


            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result)
            {

                // Activity 是context类的， webVew 不是，是mockView 。
                Toast.makeText(WebViewTest.this, message, Toast.LENGTH_SHORT).show();

                result.confirm();
                return true;
            }

            /**
             * Tell the client to show a file chooser.
             * <p>
             * This is called to handle HTML forms with 'file' input type, in response to the
             * user pressing the "Select File" button.
             * To cancel the request, call <code>filePathCallback.onReceiveValue(null)</code> and
             * return {@code true}.
             *
             * @param webView           The WebView instance that is initiating the request.
             * @param filePathCallback  Invoke this callback to supply the list of paths to files to upload,
             *                          or {@code null} to cancel. Must only be called if the
             *                          {@link #onShowFileChooser} implementation returns {@code true}.
             * @param fileChooserParams Describes the mode of file chooser to be opened, and options to be
             *                          used with it.
             * @return {@code true} if filePathCallback will be invoked, {@code false} to use default
             * handling.
             * @see FileChooserParams
             */
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams)
            {
                //return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                //Intent intent = new Intent(this, ImageGridActivity.class);


                intent.addCategory(Intent.CATEGORY_OPENABLE); // 有的文件， 不是可以直接打开的 二进制码。  排除 virtual File
                intent.setType("*/*");
                //  intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);

                startActivityForResult(intent, CHOOSE_PHOTO);

                try
                {


                    filePathCallback.onReceiveValue(new Uri[]{uri});
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
                return false;
            }


        });


    }


    public void testJS(String javaScriptName)
    {
        webViewMain.loadUrl("javascript:alert(navigator.userAgent)");
        webViewMain.loadUrl("javascript:getUserAgent()");


//
//    //    跑在 ui 线程中，立即进行执行。
//        runOnUiThread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
////                try
////                {
//                    // 使用 webview，免不了会出现这样那样的问题，要防止 app 报错直接挂掉。
//                    //webViewMain.loadUrl("javascript:alert(navigator.userAgent)");
//                    webViewMain.loadUrl("javascript:getUserAgent()");
////                    webViewMain.loadUrl("javascript:document.getElementById('btn1').innerText ='btnadsf1'");
////                    webViewMain.loadUrl("javascript:alert(document.getElementById('btn1').innerText )");
////                    webViewMain.evaluateJavascript( javaScriptName+"()", new ValueCallback<String>()
////                    {
////                        @Override
////                        public void onReceiveValue(String value)
////                        {
////                            String str = value;
////                        }
////                    });
////                }
////                catch (Exception exception)
////                {
////
////
////                }
//            }
//        });
//

    }

    public void testJS2()
    {
        webViewMain.loadUrl("javascript:getUserAgentDealed()");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
//是否是返回键
        if (keyCode == event.KEYCODE_BACK)
        {
//webView是否可以返回。
            if (webViewMain.canGoBack())
            {
                webViewMain.goBack();
                return true;
            }

        }

        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.buttonClose)
    public void setButtonClose()
    {

        Toast.makeText(this, "fdfd", Toast.LENGTH_SHORT).show();

        JinLog.e("许伟进", "进来我的方法了");


        jinSetResult();
        finish();

    }


    @OnClick(R.id.buttonOpenNoraml)
    public void setButtonOpenNoraml()
    {
        testJS("getUserAgent");
        testJS("getUserAgentDealed");
        //startActivity(new Intent(this, ActivityBtn.class));
    }

    @OnClick(R.id.buttonOpenDialog)
    public void setButtonOpenDialog()
    {
        startActivity(new Intent(this, ActivityDialog.class));
    }


    @Override
    public void onBackPressed()
    {
        jinSetResult();
        super.onBackPressed();
    }

    /*
     * 当前Activity 返回，销毁的时候，给上一次启动它的Activity 的返回参数。
     *
     * */
    void jinSetResult()
    {

        Intent intent = new Intent();

        intent.putExtra("isClosed", true);
        intent.putExtra("name", "我关闭了");
        setResult(RESULT_OK, intent);
    }

    @OnClick(R.id.button2)
    public void onButton2Click()
    {
        Toast.makeText(this, "进来了", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ActivityBtn.class));
//
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.addCategory("com.jin.My_CATEGORY");
//        intent.setData(Uri.parse("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=2&tn=baiduhome_pg&wd=%20R.id%20%E8%B7%A8%20activity&rsv_spt=1&oq=R.id%2520%25E5%258F%25AA%25E8%2583%25BD%25E6%2598%25AF%2520R.Layout&rsv_pq=f530211e00040622&rsv_t=5164TbM5sg%2BeDU3iF2YewIudtPUqsbI8NVp%2BvRhl2ZvUPmbaeVYjSpJQ3jxnkYb5KEfx&rqlang=cn&rsv_dl=tb&rsv_enter=1&rsv_btype=t&inputT=4239&rsv_sug3=64&rsv_sug1=20&rsv_sug7=100&rsv_sug2=0&rsv_sug4=4971"));
//        intent.putExtra("jin","我是谁呀！！？？许进进");
//        intent.putExtra("jinAge",88);
//        intent.putExtra("isBOY",true);
//        startActivity(intent);

        // startActivityForResult(intent,88);
//        return;
//        //跑在 ui 线程中，立即进行执行。
//        runOnUiThread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                try
//                { // 使用 webview，免不了会出现这样那样的问题，要防止 app 报错直接挂掉。
//
//
//                    webViewSecond.evaluateJavascript("forAppCall('进我的')", new ValueCallback<String>()
//                    {
//                        @Override
//                        public void onReceiveValue(String value)
//                        {
//                            button2.setText("我改变了！");
//                        }
//                    });
//                }
//                catch (Exception exception)
//                {
//
//
//                }
//            }
//        });
//

    }

    @Override
    protected void onStart()
    {
        JinLog.d(this.toString() + "--start 了");
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        JinLog.d(this.toString() + "--onResume 了");
        super.onResume();
    }

    @Override
    protected void onRestart()
    {
        JinLog.d(this.toString() + "--onRestart 了");
        super.onRestart();
    }

    @Override
    protected void onPause()
    {

        JinLog.d(this.toString() + "--onPause 了");
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        Intent intent = new Intent();
        JinLog.d(this.toString() + "--onStop 了");
        intent.putExtra("isClosed", true);
        intent.putExtra("name", "我关闭了");
        setResult(RESULT_OK, intent);
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState)
    {

        super.onSaveInstanceState(outState, outPersistentState);
        String tempData = "上个 页面保存来的数据。 ";
        outState.putString("temp", tempData);


    }

    @Override
    protected void onDestroy()
    {
        JinLog.d(this.toString() + "--onDestroy 了");
        Intent intent = new Intent();

        intent.putExtra("isClosed", true);
        intent.putExtra("name", "我关闭了");
        setResult(RESULT_OK, intent);
        super.onDestroy();
    }

    /*
     * 供JS 来调用的 Java ，android 的方法。
     *
     * */
    private class JsReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {

        }


    }
}



