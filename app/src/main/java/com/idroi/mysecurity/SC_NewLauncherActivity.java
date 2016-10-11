package com.idroi.mysecurity;

import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SC_NewLauncherActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sc__new_launcher);
        findViewById(R.id.admob_button).setOnClickListener(this);
        findViewById(R.id.fb_button).setOnClickListener(this);
        findViewById(R.id.inmobi_button).setOnClickListener(this);
        findViewById(R.id.mopub_button).setOnClickListener(this);
        findViewById(R.id.sever_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id  = v.getId();
        Intent acIntent = new Intent();
        switch (id){
            case R.id.admob_button:
                acIntent.setClass(SC_NewLauncherActivity.this,AdmobMainActivity.class);
                break;
            case R.id.fb_button:
                acIntent.setClass(SC_NewLauncherActivity.this, FacebookMainActivity.class);
                break;
            case R.id.inmobi_button:
                acIntent.setClass(SC_NewLauncherActivity.this, InmobiMainActivity.class);
                break;
            case R.id.mopub_button:
                acIntent.setClass(SC_NewLauncherActivity.this, MopubMainActivity.class);
                break;
            case R.id.sever_button:
                acIntent =null;
                Toast.makeText(SC_NewLauncherActivity.this,"no ad now!",Toast.LENGTH_SHORT).show();
                break;

            default:
                acIntent =null;
                Toast.makeText(SC_NewLauncherActivity.this,"strange error occured!",Toast.LENGTH_SHORT).show();
                break;
        }
        if(acIntent != null)
            startActivity(acIntent);

    }

}
