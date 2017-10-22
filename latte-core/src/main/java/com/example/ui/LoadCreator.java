package com.example.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by Watt on 2017/10/22.
 */

public final class LoadCreator {
    private static WeakHashMap<String ,Indicator> LOAD_MAP=new WeakHashMap<>();
    static AVLoadingIndicatorView create (String type, Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView=new AVLoadingIndicatorView(context);
        if(LOAD_MAP.get(type)==null){
            final Indicator indicator = getIndicator(type);
            LOAD_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOAD_MAP.get(type));
        return avLoadingIndicatorView;
    }

    public static Indicator getIndicator(String name) {
        if(name==null||name.isEmpty()){
            return null;
        }
        final StringBuffer drawClassName = new StringBuffer();
        if(!name.contains(".")){
            final String defPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawClassName.append(defPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawClassName.append(name);
        try {
            final Class<?> drawClass = Class.forName(drawClassName.toString());
            return (Indicator) drawClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
