package com.example.floatingview;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import java.security.Provider;

public class FloatingViewService extends Service {

    private View floatingWidget;
    private WindowManager windowManager;

    @Override
    public void onCreate() {
        super.onCreate();

        floatingWidget = LayoutInflater.from(FloatingViewService.this)
                .inflate(R.layout.floating_view_layout, null);

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        final WindowManager.LayoutParams parameters = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        parameters.gravity = Gravity.TOP | Gravity.LEFT;
        parameters.x = 200;
        parameters.y = 200;

        windowManager.addView(floatingWidget, parameters);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
