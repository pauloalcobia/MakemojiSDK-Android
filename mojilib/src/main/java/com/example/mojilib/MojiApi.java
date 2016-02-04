package com.example.mojilib;

import com.example.mojilib.model.Category;
import com.example.mojilib.model.MojiModel;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Scott Baar on 1/9/2016.
 */
public interface MojiApi {
    String BASE_URL = "https://api.makemoji.com/sdk/";

    @GET("emoji/index/trending")
    Call<List<MojiModel>> getTrending();

    @GET("emoji/index/{category}")
    Call<List<MojiModel>> getByCategory(@Path("category") String category);

    @GET("emoji/categories")
    Call<List<Category>> getCategories();

    @GET("emoji/index/used/1/255/{deviceId}")
    Call<List<MojiModel>> getRecentlyUsed(@Path("deviceId") String deviceId);

    @GET("emoji/allflashtags")
    Call<List<MojiModel>> getFlashtags();

    @GET("emoji/index/trendingflashtags")
    Call<List<MojiModel>> getTrendingFlashtags();

    @POST("messages/create")
    Call<Integer> sendPressed(@Body String htmlMessage);

    @POST("emoji/viewTrack")
    Call<Void> trackViews( @Body RequestBody array);

}