package com.example.phuongproject;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.phuongproject.api.getQuiz_api;


public class activity_topics extends AppCompatActivity {
    gamePlay_Activity game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);


    }
    public void backToGame (View view)
    {
        onPause();
        Intent returnBtn = new Intent(this, MainActivity.class);
        startActivity(returnBtn);
        finish();
    }
    public void clickFood(View view)
    {
        game.setTopic("food");
        Intent intent = new Intent(this, gamePlay_Activity.class);
        startActivity(intent);


    }
    public  void clickJob(View view)
    {
        game.setTopic("job");
        Intent intent = new Intent(this, gamePlay_Activity.class);
        startActivity(intent);
    }



}