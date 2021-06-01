package com.jinwode.jintest.utility;

import com.jin.jinutility.utility.JINLog;
import com.jinwode.jintest.BuildConfig;


public  class JinLog extends JINLog
{


    /**
     * Sets is only debug.
     * IsOnlyDebug  =BuildConfig.BUILD_TYPE=="debug"
     * 因：build Config 此时无法进行传递，所以先重写吧。
     */
    @Override
    public void setIsOnlyDebug()
    {
        IsOnlyDebug  =BuildConfig.BUILD_TYPE=="debug";
    }
}
