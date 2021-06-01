package com.jin.jinutility.store;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.mbms.FileInfo;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type Store in file.
 */
/*
 * 文件存储到： 本地    data/data/<packagenaem>/file 目录下。
 * */
public class StoreInFile
{
    private static StoreInFile storeInFile = new StoreInFile();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static StoreInFile getInstance()
    {
        return storeInFile;
    }

    /**
     * Save to file.
     *
     * @param context the context
     * @param obj     the obj
     */
    public void SaveToFile(Context context, Object obj)
    {
        FileOutputStream outputStream = null;
        BufferedWriter writer = null;
        try
        {
//Attempt to invoke virtual method 'java.io.FileOutputStream android.content.Context.openFileOutput(java.lang.String, int)' on a null object reference
            //Context.MODE_PRIVATE :替换。
            //outputStream = context.openFileOutput("data", Context.MODE_PRIVATE);
            //Context.MODE_APPEND : 如果文件存在，就往里面追加内容。
            outputStream = context.openFileOutput("data", Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(obj.toString());
        }
        catch (FileNotFoundException e) // 获取 outputstream时的。
        {
            e.printStackTrace();
        }
        catch (IOException e) //write 时
        {
            e.printStackTrace();
        }
        catch (Exception e) //write 时
        {
            e.printStackTrace();
        }
        finally
        {
            if (writer != null)
            {
                try
                {
                    writer.close();

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            try
            {
                outputStream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }

    /**
     * Read from file string.
     *
     * @param context the context
     * @return the string
     */
    public String ReadFromFile(Context context)
    {
        FileInputStream inputStream = null;
        BufferedReader reader = null;


        StringBuilder content = new StringBuilder();
        try
        {
            // 使用 读取要小心了，万一这个文件不存在。就报错了。
            //而写这个文件不怕，即便不存在，也会自动新建。
            inputStream = context.openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = reader.readLine()) != null)
            {
                content.append(line);
            }

        }
        catch (FileNotFoundException e) // 获取 outputstream时的。
        {
            e.printStackTrace();
        }
        catch (IOException e) //write 时
        {
            e.printStackTrace();
        }
        catch (Exception e) //任何的异常，都必须用 最大的异常类： Exception 来进行捕捉， 万一没捕捉全还是白搭。
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            try
            {
                inputStream.close();
//                if (inputStream != null)
//                {
//                    inputStream.close();
//                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }


        return content.toString();
    }


    /**
     * Save to prefs 使用 SharedPrefernces 来进行保存数据 .
     * 解决：
     * 1.ojbect 类型繁琐的转换。
     * 2.最后在完成后自动apply
     *
     * @param context the context
     * @param name    the name
     * @param mapList the map list
     */
    public void saveToPrefs(Context context, String name, Map<String, Object> mapList)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(name, Context.MODE_PRIVATE).edit();
        for (String s : mapList.keySet())
        {
            Object val = mapList.get(s);
           // String valType = val.getClass().getTypeName(); //java.lang.Boolean.
            String valType = val.getClass().getSimpleName(); //Boolean

            switch (valType)
            {
                case "Integer":
                    editor.putInt(s, (int) val);
                    break;

                case "Boolean":
                    editor.putBoolean(s, (boolean) val);
                    break;
                case "Float":
                    editor.putFloat(s, (Float) val);
                    break;
                case "Double":
                    editor.putFloat(s,  ((Double) val ).floatValue());
                    break;
                case "Long":
                    editor.putLong(s, (long) val);
                    break;
                case "StringSet":
                    editor.putStringSet(s, (Set<String>) val);
                    break;
                case "String":
                default:
                    editor.putString(s, val.toString());
                    break;
            }
        }
        editor.apply();
    }
    public void saveToPrefs(Context context,Object val)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(val.getClass().getName(), Context.MODE_PRIVATE).edit();
       //for (TypeVariable<? extends Class<?>> jj : val.getClass().getTypeParameters())
        for (Field field : val.getClass().getDeclaredFields())
        {
        }
        editor.apply();
    }
    /**
     * Read from prefs object.
     *
     * @param context the context
     * @param key     the key
     * @return the object
     */
    public Object ReadFromPrefs(Context context, String name, String key)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(name, Context.MODE_PRIVATE).edit();
        return editor;

    }
}
