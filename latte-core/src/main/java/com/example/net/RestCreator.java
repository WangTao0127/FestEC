package com.example.net;

import com.example.latte.ConfigType;
import com.example.latte.Latte;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Watt on 2017/10/20.
 */

public class RestCreator {
    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }
    private static final class RestCreatorHolder {
        private final static String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());
        private final static Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OKHttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT =new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE = RestCreatorHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
