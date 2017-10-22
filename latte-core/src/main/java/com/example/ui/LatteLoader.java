package com.example.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.latte.R;
import com.example.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by Watt on 2017/10/22.
 */

public class LatteLoader {
    private final static  int LOADER_SIZE_SCALE = 8;
    private final static  int LOADER_OFFSET_SCALE = 45;
    private final static ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    private final static String DEF_STYLE = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Context context,String type){
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoadCreator.create(type,context);
        dialog.setContentView(avLoadingIndicatorView);
        dialog.setCanceledOnTouchOutside(false);
        int devicewidth = DimenUtil.getScreenWidth();
        int deviceheight = DimenUtil.getScreenHeight();
        final Window dialogWindow = dialog.getWindow();
        if(dialogWindow != null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = devicewidth/ LOADER_SIZE_SCALE;
            lp.height = deviceheight/ LOADER_SIZE_SCALE;
            //lp.height = lp.height + deviceheight/LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }
    public static void showLoading(Context context){
        showLoading(context,DEF_STYLE);
    }
    public static void stopLoading(){
        for(AppCompatDialog dialog : LOADERS){
            if(dialog != null){
                if (dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }
}
