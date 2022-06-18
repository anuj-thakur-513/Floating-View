package com.example.floatingview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnFloating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFloating = (Button) findViewById(R.id.btnFloating);

        btnFloating.setOnClickListener(view ->{
            startService(new Intent(MainActivity.this, FloatingViewService.class));
        });
    }
}