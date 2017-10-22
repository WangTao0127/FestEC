package com.watt.festec;

import android.app.Application;

import com.example.latte.Latte;
import com.example.latte.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Administrator on 2017/10/17.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).withIcon(new FontEcModule()).withIcon(new FontAwesomeModule()).withApiHost("http://127.0.0.1").configure();
    }

}
