package com.idroi.mysecurity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mopub.nativeads.MoPubAdAdapter;
import com.mopub.nativeads.MoPubNativeAdLoadedListener;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.ViewBinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MopubNativeActivity extends AppCompatActivity {

    ListView mylist;
    SimpleAdapter adapter;
    MoPubAdAdapter mAdAdapter;
    RequestParameters myRequestParameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mopub_native);
        mylist = (ListView)findViewById(R.id.mylist);

        adapter = new SimpleAdapter(MopubNativeActivity.this,
                getData(),
                R.layout.liet_item,
                new String[]{"title","info"},
                new int[]{R.id.title,R.id.content});

        createNativeAd();
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "小宗");
        map.put("info", "电台DJ");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "貂蝉");
        map.put("info", "四大美女");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "奶茶");
        map.put("info", "清纯妹妹");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "大黄");
        map.put("info", "是小狗");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "hello");
        map.put("info", "every thing");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "world");
        map.put("info", "hello world");
        list.add(map);

        return list;
    }


    private  void createNativeAd(){
        //create binder
        ViewBinder viewBinder = new ViewBinder.Builder(R.layout.native_ad_list_item)
                .mainImageId(R.id.native_main_image)
                .iconImageId(R.id.native_icon_image)
                .titleId(R.id.native_title)
                .textId(R.id.native_text)
                .privacyInformationIconImageId(R.id.native_privacy_information_icon_image)
                .build();

        //createrender
        MoPubStaticNativeAdRenderer adRenderer = new MoPubStaticNativeAdRenderer(viewBinder);

        //get position
        MoPubNativeAdPositioning.MoPubServerPositioning adPositioning =
                MoPubNativeAdPositioning.serverPositioning();

        //crerate adapter
        mAdAdapter = new MoPubAdAdapter(this, adapter, adPositioning);

        //register render
        mAdAdapter.registerAdRenderer(adRenderer);
        mAdAdapter.setAdLoadedListener(new MoPubNativeAdLoadedListener() {
            @Override
            public void onAdLoaded(int position) {
                Log.d("zzb","native ad load at position:"+position);
            }

            @Override
            public void onAdRemoved(int position) {
                Log.d("zzb","native ad onAdRemoved at position:"+position);
            }
        });
        mylist.setAdapter(mAdAdapter);
    }

    @Override
    public void onResume() {
        // Set up your request parameters
        myRequestParameters = new RequestParameters.Builder()
//                .location(location)
//                .keywords(keywords)
//                .desiredAssets(desiredAssets)
                .build();

        // Request ads when the user returns to this activity.
        mAdAdapter.loadAds("30270ecca0ff4f5ea8079af907b49923", myRequestParameters);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAdAdapter.destroy();
        super.onDestroy();
    }

}
