package com.example.floatingview;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import java.security.Provider;

public class FloatingViewService extends Service {

    private View floatingWidget;

    @Override
    public void onCreate() {
        super.onCreate();

        floatingWidget = LayoutInflater.from(FloatingViewService.this)
                .inflate(R.layout.floating_view_layout, null);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
