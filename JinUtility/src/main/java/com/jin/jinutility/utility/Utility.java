package com.jin.jinutility.utility;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utility
{
    /*
     * 用于判断版本号是否大于。
     * 3.9.8 >3.9.6
     *都是三个点？ 如果点数不一样，只判断前面的。
     * */
    public static boolean IsVersionGreater(String origin, String target)
    {
        //  * ^ : | . \ 必须转义。
        //String[] origins = origin.split(".");
        String[] origins = origin.split("\\.");
        String[] targets = target.split("\\.");
        ArrayList<String> originss = new ArrayList<>();
        List<String> targetss = new ArrayList<>();


        if (origins.length >= targets.length)
        {
         //   java.lang.ClassCastException: java.util.Arrays$ArrayList cannot be cast to java.util.ArrayList
            targetss = (List<String>) Arrays.asList(targets);
            for (int i = 0; i < targets.length; i++)
            {
                originss.add(origins[i]);
            }
        } else if (origins.length < targets.length)
        {
            originss = (ArrayList<String>) Arrays.asList(origins);
            for (int i = 0; i < origins.length; i++)
            {
                targetss.add(targets[i]);
            }

        }


        for (int i = 0; i < targetss.size(); i++)
        {

            try
            {
                int originInt =  Integer.parseInt  (originss.get(i));
                int targetInt =  Integer.parseInt  (targetss.get(i));
                if(targetInt<originInt)
                {
                    return  false;
                }else if(targetInt>originInt)
                {
                    return  true;
                }
            }
            catch (NumberFormatException e)
            {
                e.printStackTrace();
                return  false;
            }

        }
       return  true;
    }
    public static Object[] ListToStrings(List<Object> list)
    {
        Object[] objects = new  Object[list.size()];
        for (int i = 0; i < objects.length; i++)
        {
            objects[i] = list.get(i);
        }
        return  objects;
    }
}
