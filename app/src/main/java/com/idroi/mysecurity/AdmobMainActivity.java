package com.idroi.mysecurity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AdmobMainActivity extends AppCompatActivity implements View.OnClickListener{

    private AdView mBanner = null;
    private InterstitialAd mInterstitialAd = null;

//    appid:ca-app-pub-6704987105764608~9617767171
//    banner:ca-app-pub-6704987105764608/2094500374
//    interstatial:ca-app-pub-6704987105764608/3571233576
//    native:ca-app-pub-6704987105764608/5047966779

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admob_main);
        findViewById(R.id.admob_intetstitialad).setOnClickListener(this);
        findViewById(R.id.admob_nativead).setOnClickListener(this);

        adinit();
    }


    private void adinit(){
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-6704987105764608~9617767171");

        //banner
        AdView mBanner = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mBanner.loadAd(adRequest);
        createInterstitial();
    }


    protected void createInterstitial(){
        //interstitial
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6704987105764608/3571233576");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mInterstitialAd = null;
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Toast.makeText(AdmobMainActivity.this, "Ad did not load", Toast.LENGTH_SHORT).show();
                }
                if(mInterstitialAd != null)
                    mInterstitialAd.show();
            }
        });
    }

    @Override
    public void onClick(View button){
        int id  = button.getId();
        if(id == R.id.admob_intetstitialad){
            if(mInterstitialAd == null){
                createInterstitial();
            }else if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
                AdRequest adRequest = new AdRequest.Builder().build();
                mInterstitialAd.loadAd(adRequest);
            }
        }else if(id ==R.id.admob_nativead){
            Intent nativeAdView = new Intent(this,AdMobNativeActivity.class);
            nativeAdView.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(nativeAdView);
        }
    }


    @Override
    protected void onDestroy() {
        if(mBanner!= null){
            mBanner.destroy();
        }
//      if(mInterstitialAd != nul){
//          mInterstitialAd.loadAd();
//      }
        super.onDestroy();
    }
}
