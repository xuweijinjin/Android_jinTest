package com.jinwode.jintest.Main;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyProvider extends ContentProvider
{

    public static int INSET = 1;
    public static int QUERY = 2;
    public static int UPDATE = 3;
    public static int DELETE = 4;
    public static String code = "com.jin.jintest.provider";
    private static UriMatcher uriMatcher;

    static
    {
        // 首先要设置Uri 这个参数： 增删改查，都得传这个参数。
        /*
        Uri两种：

        1. content://com.example.app.provider/table1      --只是表名，全查。
        2. content://com.example.app.provider/table1/1    --只是表名，带id ，只查找符合等于这个id的。。

        */


        uriMatcher = new UriMatcher(-1);
        //这个就表示： 匹配的Uri是： content://com.jin.jintest.provider/insert .
        uriMatcher.addURI(code, "insert", INSET);
        uriMatcher.addURI(code, "query", QUERY);
        uriMatcher.addURI(code, "update", UPDATE);
        uriMatcher.addURI(code, "delete", DELETE);


    }


    @Override
    public boolean onCreate()
    {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder)
    {
        if (uriMatcher.match(uri) == QUERY)
        {

            Cursor cursor;
        } else
        {
            throw new IllegalArgumentException("密码不匹配，无法进行添加操作！");
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri)
    {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values)
    {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs)
    {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs)
    {
        return 0;
    }

    @Nullable
    @Override
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String mode) throws FileNotFoundException
    {
        if (uri != null)
        {

            if (uri.getHost().equals(code))
            {
                String filePath = "";
                String uriPath =uri.getPath();

                String first =  uri.getPathSegments().get(0);
                String last = uriPath.substring(first.length()+1); // 前面有个斜杠 /

                switch (first)
                {
                    case "external_cache_path":
                        filePath = MyApplication.getContext().getExternalCacheDir().getPath();
                        break;
                    default:
                        break;
                }
                filePath += last;


                File path = new File(filePath);
                // So, if the uri was content://com.example.myapp/some/data.xml,
                // we'll end up accessing /Android/data/com.example.myapp/cache/some/data.xml

                int imode = 0;
                if (mode.contains("w"))
                {
                    imode |= ParcelFileDescriptor.MODE_WRITE_ONLY;
                    if (!path.exists())
                    {
                        try
                        {
                            path.createNewFile();
                        }
                        catch (IOException e)
                        {
                            // TODO decide what to do about it, whom to notify...
                            e.printStackTrace();
                        }
                    }
                }
                if (mode.contains("r")) imode |= ParcelFileDescriptor.MODE_READ_ONLY;
                if (mode.contains("+")) imode |= ParcelFileDescriptor.MODE_APPEND;

                return ParcelFileDescriptor.open(path, imode);

            }

        }
        return null;
    }
}