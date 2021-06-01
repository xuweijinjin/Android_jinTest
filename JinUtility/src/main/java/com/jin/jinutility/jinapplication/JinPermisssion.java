package com.jin.jinutility.jinapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.jin.jinutility.utility.Utility;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

/**
 * The type Jin permisssion.
 */
public class JinPermisssion
{

    private  static  String[] getNoGrantedPermissons(Context activity,  String[] requestPermissons )
    {
        List<String> permissionList = new ArrayList<>();
        for (String requestPermisson : requestPermissons)
        {
            int result = ContextCompat.checkSelfPermission(activity,requestPermisson);
            if (result!=PackageManager.PERMISSION_GRANTED)
            {
                permissionList.add(requestPermisson);
            }
        }
        return  permissionList.toArray( new String[ permissionList.size()]);
    }


    /**
     * Granted permission boolean.
     *
     * @param activity         the activity
     * @param requestPermissons 亲测： 同一权限组下， 如只申请了打电话 。 读取电话状态， 和通话记录 还是 -1 . 而且 申请通话记录还是单独一个弹出来申请的。
     * @return the boolean
     */
    public static boolean grantedPermission( Activity activity,String[] requestPermissons)
    {
        requestPermissons =  getNoGrantedPermissons( activity, requestPermissons );
        if (requestPermissons.length == 0 )
        {
            return  true;
        }else
        {
            //去申请运行时权限。
            ActivityCompat.requestPermissions(activity,requestPermissons, 88);
        }
        // 本来有个 回调函数： onRequestPermissionsResult。
        //但是： 回调函数写道通用类中， 单个activity 。调用有点复杂。暂时先不用。
        //最后再重新再判断一下，这个权限是否成功了。
        requestPermissons =  getNoGrantedPermissons( activity, requestPermissons );
        if (requestPermissons.length == 0 )
        {
            return  true;
        }else
        {
            Toast.makeText(activity,"权限申请失败！",Toast.LENGTH_SHORT).show();
            return  false;
        }
    }

}
