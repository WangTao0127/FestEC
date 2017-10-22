package com.watt.festec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.activities.ProxyActivity;
import com.example.delegate.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

}
