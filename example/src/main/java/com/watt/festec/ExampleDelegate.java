package com.watt.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.delegate.LatteDelegate;
import com.example.net.RestClient;
import com.example.net.callback.IError;
import com.example.net.callback.IFailure;
import com.example.net.callback.ISuccess;

/**
 * Created by Watt on 2017/10/20.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        test();
    }
    private void test(){
        RestClient.builder()
                .loader(getContext())
                .url("https://www.baidu.com/s?wd=test&rsv_spt=1&rsv_iqid=0xf91484ad00039cf7&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=4&rsv_sug1=3&rsv_sug7=100&rsv_t=117c%2FiISmaWo5jrJpI59lDXy4Uss%2F%2BHQYw5Q6wFxFiU%2B6JsIsyj94gMlu84%2BujCLYxgG")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
                        //LatteLoader.stopLoading();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), "error:"+msg, Toast.LENGTH_SHORT).show();
                       // LatteLoader.stopLoading();
                    }
                })
                .build()
                .get();
    }
}
