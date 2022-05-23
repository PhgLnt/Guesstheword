package com.example.phuongproject;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.phuongproject.api.getQuiz_api;
import com.example.phuongproject.model.gamePlay_model;

public class MainActivity extends AppCompatActivity {

    Button btn_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        btn_setting = (Button) findViewById(R.id.btn_settings);
//        registerForContextMenu(btn_setting);

        new getQuiz_api().execute();

    }
    public void play_game(View view)
    {
        if(DATA.getData().arrQuiz.size()>0)
        {
            Intent intent = new Intent(this, gamePlay_Activity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Error!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void exit_game(View view){
        exit();
    }

    public void exit(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);

    }
    @Override
    public void onBackPressed() {
        exit();
    }
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        getMenuInflater().inflate(R.menu.context_menu,menu);
//        super.onCreateContextMenu(menu, v, menuInfo);
//    }
//    private MediaPlayer mp;
//    @Override
//    public boolean  onContextItemSelected(@NonNull MenuItem item) {
//        mp = MediaPlayer.create(this, R.raw.howl);
//        switch (item.getItemId()) {
//            case R.id.ct_music_on:
//
//                mp.start();
//                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.ct_music_off:
//                stopPlaying();
//                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
//                return true;
//
//            default:
//                return false;
//        }
//    }

//    public void setting_game (View view){
//        this.openContextMenu(view);
//    }
//





}
