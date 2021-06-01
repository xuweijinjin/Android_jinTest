package com.jin.jinutility.utility;

import android.util.Log;

import com.jin.jinutility.BuildConfig;


/**
 * The type Jin log.
 */
public abstract class JINLog
{
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;

    public static final int ERROR = 5;



    //啥都不显示。 正式线了。
    public static final int NOTHING = 6;

    /*是否： 只是在 debug 下才进行显示输出。 */
    public    static     boolean IsOnlyDebug  = BuildConfig.BUILD_TYPE=="debug"   ;  //= true;


    public static int Level = VERBOSE;


    /**
     * Sets is only debug.
     * IsOnlyDebug  =BuildConfig.BUILD_TYPE=="debug"
     * 因：build Config 此时无法进行传递，所以先重写吧。
     */
    public abstract  void setIsOnlyDebug();



    public static void v(String msg)
    {
        if (IsOnlyDebug)
        {
            if (Level <= VERBOSE)
            {
                Log.v("jin", msg);

            }
        }

    }

    public static void d(String msg)
    {
        if (IsOnlyDebug)
        {
            if (Level <= DEBUG)
            {
                Log.d("jin", msg);
            }
        }
    }

    public static void i(String msg)
    {
        if (IsOnlyDebug)
        {
            if (Level <= INFO)
            {
                Log.i("jin", msg);
            }
        }
    }

    public static void w(String msg)
    {
        if (IsOnlyDebug)
        {
            if (Level <= WARN)
            {
                Log.w("jin", msg);
            }
        }
    }

    public static void e(String msg)
    {
        if (IsOnlyDebug)
        {
            if (Level <= ERROR)
            {
                Log.e("jin", msg);
            }
        }
    }

    public static void v(String tag, String msg)
    {
        if (Level <= VERBOSE)
        {
            Log.v(tag, msg);

        }
    }

    public static void d(String tag, String msg)
    {
        if (Level <= DEBUG)
        {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg)
    {
        if (Level <= INFO)
        {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg)
    {
        if (Level <= WARN)
        {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg)
    {
        if (Level <= ERROR)
        {
            Log.e(tag, msg);
        }
    }

}
