package com.jin.jinutility.jinactivity;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.jin.jinutility.utility.JINLog;

import java.security.Permission;

/**
 * The type Base activity.
 */
public class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        JINLog.d("jinactivity.BaseActivity",getClass().getSimpleName());
        grantedPermission( );
    }


    /**
     * Dp 2 px int.
     * 转换dp 成像素 px 。
     *
     * @param dp the dp
     * @return the int
     */
    public   int dp2px(float dp)
    {
        //final 变量（常量）： 用final 修饰的成员变量表示常量，只能被赋值一次， 赋值后值无法改变。
        //final 参数： 该值只能读取该参数，但无法改变该参数的值。 其实就是copy了一份供你玩。
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    public   void grantedPermission()
    {

        //        PackageManager.PERMISSION_GRANTED; //  0
        //        PackageManager.PERMISSION_DENIED; //   -1
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            //去申请运行时权限。
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE}, 88);
        }
    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {

        switch (requestCode)
        {
           /* case 88:
                Toast.makeText(this,"接受权限了",Toast.LENGTH_SHORT ).show();

                Toast.makeText(this,permissions[0],Toast.LENGTH_SHORT ).show();
                break;
            case 1:
                Toast.makeText(this,"已拒绝",Toast.LENGTH_SHORT ).show();
                break;*/

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
