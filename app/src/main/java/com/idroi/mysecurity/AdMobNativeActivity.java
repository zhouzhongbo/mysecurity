package com.idroi.mysecurity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

public class AdMobNativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_mob_native);
        NativeExpressAdView adView = (NativeExpressAdView) findViewById(R.id.admob_native);
        adView.loadAd(new AdRequest.Builder().build());
    }
}
