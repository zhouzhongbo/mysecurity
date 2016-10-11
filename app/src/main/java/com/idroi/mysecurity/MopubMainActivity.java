package com.idroi.mysecurity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;
import com.mopub.mobileads.MoPubInterstitial;

public class MopubMainActivity extends AppCompatActivity implements MoPubView.BannerAdListener, View.OnClickListener ,MoPubInterstitial.InterstitialAdListener {

    private MoPubView moPubView;
    private MoPubInterstitial mInterstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mopub_main);
        moPubView = (MoPubView) findViewById(R.id.adview);
        moPubView.setAdUnitId("9e851241d2954ea88b7c0810b6ac6550");
        moPubView.loadAd();
        moPubView.setBannerAdListener(this);

        findViewById(R.id.mopub_interstitial_button).setOnClickListener(this);
        findViewById(R.id.mopub_native_button).setOnClickListener(this);

    }

    @Override
    public void onBannerLoaded(MoPubView banner) {
        Log.d("zzb","mopub bannner onBannerLoaded");
    }

    @Override
    public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
        Log.d("zzb","mopub bannner onBannerFailed");
    }

    @Override
    public void onBannerClicked(MoPubView banner) {
        Log.d("zzb","mopub bannner onBannerClicked");
    }

    @Override
    public void onBannerExpanded(MoPubView banner) {
        Log.d("zzb","mopub bannner onBannerExpanded");
    }

    @Override
    public void onBannerCollapsed(MoPubView banner) {
        Log.d("zzb","mopub bannner onBannerCollapsed");
    }

    private void createinterstitial(){
        Log.d("zzb","create interstitial ad");
        mInterstitial = new MoPubInterstitial(this, "70b9e0b1cd5f4653858143af417bc438");
        mInterstitial.setInterstitialAdListener(MopubMainActivity.this);
        mInterstitial.load();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.mopub_interstitial_button){
            if(mInterstitial == null){
                createinterstitial();
            }else{
                mInterstitial.load();
            }
        }else if(id == R.id.mopub_native_button){
            Intent mopub_native = new Intent(MopubMainActivity.this,MopubNativeActivity.class);
            mopub_native.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mopub_native);
        }
    }

    @Override
    protected void onDestroy() {
        if(mInterstitial != null){
            mInterstitial.destroy();
            mInterstitial = null;
        }
        super.onDestroy();
    }

    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        Log.d("zzb","mopub onInterstitialLoaded");
        if(interstitial.isReady()){
            mInterstitial.show();
        }
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
        Log.d("zzb","mopub onInterstitialFailed");
    }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {
        Log.d("zzb","mopub onInterstitialShown");
    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {
        Log.d("zzb","mopub onInterstitialClicked");
    }

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
        Log.d("zzb","mopub onInterstitialDismissed");
        interstitial = null;
    }
}