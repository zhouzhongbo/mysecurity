package com.idroi.mysecurity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.sdk.InMobiSdk;

import java.util.Map;

public class InmobiMainActivity extends AppCompatActivity implements View.OnClickListener{

    InMobiInterstitial interstitial= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inmobi_main);

        findViewById(R.id.inmobi_interstitial_button).setOnClickListener(this);
        findViewById(R.id.inmobi_native_button).setOnClickListener(this);
        InMobiSdk.init(this, "df70fe7e97474f4d9f96d166b1b3434f"); //'this' is used sp
        Log.d("zzb","inmobi ad init");
        initAd();
        createInterstitial();
    }

    protected void initAd(){
        InMobiBanner banner = (InMobiBanner)findViewById(R.id.banner);
        banner.load();
    }

    protected void createInterstitial(){
        // ‘this’ is used to specify context, replace it with the appropriate context as needed.
//        1474868184256
        interstitial = new InMobiInterstitial(this, 1477676934812L,new InMobiInterstitial.InterstitialAdListener(){
            @Override
            public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
                Log.d("zzb","inmobi onAdLoadFailed");
            }

//            @Override
//            public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
//                Log.d("zzb","inmobi onAdReceived");
//            }

            @Override
            public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
                Log.d("zzb","inmobi onAdLoadSucceeded");

            }

            @Override
            public void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {

            }

//            @Override
//            public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {
//
//            }
//
//            @Override
//            public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {
//
//            }

            @Override
            public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {
                Log.d("zzb","inmobi onAdDisplayed");

            }

            @Override
            public void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {

            }

            @Override
            public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
                Log.d("zzb","inmobi onAdDismissed");

            }

            @Override
            public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {

            }
        });

        interstitial.load();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id ==  R.id.inmobi_interstitial_button){
            if(interstitial.isReady()){
                interstitial.show();
            }else{
                interstitial.load();
            }
        }else if(id == R.id.inmobi_native_button){
            Intent nativead = new Intent(InmobiMainActivity.this,InmobiNativeActivity.class);
            nativead.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(nativead);
        }
    }
}
