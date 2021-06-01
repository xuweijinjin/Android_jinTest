package com.jin.jinutility.jinapplication;

import android.app.Application;
import android.content.Context;

public class JinApplication extends Application
    {
        /*
         * 初始化全局资源
         * */
        @Override
        public void onCreate()
        {
            super.onCreate();
            context =getApplicationContext();

        }

        private  static Context context ;

        public static Context getContext()
        {
            return context;
        }

        @Override
        public void onLowMemory()
        {
            super.onLowMemory();
        }

        @Override
        public void onTrimMemory(int level)
        {
            super.onTrimMemory(level);
        }

        public static JinApplication myInstance = new JinApplication();



        public JinApplication()
        {
        }

        public  String GetStaticApplicationData()
        {
            return  "我是全局的变量。";

        }


}
