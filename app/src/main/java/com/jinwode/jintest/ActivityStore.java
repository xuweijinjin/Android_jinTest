package com.jinwode.jintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.jinwode.jintest.Main.MyApplication;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityStore extends AppCompatActivity
{
    @BindView(R.id.buttonSaveText)
    Button buttonSaveText;

    @BindView(R.id.buttonReadText)
    Button buttonReadText;
    @BindView(R.id.textInputEditText)
    TextInputEditText textInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.buttonSaveText)
    void clickButtonSaveText()
    {
        String str = textInputEditText.getText().toString();
        str += ((MyApplication) getApplication()).GetStaticApplicationData();
        com.jin.jinutility.store.StoreInFile.getInstance().SaveToFile(this, str);
       Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","进进");
        map.put("age",88);
        map.put("isGood",true);
        map.put("core",99.88);
        map.put("name","许进进");

        com.jin.jinutility.store.StoreInFile.getInstance().saveToPrefs(this,"jinData",map);
    }

    @OnClick(R.id.buttonReadText)
    void clickButtonReadText()
    {
        String rtn = com.jin.jinutility.store.StoreInFile.getInstance().ReadFromFile(this);
        //将光标移动到文本的末尾， 便于继续输入。


        if (!TextUtils.isEmpty(rtn))
        {
            textInputEditText.setText(rtn);
            textInputEditText.setSelection( rtn.length() - 5);
        }

         SharedPreferences sharedPreferences =getSharedPreferences("jinData", Context.MODE_PRIVATE);
        Toast.makeText(this,sharedPreferences.getString("name","默认值"),Toast.LENGTH_SHORT ).show();
        Toast.makeText(this,sharedPreferences.getInt("age",8)+"",Toast.LENGTH_SHORT ).show();
        Toast.makeText(this,sharedPreferences.getBoolean("isGood",false)+"" ,Toast.LENGTH_SHORT ).show();
        Toast.makeText(this,sharedPreferences.getFloat("core",0)+"",Toast.LENGTH_SHORT ).show();
          SharedPreferences.Editor  editor=  sharedPreferences.edit() ;
        editor.clear();
        editor.apply();

        Toast.makeText(this,rtn,Toast.LENGTH_SHORT ).show();
    }
}