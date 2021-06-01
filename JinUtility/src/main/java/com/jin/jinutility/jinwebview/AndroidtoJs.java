package com.jin.jinutility.jinwebview;

import android.content.pm.ActivityInfo;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.jin.jinutility.jinactivity.BaseActivity;
import com.jin.jinutility.jinapplication.JinDeviceMsg;

public class AndroidtoJs
{
    private BaseActivity baseActivity;

    public AndroidtoJs(BaseActivity baseActivity)
    {
        this.baseActivity = baseActivity;
    }

    @JavascriptInterface
    public String getDeviceMsg()
    {
        JinDeviceMsg deviceMsg = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            deviceMsg = new JinDeviceMsg(baseActivity);
        }
       // return new Gson().toJson(deviceMsg);
        return deviceMsg.toString();
    }

    @JavascriptInterface
    public void setPortraitScreen()
    {
        baseActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
    }

    @JavascriptInterface
    public void setHorizontalScreen()
    {
        baseActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏
    }
}
