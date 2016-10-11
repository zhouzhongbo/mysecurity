package com.idroi.mysecurity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiNative;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class InmobiNativeActivity extends AppCompatActivity {

    InMobiNative nativead;
    RelativeLayout rl;
    View adview;
    ViewHolder myholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inmobi_native);
        rl = (RelativeLayout) findViewById(R.id.inmobi_native_container);
        adview = getLayoutInflater().inflate(R.layout.photos_item_view,null);
        adview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (nativead != null) {
                    nativead.reportAdClickAndOpenLandingPage(null);
                }
            }
        });
        myholder = new ViewHolder();
        myholder.title = (TextView) adview.findViewById(R.id.caption);
        myholder.image = (ImageView) adview.findViewById(R.id.photo);
        myholder.tag = (TextView) adview.findViewById(R.id.sponsored);
//        this,

        nativead = new InMobiNative(1474250581591l, new InMobiNative.NativeAdListener() {
            @Override
            public void onAdLoadSucceeded(InMobiNative inMobiNative) {
                Log.d("zzb","onAdLoadSucceeded");
                Object adobject = inMobiNative.getAdContent();
                Log.d("zzb","onAdLoadSucceeded adobject = "+adobject);

                try {
                    JSONObject content = new JSONObject((String) inMobiNative.getAdContent());
                    String title = content.getString("title");
                    String landingUrl = content.getString("landingURL");
                    String imageUrl = content.getJSONObject("screenshots").getString("url");
                    myholder.title.setText(title);
//                    myholder.image.setImageURI(Uri.parse(imageUrl));
//                    picasso
                    Picasso.with(InmobiNativeActivity.this).load(imageUrl).into(myholder.image);


                    rl.addView(adview);
                } catch (JSONException e) {
                    Log.d("zzb", e.toString());
                }
                inMobiNative.bind(adview,inMobiNative);

            }

            @Override
            public void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus inMobiAdRequestStatus) {
                Log.d("zzb","onAdLoadFailed");

            }

            @Override
            public void onAdDismissed(InMobiNative inMobiNative) {
                Log.d("zzb","onAdDismissed");

            }

            @Override
            public void onAdDisplayed(InMobiNative inMobiNative) {
                Log.d("zzb","onAdDisplayed");

            }

            @Override
            public void onUserLeftApplication(InMobiNative inMobiNative) {
                Log.d("zzb","onUserLeftApplication");

            }
        });
        nativead.load();
    }





    private class ViewHolder {
        TextView title;
        ImageView image;
        TextView tag;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nativead.unbind(adview);
    }
}
