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

public class FloatingViewService extends Service implements View.OnClickListener {

    private View floatingWidget;
    private WindowManager windowManager;
    View collapsedView;
    View expandedView;

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

        collapsedView = floatingWidget.findViewById(R.id.collapsed_view);
        ImageView collapsedCloseButton = (ImageView) floatingWidget.findViewById(R.id.collapsed_closed_button);
        collapsedCloseButton.setOnClickListener(this);

        expandedView = floatingWidget.findViewById(R.id.expanded_view);
        ImageView lionImage = (ImageView) floatingWidget.findViewById(R.id.lionImage);
        lionImage.setOnClickListener(this);
        ImageView previousButton = (ImageView) floatingWidget.findViewById(R.id.btnPrevious);
        previousButton.setOnClickListener(this);
        ImageView leopardImage = (ImageView) floatingWidget.findViewById(R.id.leopardImage);
        leopardImage.setOnClickListener(this);
        ImageView nextButton = (ImageView) floatingWidget.findViewById(R.id.btnNext);
        nextButton.setOnClickListener(this);
        ImageView expandedCloseButton = (ImageView) floatingWidget.findViewById(R.id.close_button_expanded);
        expandedCloseButton.setOnClickListener(this);
        ImageView openButton = (ImageView) floatingWidget.findViewById(R.id.open_button);
        openButton.setOnClickListener(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.collapsed_closed_button:
                stopSelf();
                break;
            case R.id.lionImage:
                break;
            case R.id.btnPrevious:
                break;
            case R.id.leopardImage:
                break;
            case R.id.btnNext:
                break;
            case R.id.close_button_expanded:
                collapsedView.setVisibility(View.VISIBLE);
                expandedView.setVisibility(View.GONE);
                break;
            case R.id.open_button:
                Intent openAppIntent = new Intent(this, MainActivity.class);
                openAppIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(openAppIntent);
                stopSelf();
                break;
        }

    }
}
