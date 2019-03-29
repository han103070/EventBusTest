package com.example.eventbustest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = findViewById(R.id.show);

        EventBus.getDefault().register(this);
    }


    public void goSecond(View view){
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void userEventBus(UserEvent userEvent){
        show.setText(userEvent.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销注册
        EventBus.getDefault().unregister(this);
    }
}
