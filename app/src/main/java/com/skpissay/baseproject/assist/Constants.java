package com.skpissay.baseproject.assist;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by skpissay on 25/06/18.
 */

public class Constants {

    public static final String EMAIL_PATTERN_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String PHONE_PATTERN_REGEX = "^[2-9]{1}[0-9]{9}$";

    public static final String ALPHA_NUMERIC_SEARCH_REGEX = "^[a-zA-Z0-9 -]*$";

    public static final String ALPHA_NUMERIC_REGEX = "^[a-zA-Z0-9 ]*$";

    public static final String WIKIPAGE_ID = "WIKIPAGE_ID";

    public interface PreferenceKeys {
        String USER_LOGGED_IN = "user_logged_in";
        String FIRST_TIME_USER = "first_time_user";
        String RETAILER_ID = "retailer_id";
        String DISTRIBUTOR_ID = "distributor_id";
        String IS_ADD_CLOSED = "is_add_closed";
        String PAYMENT_REQUESTED = "payment_requested";
        //        String CART_ID = "cart_id";
//        String CART_UNIQUE_ID = "cart_unique_id";
        String IS_EXIT_CONFIRMATION_TO_BE_ASKED = "is_confirmation_to_be_asked";
        String RETAILER_STORE_NAME = "retailer_store_name";
    }

    public static boolean isEmailValid(String pEmail) {
        boolean lRetVal = true;
        if (pEmail.trim().length() > 0) {
            CharSequence inputStr = pEmail;
            Pattern pattern = Pattern.compile(Constants.EMAIL_PATTERN_REGEX, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputStr);
            if (matcher.matches())
                lRetVal = true;
            else
                lRetVal = false;
        }
        return lRetVal;
    }

    public static boolean isAlphaNumeric(String pAlphaNum) {
        boolean lRetVal = true;
        if (pAlphaNum.trim().length() > 0) {
            CharSequence inputStr = pAlphaNum;
            Pattern pattern = Pattern.compile(Constants.ALPHA_NUMERIC_REGEX, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputStr);
            if (matcher.matches())
                lRetVal = true;
            else
                lRetVal = false;
        }
        return lRetVal;
    }

}

