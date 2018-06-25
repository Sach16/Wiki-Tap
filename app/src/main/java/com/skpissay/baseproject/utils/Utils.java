package com.skpissay.baseproject.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.gson.Gson;
import com.skpissay.baseproject.rest.response.BasicResponse;
import com.skpissay.baseproject.rest.response.RestError;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import okhttp3.ResponseBody;

/**
 * Created by skpissay on 25/06/18.
 */

public class Utils {
    private static final String DATE_FORMAT = "yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'";

    public Utils() {
    }

    public static boolean isEmailValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void showInputMethod(View view) {
        InputMethodManager imm = (InputMethodManager)view.getContext().getSystemService("input_method");
        if(imm != null) {
            imm.showSoftInput(view, 2);
        }

    }

    public static void hideInputMethod(View view) {
        if(view != null) {
            InputMethodManager imm = (InputMethodManager)view.getContext().getSystemService("input_method");
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    public static TextDrawable getTextDrawable(String name, ColorGenerator colorGenerator) {
        int color = colorGenerator.getColor(name);
        TextDrawable textDrawable = TextDrawable.builder().beginConfig().textColor(-1).bold().toUpperCase().endConfig().buildRound(name.substring(0, 1), color);
        return textDrawable;
    }

    public static Date getDate(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'", Locale.US);

        try {
            return df.parse(date);
        } catch (ParseException var3) {
            return null;
        }
    }

    public static String getRelativeTime(String timeString) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'", Locale.US);

        try {
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = df.parse(timeString);
            long e = date.getTime();
            long now = System.currentTimeMillis();
            long diff = now - e;
            long diffMinutes = diff / 60000L;
            long diffHours = diff / 3600000L;
            if(diffMinutes < 60L) {
                return diffMinutes + "m";
            } else if(diffHours < 6L) {
                return diffHours + "h";
            } else {
                SimpleDateFormat todate = new SimpleDateFormat("hh:mm a", Locale.US);
                return todate.format(date);
            }
        } catch (ParseException var14) {
            var14.printStackTrace();
            return timeString;
        }
    }

    public static String getDateHeader(String dateTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'", Locale.US);

        try {
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date e = df.parse(dateTime);
            if(DateUtils.isToday(e.getTime())) {
                return "Today\'s Orders";
            } else {
                SimpleDateFormat todate = new SimpleDateFormat("EEEE, dd MMM", Locale.US);
                return todate.format(e);
            }
        } catch (ParseException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static void setSpannableText(Context context, TextView view, String text1, String text2, int color1, int color2) {
        SpannableString text = new SpannableString(text1 + text2);
        text.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, color1)), 0, text1.length(), 33);
        text.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, color2)), text1.length(), text.length(), 33);
        view.setText(text);
    }

    public static void showToast(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    public static void openAppInPlayStore(Context context) {
        String packageName = context.getPackageName();

        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
        } catch (ActivityNotFoundException var3) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
        }

    }

    public static void dialNumber(String number, Context context) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:" + number));
        context.startActivity(intent);
    }

    public static void messageNumber(String number, Context context) {
        Uri smsUri = Uri.parse("smsto:" + number);
        Intent smsIntent = new Intent("android.intent.action.SENDTO", smsUri);
        context.startActivity(smsIntent);
    }

    public static void openEmail(String emailId, Context context, String subject) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{emailId});
        intent.putExtra("android.intent.extra.SUBJECT", subject);
        context.startActivity(Intent.createChooser(intent, "Send Email"));
    }

    public static String wordFirstCap(String source) {
        String[] words = source.toLowerCase().split("\\s");
        StringBuilder result = new StringBuilder();

        try {
            for(int i = 0; i < words.length; ++i) {
                String word = words[i].trim();
                if(word.length() > 1) {
                    word = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
                }

                result.append(word + " ");
            }
        } catch (Exception var5) {
            ;
        }

        return result.toString().trim();
    }

    public static Date parseDate(Long seconds) {
        Date d = new Date(seconds.longValue() * 1000L);
        return d;
    }

    public static String simpleDate(Date date) {
        String displayDate = (new SimpleDateFormat("EEE, MMMM d, yyyy")).format(date);
        return displayDate;
    }

    public static String only2DecimalPlaces(double num) {
        String sValue = String.format("%.2f", new Object[]{Double.valueOf(num)});
        return sValue;
    }

    /*public static RetailioBuilderConfig retailioBuilderConfig(String baseUrl, String apiKey, String apiSource, String mixPanelToken, int versionCode, String versionName) {
        RetailioBuilderConfig retailioBuilderConfig = new RetailioBuilderConfig(baseUrl, apiKey, apiSource, versionName, versionCode, mixPanelToken);
        return retailioBuilderConfig;
    }*/

    public static RestError handleError(ResponseBody response) {
        RestError restError = new RestError();
        restError.setStatus(400);
        restError.setMessage("Something Went Wrong!");
        if(response instanceof ResponseBody) {
            ResponseBody responseBody = response;

            try {
                BasicResponse e = (BasicResponse)(new Gson()).fromJson(new String(responseBody.bytes()), BasicResponse.class);
                restError = e.getError();
            } catch (IOException var4) {
                var4.printStackTrace();
            }
        }

        return restError;
    }
}
