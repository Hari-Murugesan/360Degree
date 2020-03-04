package com.onnidrops.threefiftydrops.data;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiList {
    @Multipart
    @POST
    public Call<ResponseBody> apiMultiPartCall(@Url String url, @PartMap() Map<String, RequestBody> partMap, @Part List<MultipartBody.Part> files);

    @FormUrlEncoded
    @POST
    public Call<ResponseBody> apiFormCall(@Url String url, @FieldMap() Map<String, String> partMap);

    @GET
    public Call<ResponseBody> apiGetCall(@Url String url);

    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlAsync(@Url String fileUrl);

    @POST
    Call<ResponseBody> apiPostBody(@Url String fileUrl, @Body RequestBody body);
}
