package com.jinwode.jintest.utility;

import android.os.Looper;
import android.widget.Toast;

import com.jin.jinutility.jinapplication.JinApplication;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class JinOkHttp
{
private  String uri ;

    public JinOkHttp( builder builder)
    {
        this.uri = builder.uri;
    }

    public  static class builder{

        private  String uri = "https://www.baidu.com";

        public  void uri(String uri)
        {
             this.uri = uri;
        }


        public   JinOkHttp  build()
        {
            return new JinOkHttp( this);
        }

    }


    public  void JinRequest()
    {
        new Thread(new Runnable(){
            /**
             * When an object implementing interface <code>Runnable</code> is used
             * to create a thread, starting the thread causes the object's
             * <code>run</code> method to be called in that separately executing
             * thread.
             * <p>
             * The general contract of the method <code>run</code> is that it may
             * take any action whatsoever.
             *
             * @see Thread#run()
             */
            @Override
            public void run()
            {

                try
                {
                    OkHttpClient httpClient= new OkHttpClient();
                    Request request = new Request.Builder().url(uri).build();
                    Response response = httpClient.newCall(request).execute();
                    String rtn = response.body().string();
                    Looper.prepare();
                    Toast.makeText(JinApplication.getContext(),rtn,Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        } ).start();


    }
}
