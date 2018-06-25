package com.skpissay.baseproject.essentials.utils;

import java.io.Serializable;

/**
 * Created by skpissay on 25/06/18.
 */

public class AppBuilderConfig implements Serializable {
    public String baseUrl;
    public String apiKey;
    public String versionName;
    public String apiSource;
    public int versionCode;
    public String mixPanelToken;

    public AppBuilderConfig(String baseUrl, String apiKey, String apiSource, String versionName, int versionCode, String mixPanelToken) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.apiSource = apiSource;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.mixPanelToken = mixPanelToken;
    }
}
