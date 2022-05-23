package com.example.phuongproject;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.phuongproject.api.getQuiz_api;


public class activity_topics extends AppCompatActivity {
    public String getTopic() {
        return topic;
    }

    String topic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
    }
    public void backToGame (View view)
    {
        Intent returnBtn = new Intent(this, MainActivity.class);
        startActivity(returnBtn);
        finish();
    }
    public void clickFood(View view)
    {
        topic = "food";
    }
    public  void clickJob(View view)
    {
        topic = "job";
    }



}