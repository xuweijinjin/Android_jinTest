package com.jinwode.jintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityBtn extends AppCompatActivity {

    @BindView(R.id.button3)
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn);
        ButterKnife.bind(this);
        Window window =  this.getWindow();
        View decorWew =  window.getDecorView();

    }

  @OnClick(R.id.button3)
 public     void    onclickButton3(View    view)
  {
      startActivity(new Intent(this, WebViewTest.class) );
  }



}