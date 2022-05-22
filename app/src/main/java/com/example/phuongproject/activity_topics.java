package com.example.phuongproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_topics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
    }
    public void backtoGame (View view)
    {
        onPause();
        Intent returnBtn = new Intent(this, MainActivity.class);
        startActivity(returnBtn);
    }
}