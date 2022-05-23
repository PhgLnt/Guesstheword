package com.example.phuongproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.phuongproject.adapter.answer_adapter;
import com.example.phuongproject.model.gamePlay_model;
import com.example.phuongproject.obj.quiz_class;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class gamePlay_Activity extends AppCompatActivity {
    ArrayList<String> arrlistAnswer;
    GridView gvAnswer;
    ArrayList<String> arrListLetters;
    GridView gvLetters;
    ImageView iv_quiz;
    TextView tv_money;
    CardView cv;
//    Switch switchbg;
//    LinearLayout linLay;
    int index = 0;

    gamePlay_model model;
    quiz_class quiz;
    private String dapan = "huhu";
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        // find view by id
        gvAnswer = findViewById(R.id.gvAnswer);
        gvLetters = findViewById(R.id.gvLetters);
        iv_quiz = findViewById(R.id.iv_question);
        tv_money = findViewById(R.id.tv_money);
        //
//        switchbg = findViewById(R.id.switch1);
//        linLay = findViewById(R.id.linelayout)
//
//        if (switchbg != null) {
//            switchbg.setOnCheckedChangeListener(this::onCheckedChanged);
//        }
        initAnswer();
        setOnClick();
        displayQuiz();
    }

//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        Toast.makeText(this, "The Switch is " + (isChecked ? "on" : "off"),
//                Toast.LENGTH_SHORT).show();
//        if(isChecked) {
//            //do stuff when Switch is ON
//            linLay.setBackground();
//
//        } else {
//            //do stuff when Switch if OFF
//
//        }
//    }

    private void setOnClick() {
        gvLetters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String s = (String) adapterView.getItemAtPosition(position);
                int count = 0;

                if (s.length() != 0 && index < arrlistAnswer.size()) {
                    for(int i=0; i<arrlistAnswer.size();i++ )
                    {
                        if(arrlistAnswer.get(i).length()==0)
                        {
                            index = i;
                            break;
                        }
                        count++;
                    }
                    if(count == arrlistAnswer.size())
                    {
                        return;
                    } else {
                        arrListLetters.set(position, "");
                        arrlistAnswer.set(index, s);
                        index++;
                        displayAnswer();
                        displayLetters();
                        checkWin();
                    }
                }
            }
        });


        gvAnswer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String s = (String) adapterView.getItemAtPosition(position);
                if (s.length() != 0 ) {
                    index = position;
                    arrlistAnswer.set(position, "");

                    for(int i =0; i<arrListLetters.size();i++){
                        if(arrListLetters.get(i).length()==0)
                        {
                            arrListLetters.set(i,s);
                            break;
                        }
                    }
                    displayAnswer();
                    displayLetters();


                }
            }
        });
    }
    private  void initAnswer ()
    {
        model = new gamePlay_model(this);
        arrlistAnswer = new ArrayList<>();
        arrListLetters = new ArrayList<>();
    }
    private  void displayAnswer()
    {
        gvAnswer.setNumColumns(arrlistAnswer.size());
        gvAnswer.setAdapter(new answer_adapter(this,0, arrlistAnswer));
    }
    private  void displayLetters()
    {
        gvLetters.setNumColumns(arrListLetters.size()/2);
        gvLetters.setAdapter(new answer_adapter(this,0, arrListLetters));
    }
    public void endQuiz()
    {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(gamePlay_Activity.this);
        // Set the message show for the Alert time
        String message = "This is the end of question.\nThanks for playing!!";
        builder.setMessage(message);
        builder.setTitle("Notification");
        builder.setCancelable(false);
        builder
                .setNegativeButton(
                        "OK",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void displayQuiz()
    {
        quiz = model.getQuiz();

            dapan = quiz.answer;
            createAllChar();
            displayAnswer();
            displayLetters();
            Glide.with(this)
                    .load(quiz.image)
                    .into(iv_quiz);
            model.getInfo();
            tv_money.setText(model.user.money + "");
    }
    // Phát sinh ra các chữ cái đáp án
    private void createAllChar()
    {
        index = 0;
        arrlistAnswer.clear();
        arrListLetters.clear();

        int letters = 16 - dapan.length();
//        Random ran = new Random();
//        if(dapan.length()<10)
//        {
//            letters = (ran.nextInt(5)+6);
//        }
//        else
//        {
//            letters = ran.nextInt(5);
//        }
        for(int i=0; i<dapan.length();i++)
        {
            arrlistAnswer.add("");
        }
        String allChar = dapan.toUpperCase();
        allChar = randomLetter(allChar, letters);
        allChar = shuffleString(allChar);
        for(int i=0; i<allChar.length();i++)
        {
            String s = ""+allChar.charAt(i);
            arrListLetters.add(s);
        }
    }
    String shuffleString (String x)
    {
        List<Character> list = new ArrayList<Character>();
        for(char c : x.toCharArray()) {
            list.add(c);
        }
        Collections.shuffle(list);
        StringBuilder builder = new StringBuilder();
        for(char c : list) {
            builder.append(c);
        }
        String shuffled = builder.toString();
        return shuffled;
    }
    String randomLetter (String x, int n)
    {
        Random ran = new Random();
        for(int i=0; i<n; i++)
        {
            x += ""+(char)(ran.nextInt(26)+65);
        }
        return x;
    }
    private void checkWin()
    {
        String checkAnswer ="";
        for(String s:arrlistAnswer)
        {
            checkAnswer+=s;
        }
        checkAnswer.toUpperCase();
        if(checkAnswer.equals(dapan.toUpperCase()))
        {
            Toast.makeText(this,"That' right !!!", Toast.LENGTH_SHORT).show();
            model.getInfo();
            model.user.money += 10;
            model.saveInfo();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    // Actions to do after 10 seconds
                }
            }, 2000);
            handler.removeCallbacksAndMessages(null);
            displayQuiz();
        }

    }
    // Gợi ý button
    public void openHint (View view)
    {
        model.getInfo();

        if(model.user.money < 10)
        {
            Toast.makeText(this, "Fee is 10$ and you are out of money", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = -1;
        for(int i=0; i<arrlistAnswer.size();i++)
        {
            if(arrlistAnswer.get(i).length() == 0)
            {
                id=i;
                break;
            }
        }
        if (id == -1)
        {
            for(int i = 0; i<arrListLetters.size();i++)
            {
                String s = dapan.toUpperCase().charAt(i) +"";
                if(!arrlistAnswer.get(i).toUpperCase().equals(s))
                {
                    id=i;
                    break;
                }
            }
            for(int j = 0; j<arrListLetters.size();j++)
            {
                if(arrListLetters.get(j).length()==0)
                {
                    arrListLetters.set(j, arrlistAnswer.get(id));
                    break;
                }
            }
        }

        String hint = "" +dapan.charAt(id);
        hint = hint.toUpperCase();
        for(int i=0; i<arrlistAnswer.size();i++)
        {
            if(arrlistAnswer.get(i).toUpperCase().equals(hint))
            {
                arrlistAnswer.set(i,"");
            }
        }
        for(int i = 0; i<arrListLetters.size(); i++)
        {
            if(hint.equals(arrListLetters.get(i)))
            {
                arrListLetters.set(i,"");
            }
        }
        arrlistAnswer.set(id, hint);
        displayAnswer();;
        displayLetters();
        model.getInfo();
        model.user.money -= 10;
        model.saveInfo();
        tv_money.setText(model.user.money+"");

    }
   // Đổi câu hỏi
    public void changeQuiz (View view)
    {
        model.getInfo();

        if(model.user.money < 10)
        {
            Toast.makeText(this, "Fee is 10$ and you are out of money", Toast.LENGTH_SHORT).show();
            return;
        }
        model.getInfo();
        model.user.money -= 10;
        model.saveInfo();
        tv_money.setText(model.user.money+"");
        displayQuiz();
    }
    public void backtoMain(View view)
    {
        onPause();
        Intent returnBtn = new Intent(this, activity_topics.class);
        startActivity(returnBtn);
    }

    //Open music for relax
    public void music_click(View view)
    {
        ImageView im = findViewById(R.id.imMusic);
        if(mp == null)
        {
            mp = MediaPlayer.create(gamePlay_Activity.this, R.raw.latata);
            mp.start();
            im.setBackgroundResource(R.drawable.volume);
        }
        if(mp.isPlaying()) {
            mp.pause();
            im.setBackgroundResource(R.drawable.volumeoff);

        } else {
            mp.start();
            im.setBackgroundResource(R.drawable.volume);
        }

    }
    // Show help
    public void help_center(View view){
        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(gamePlay_Activity.this);
        // Set the message show for the Alert time
        String message = "There will be an interesting picture." +
                "\nYou will have to find what phrase the picture contains.\n" +
                "\nNow, I wish you good luck xD";
        builder.setMessage(message);

        // Set Alert Title
        builder.setTitle("How to play game");
        builder.setCancelable(false);
        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.
        builder
                .setNegativeButton(
                        "CANCEL",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // If user click no
                                // then dialog box is canceled.
                                dialog.cancel();
                            }
                        });
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.reset();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

