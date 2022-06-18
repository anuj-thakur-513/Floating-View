package com.example.floatingview;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.security.Provider;

public class FloatingViewService extends Service implements View.OnClickListener{

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

        final View collapsedView = floatingWidget.findViewById(R.id.collapsed_view);
        ImageView collapsedCloseButton = (ImageView) floatingWidget.findViewById(R.id.collapsed_closed_button);
        collapsedCloseButton.setOnClickListener(FloatingViewService.this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.collapsed_closed_button:
                stopSelf();
                break;
        }

    }
}
