package com.example.net;

import android.content.Context;

import com.example.net.callback.IError;
import com.example.net.callback.IFailure;
import com.example.net.callback.IRequest;
import com.example.net.callback.ISuccess;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Watt on 2017/10/20.
 */

public class RestClientBuilder {

    private String mURL;
    private Map<String,Object> mPARMS;
    private IRequest mIREQUEST;
    private ISuccess mISUCCESS;
    private IFailure mIFAILURE;
    private IError mIERROR;
    private RequestBody mBODY;
    private File mFile;
    private Context loadingContext;
    private String loaderType;

    RestClientBuilder(){this.mPARMS=new HashMap<>();}

    public final RestClientBuilder url(String mURL) {
        this.mURL = mURL;
        return this;
    }

    public final RestClientBuilder loader(Context loadingContext){
        this.loadingContext=loadingContext;
        return this;
    }

    public final RestClientBuilder loader(Context loadingContext,String loaderType){
        this.loadingContext=loadingContext;
        this.loaderType=loaderType;
        return this;
    }

    public final RestClientBuilder parms(Map<String, Object> mPARMS) {
        this.mPARMS.putAll(mPARMS);
        return this;
    }

    public final RestClientBuilder parms(String key, Object value) {
        this.mPARMS.put(key, value);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest mIREQUEST) {
        this.mIREQUEST = mIREQUEST;
        return this;
    }

    public final RestClientBuilder success(ISuccess isuccess) {
        this.mISUCCESS = isuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure ifailure) {
        this.mIFAILURE = ifailure;
        return this;
    }

    public final RestClientBuilder error(IError ierror) {
        this.mIERROR = ierror;
        return this;
    }

    public final RestClientBuilder body(RequestBody body) {
        this.mBODY = body;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBODY = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClient build() {
        return new RestClient(mURL, mPARMS,mIREQUEST,mISUCCESS,mIFAILURE,mIERROR,mBODY,mFile,loadingContext,loaderType);
    }
}
