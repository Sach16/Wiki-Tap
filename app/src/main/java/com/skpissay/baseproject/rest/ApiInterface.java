package com.skpissay.baseproject.rest;


import com.skpissay.baseproject.models.User;
import com.skpissay.baseproject.models.Wikipedia;
import com.skpissay.baseproject.rest.response.BasicResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by skpissay on 25/06/18.
 */

public interface ApiInterface {

    @GET("w/api.php")
    Observable<Response<Wikipedia>> getWikiList(@Query("format") String format,
                                                @Query("prop") String prop,
                                                @Query("generator") String generator,
                                                @Query("redirects") int redirects,
                                                @Query("formatversion") int formatversion,
                                                @Query("piprop") String piprop,
                                                @Query("pithumbsize") int pithumbsize,
                                                @Query("pilimit") int pilimit,
                                                @Query("wbptterms") String wbptterms,
                                                @Query("gpssearch") String gpssearch,
                                                @Query("gpslimit") int gpslimit,
                                                @Query("action") String action);

}
