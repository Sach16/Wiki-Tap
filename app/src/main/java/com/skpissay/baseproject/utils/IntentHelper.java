package com.skpissay.baseproject.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.skpissay.baseproject.R;

/**
 * Created by skpissay on 25/06/18.
 */

public class IntentHelper {

    private Context mContext;

    public IntentHelper(Context context) {
        this.mContext = context;
    }

    public void openDialer(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(mContext.getString(R.string.tel, number)));
        mContext.startActivity(intent);
    }

    public void sendMail(String email){
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.setType("vnd.android.cursor.item/email");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
        mContext.startActivity(Intent.createChooser(emailIntent, mContext.getString(R.string.send_using)));
    }
}

