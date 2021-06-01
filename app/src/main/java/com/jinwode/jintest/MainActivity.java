package com.jinwode.jintest;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jin.jinutility.jinapplication.JinPermisssion;
import com.jin.jinutility.utility.Utility;
import com.jinwode.jintest.utility.JinOkHttp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn);
        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null)
        {
            String pid = uri.getQueryParameter("pid");
        }

        boolean b = Utility.IsVersionGreater("3.63.8", "45.68.9");

        SetLayout_Second();


    }


    /**
     * @param viewParent
     */
    public void addBtn(View viewParent)
    {
//        Button  btn = new Button();对方的
//          boolean


    }


    public void SetLayout_Main()
    {
        setContentView(R.layout.activity_main);
        LinearLayout ll = findViewById(R.id.ll1);
        float width = ll.getWidth();
        float high = ll.getHeight();
        LinearLayout ll2 = findViewById(R.id.llMain);
        float width2 = ll2.getWidth();
        float high2 = ll2.getHeight();
        TableLayout tableLayout1 = findViewById(R.id.tableLayout1);
        float width4 = tableLayout1.getWidth();
        float high4 = tableLayout1.getHeight();
        Log.e(
                "onCreate: ",
                "width" + width + "high" + high + "width2" + width2 + "high2" + high2);

        Log.e(
                "tableLayout 的尺寸: ",
                "width" + width4 + "high" + high4);


        String ss = "---下面的小的 ：----width" + width + "-------height" + high;

        TextView tv1 = findViewById(R.id.textView3);

        tv1.setText(ss);


        String ss2 = "-  主窗体： ------width" + width2 + "-------height" + high2;

        TextView tv2 = findViewById(R.id.textView2);

        tv2.setText(ss2);
        DisplayMetrics metrics = new DisplayMetrics();
        // Display display = MainActivity.this.getWindowManager().getDefaultDisplay();
//        display.getRealMetrics(metrics);
//        int width3 = metrics.widthPixels;
//        int height = metrics.heightPixels;
//        String ss3 = "-------width"+ width3+"-------height"+height;
//
//        TextView tv = findViewById(R.id.textView1);
//
//        tv.setText(ss3);

        //WindowMetrics display = MainActivity.this.getWindowManager().getCurrentWindowMetrics();


        // Rect rect =  display.getBounds();
        int width3 = metrics.widthPixels;
        int height = metrics.heightPixels;
        String ss3 = "-------width" + width3 + "-------height" + height;

        TextView tv = findViewById(R.id.textView1);

        tv.setText(ss3);


    }

    /**
     * 使用java 代码来设置第二个view
     */
    public void SetLayout_Second()
    {
        setContentView(R.layout.layout_second);

        LinearLayout root = findViewById(R.id.second_Main);

        root.setOrientation(LinearLayout.VERTICAL);
        // root.setGravity(Gravity.CENTER);
        // root.

        for (int i = 0; i < 10; i++)
        {
            Button button = new Button(this);
            button.setWidth(50);
            button.setHeight(50);


            button.setId(View.generateViewId());
            int btnId = button.getId();
            String val = "";
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1, 1);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1, 4);
            //button.setLayoutParams(layoutParams);
            switch (i)
            {

                case 0:
                    val = "屏幕getDisplayMetrics：" + getResources().getDisplayMetrics();

                    button.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            openActivity();
                        }


                    });
                    button.setLayoutParams(layoutParams2);
                    break;
                case 1:
                    val = "屏幕density：" + getResources().getDisplayMetrics().density;
                    button.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            openWebViewActivity();
                        }


                    });
                    break;
                case 2:
                    val = "btn 的宽度是：" + button.getWidth();
                    button.setOnClickListener(v -> implicitIntentTest());
                    break;
                case 3:
                    val = "拍照";
                    button.setOnClickListener(v ->
                    {
                        startActivity(new Intent(this, CameraActivity.class));
                    });
                    break;
                case 4:
                    val = "调用隐式意图";
                    button.setOnClickListener(v ->
                    {
                        Intent intent = new Intent("com.jin.Action_Start");
                        intent.addCategory("com.jin.My_CATEGORY");

                        startActivity(intent);
                    });
                    break;
                case 5:
                    val = "隐式intent 打开网页";
                    button.setOnClickListener(v ->
                    {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.addCategory("com.jin.My_CATEGORY");
                        intent.setData(Uri.parse("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=2&tn=baiduhome_pg&wd=%20R.id%20%E8%B7%A8%20activity&rsv_spt=1&oq=R.id%2520%25E5%258F%25AA%25E8%2583%25BD%25E6%2598%25AF%2520R.Layout&rsv_pq=f530211e00040622&rsv_t=5164TbM5sg%2BeDU3iF2YewIudtPUqsbI8NVp%2BvRhl2ZvUPmbaeVYjSpJQ3jxnkYb5KEfx&rqlang=cn&rsv_dl=tb&rsv_enter=1&rsv_btype=t&inputT=4239&rsv_sug3=64&rsv_sug1=20&rsv_sug7=100&rsv_sug2=0&rsv_sug4=4971"));
                        intent.putExtra("jin", "我是谁呀！！？？许进进");
                        intent.putExtra("jinAge", 88);
                        intent.putExtra("isBOY", true);

                        startActivityForResult(intent, 88);


                    });
                    break;
                case 6:
                    val = "打开电话";
                    button.setOnClickListener(v ->
                    {
                        List<String> contactList = new ArrayList<>();

                        try
                        {

                            if (JinPermisssion.grantedPermission(this, new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}))
                            {
                                //查询联系人数据
                                Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                                if (cursor != null)
                                {
                                    while (cursor.moveToNext())
                                    {
                                        String dispalyName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                                        String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                        contactList.add( dispalyName+" -:-"+number);

                                    }
                                }


                                cursor.close();

                                //只是拨号，不危险。按不按拨号键由你决定的。
                                //Intent intent = new Intent(Intent.ACTION_DIAL);
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                //intent.addCategory("com.jin.My_CATEGORY");
                                //intent.setData(Uri.parse("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=2&tn=baiduhome_pg&wd=%20R.id%20%E8%B7%A8%20activity&rsv_spt=1&oq=R.id%2520%25E5%258F%25AA%25E8%2583%25BD%25E6%2598%25AF%2520R.Layout&rsv_pq=f530211e00040622&rsv_t=5164TbM5sg%2BeDU3iF2YewIudtPUqsbI8NVp%2BvRhl2ZvUPmbaeVYjSpJQ3jxnkYb5KEfx&rqlang=cn&rsv_dl=tb&rsv_enter=1&rsv_btype=t&inputT=4239&rsv_sug3=64&rsv_sug1=20&rsv_sug7=100&rsv_sug2=0&rsv_sug4=4971"));

                                intent.setData(Uri.parse("tel:15364844255"));

                                startActivity(intent);
                            } else
                            {
                                Toast.makeText(this, "您已禁止拨号权限。如果要使用该功能，请允许！", Toast.LENGTH_LONG).show();
                            }

                        }
                        catch (Exception e)
                        {
                            String str = e.getMessage();
                        }
                    });
                    break;
                case 7:
                    button.setOnClickListener(v ->
                    {
                        Intent intent = new Intent(this, ActivityStore.class);

                        startActivity(intent);
                    });
                    break;
                case 8:
                    val = "测试OkHttp";
                    button.setOnClickListener(v ->
                    {
                        new  JinOkHttp.builder().build().JinRequest();
                    });
                    break;
                default:
                    val = "id是：" + btnId;
                    break;
            }
            button.setText(val);

            button.setWidth(20);

            root.addView(button);


        }
        for (int i = 0; i < root.getChildCount(); i++)
        {
            View view = root.getChildAt(i);


        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        switch (requestCode)
        {
            case 1:
                break;
            case 88:
                if (data != null)
                {
                    Bundle bundle = data.getExtras();
                    Toast.makeText(this, bundle.getString("name"), Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void openActivity()
    {
        // explicit intent 。
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);

    }

    public void implicitIntentTest()
    {
        // implicit intent 。
        String str = "这是JinTestapp 中的文字 。";
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, str);
        startActivity(intent);


    }

    public void openWebViewActivity()
    {

        Intent intent = new Intent(this, WebViewTest.class);
        startActivity(intent);

    }

    private int dp2px(float dp)
    {
        //final 变量（常量）： 用final 修饰的成员变量表示常量，只能被赋值一次， 赋值后值无法改变。
        //final 参数： 该值只能读取该参数，但无法改变该参数的值。 其实就是copy了一份供你玩。
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public void JinTest()
    {
        int ss = 44;

        Integer integer = 455;
        int a = integer.intValue();
        byte b = integer.byteValue();

        // HashMap<Integer ,String> hashMap = new
        //   HashMap<int ,String> hashMap = new

        int c = integer.parseInt("88");

        int sss = integer.parseInt("454546", 2);

        Log.println(Log.WARN, "看看", "sss+");

        Map<Integer, String> map = new HashMap();

        Serializable serializable = new Serializable()
        {
            @Override
            public int hashCode()
            {
                return super.hashCode();
            }
        };
    }

    //region 申请权限

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
            case 88:

                break;
            case 1:

                break;

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //endregion
}

