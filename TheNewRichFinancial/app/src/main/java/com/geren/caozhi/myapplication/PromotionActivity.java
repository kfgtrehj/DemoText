package com.geren.caozhi.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/8/16.
 */
public class PromotionActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion);

        ImageView button = (ImageView) findViewById(R.id.image_promotion);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.image_promotion:
                PromotionActivity.this.finish();
        }
    }
}
