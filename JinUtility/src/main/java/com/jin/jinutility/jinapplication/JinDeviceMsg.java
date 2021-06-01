package com.jin.jinutility.jinapplication;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.jin.jinutility.utility.JINLog;

import java.lang.reflect.Method;

public class JinDeviceMsg
{


    /**
     * The constant phoneVersion.
     */
    public final String phoneVersion = android.os.Build.VERSION.RELEASE;
    /**
     * The constant deviceName，手机品牌.
     */
    public final String deviceBrand = android.os.Build.BRAND;
    /**
     * The constant userPhoneName.
     */
    public String userPhoneName = "";
    /**
     * The constant phoneModel.
     */
    public String phoneModel = "";
    /**
     * The constant localPhoneModel.
     */
    public String localPhoneModel = "";
    /**
     * The constant deviceName.
     */
    public String deviceName ;






    /**
     * The constant systemModel.
     */
    public final String systemModel = android.os.Build.MODEL;
    /**
     * The constant identifierForUUNID,手机的唯一标识  IMEI。 使用前加权限： android.permission.READ_PHONE_STATE .
     */

    private String identifierForUUNID;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public JinDeviceMsg(Activity activity)
    {
        setIdentifierForUUNID( activity);
        setDeviceName( activity);
    }


    public void setDeviceName(Activity activity)
    {
//不行， 第三方的
       // java.lang.Ill】egalAccessException: void android.os.SystemProperties.<init>() is not accessible from java.lang.Class<com.jin.jinutility.jinapplication.JinDeviceMsg>
        try{
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object object = (Object) cls.newInstance();
            Method getName = cls.getDeclaredMethod("get", String.class);
            deviceName = (String) getName.invoke(object, "persist.sys.device_name");
        } catch (Exception e){
            e.printStackTrace();
        }


        try
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            {
               deviceName =   Settings.Global.getString(activity.getContentResolver(), Settings.Global.DEVICE_NAME);

              //  Settings.Global.putString(activity.getContentResolver(), Settings.Global.DEVICE_NAME, "大西公司的手机");
             //   BluetoothAdapter.getDefaultAdapter().setName(name);
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setIdentifierForUUNID(Activity activity)
    {


        try
        {

            String ANDROID_ID = Settings.System.getString(activity.getContentResolver(), Settings.System.ANDROID_ID);
            identifierForUUNID = ANDROID_ID;
                //获取  IMEI 时， 始终要Manifest.permission.READ_PRIVILEGED_PHONE_STATE 这个权限。而这个权限是系统权限，无法获取。
//                TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(activity.TELEPHONY_SERVICE);
//                identifierForUUNID = telephonyManager.getImei();



            //设置 setIdentifierForUUNID
//            AccountManager accountManager =  AccountManager.get(activity);
//            @SuppressLint("MissingPermission")
//            Account[] accounts = accountManager.getAccounts();


        }
        catch (Exception e)
        {
            JINLog.d(e.getMessage());
        }

    }

    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }
}
