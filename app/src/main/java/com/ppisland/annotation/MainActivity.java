package com.ppisland.annotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nuoyuan.annotation.Classname;

@Classname
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
