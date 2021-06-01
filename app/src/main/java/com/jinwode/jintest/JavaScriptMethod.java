package com.jinwode.jintest;

import android.app.Activity;
import android.os.Process;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * The type Java script method.
 */
public class JavaScriptMethod {

    @JavascriptInterface
     public  void ShowStr(Activity activity, String str)
    {
        Toast.makeText(activity,str,Toast.LENGTH_SHORT).show();

    }


    /**
     * Reboot. 重启app，但是不管用。
     *
     * @param activity the activity
     * @param str      the str
     */
    @JavascriptInterface
    public  void Reboot(Activity activity, String str)
    {
        //finish();

        try
        {
            Process.killProcess(Process.myPid());
            // System.exit(0);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

    }
}
