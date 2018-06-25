package com.skpissay.baseproject.essentials;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.skpissay.baseproject.R;
import com.skpissay.baseproject.utils.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by skpissay on 25/06/18.
 */

public class ForceUpdateActivity extends AppCompatActivity {
    public static final String ACTION_FORCE_UPDATE = "force_update";

    public ForceUpdateActivity() {
    }

    public static Intent getForceUpdateListener(Context context) {
        Intent in = new Intent(context, ForceUpdateActivity.class);
        in.addFlags(67108864);
        in.addFlags('耀');
        return in;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_force_update);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_update})
    void openAppInPlayStore() {
        Utils.openAppInPlayStore(this);
    }
}
