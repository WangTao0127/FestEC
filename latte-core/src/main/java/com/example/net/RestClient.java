package com.example.net;

import android.content.Context;

import com.example.net.callback.IError;
import com.example.net.callback.IFailure;
import com.example.net.callback.IRequest;
import com.example.net.callback.ISuccess;
import com.example.net.callback.RequestCallbacks;
import com.example.ui.LatteLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;

/**
 * Created by Watt on 2017/10/20.
 */

public class RestClient {
    private final String URL;
    private final Map<String,Object> PARAMS;
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final RequestBody BODY;
    private final File FILE;
    private final RequestCallbacks requestCallbacks;
    private final Context loadingContext;
    private final String loadingStyle;

    public RestClient(String url, Map<String, Object> parms, IRequest irequest, ISuccess isuccess,
                      IFailure ifailure, IError ierror, RequestBody body ,File file, Context loadingContext,String loadingStyle) {
        URL = url;
        PARAMS =new HashMap<>();
        PARAMS.putAll( parms );
        IREQUEST = irequest;
        ISUCCESS = isuccess;
        IFAILURE = ifailure;
        IERROR = ierror;
        BODY = body;
        this.FILE=file;
        this.loadingContext=loadingContext;
        this.loadingStyle=loadingStyle;
        requestCallbacks=new RequestCallbacks(irequest,isuccess,ifailure,ierror,loadingContext!=null);
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    public void request(HttpMethod method){
        final RestService service =RestCreator.getRestService();
        Call<String> call=null;
        if(IREQUEST!=null){
            IREQUEST.onRequestStart();
        }
        if(loadingContext!=null){
            if(loadingStyle!=null){
                LatteLoader.showLoading(loadingContext,loadingStyle);
            }else{
                LatteLoader.showLoading(loadingContext);
            }
        }
        switch(method){
            case GET:
                call= service.get(URL, PARAMS);
                break;
            case POST_RAW:
                call= service.postRaw(URL,BODY);
                break;
            case POST:
                call= service.post(URL, PARAMS);
                break;
            case PUT:
                call= service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call= service.putRaw(URL, BODY);
                break;
            case DELETE:
                call= service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(),requestBody);
                call = RestCreator.getRestService().upload(URL,body);
                break;
            default:
                break;
        }
        if(call!=null){
            call.enqueue(requestCallbacks);
        }
    }
    public void get(){
        request(HttpMethod.GET);
    }
    public void post(){
        if(BODY==null){
            request(HttpMethod.POST);
        }else{
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }
    public void put(){
        if(BODY==null){
            request(HttpMethod.PUT);
        }else {
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }
    public void delete(){
        request(HttpMethod.DELETE);
    }
}
