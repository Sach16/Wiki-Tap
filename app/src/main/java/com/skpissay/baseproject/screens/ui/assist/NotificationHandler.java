package com.skpissay.baseproject.screens.ui.assist;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.skpissay.baseproject.R;
import com.skpissay.baseproject.baseclasses.BaseActivity;
import com.skpissay.baseproject.screens.activities.HomeActivity;

import java.util.Map;

/**
 * Created by skpissay on 25/06/18.
 */

public class NotificationHandler {

    private Context mContext;

    public NotificationHandler(BaseActivity context) {
        this.mContext = context;
        init();
    }

    private void init() {
    }

    public void showNotification(String pTitle, String pDesc) {
        Intent lObjIntent = new Intent(mContext, HomeActivity.class);
        int notificationId = (int) (System.currentTimeMillis() % 1000);
        lObjIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int requestCode = 1000 + (int) (System.currentTimeMillis() % 1000);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, requestCode, lObjIntent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher))
                .setContentTitle(pTitle)
                .setContentText(pDesc)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(pDesc))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
