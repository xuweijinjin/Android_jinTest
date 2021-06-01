package com.jin.jinutility.jinactivity;

import android.app.Activity;
import android.icu.text.Edits;
import android.util.AndroidException;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector
{
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity)
    {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity)
    {
        activities.remove(activity);
    }

    public static void finishAll()
    {
        for (Activity activity : activities)
        {
            if (!activity.isFinishing())
            {
                activity.finish();
            }
        }

        activities.clear();
    }

    public static void destory()
    {
        finishAll();
        // killproces只能杀死本app 。不能杀死别的app 。
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
