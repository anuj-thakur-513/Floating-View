package com.example.floatingview;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.security.Provider;

public class FloatingViewService extends Service implements View.OnClickListener, View.OnTouchListener {

    private View floatingWidget;
    private WindowManager windowManager;
    private View collapsedView;
    private View expandedView;
    private View rootView;
    private WindowManager.LayoutParams wdParams;

    int startXPos, startYPos;
    float startTouchX , startTouchY;

    @Override
    public void onCreate() {
        super.onCreate();

        floatingWidget = LayoutInflater.from(FloatingViewService.this)
                .inflate(R.layout.floating_view_layout, null);

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        wdParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        wdParams.gravity = Gravity.TOP | Gravity.LEFT;
        wdParams.x = 200;
        wdParams.y = 200;

        windowManager.addView(floatingWidget, wdParams);

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

        rootView = floatingWidget.findViewById(R.id.root_view);
        rootView.setOnTouchListener(this);
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // Access the start position of the widget
                startXPos = wdParams.x;
                startYPos = wdParams.y;
                // Access the starting touch position of the widget
                startTouchX = event.getRawX();
                startTouchY = event.getRawY();
                return true;

            case MotionEvent.ACTION_UP:
                int startToEndXDifference = (int) (event.getRawX() - startTouchX);
                int startToEndYDifference = (int) (event.getRawY() - startTouchY);

                if (startToEndXDifference < 5 && startToEndYDifference < 5){
                    if (isWidgetCollapsed()){
                        collapsedView.setVisibility(View.GONE);
                        expandedView.setVisibility(View.VISIBLE);
                    }
                }
                return true;

            case MotionEvent.ACTION_MOVE:
                wdParams.x = startXPos + (int) (event.getRawX() - startTouchX);
                wdParams.y = startYPos + (int) (event.getRawY() - startTouchY);
                windowManager.updateViewLayout(floatingWidget, wdParams);
                return true;
        }
        return false;
    }

    private boolean isWidgetCollapsed(){
        return collapsedView.getVisibility() == View.VISIBLE;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (floatingWidget != null){
            windowManager.removeView(floatingWidget);
        }
    }
}
