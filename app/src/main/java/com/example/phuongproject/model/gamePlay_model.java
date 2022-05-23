package com.example.phuongproject.model;

import com.example.phuongproject.DATA;
import com.example.phuongproject.gamePlay_Activity;
import com.example.phuongproject.obj.for_player;
import com.example.phuongproject.obj.quiz_class;

import java.util.ArrayList;

public class gamePlay_model {
    gamePlay_Activity game;
    ArrayList<quiz_class> arrQuestion;
    int noQuiz = -1;
    public for_player user;


    public gamePlay_model(gamePlay_Activity game)
    {
        this.game = game;
        user = new for_player();
        create_Data();
    }
    private  void create_Data ()
    {
        arrQuestion = new ArrayList<>(DATA.getData().arrQuiz);
    }

    public quiz_class getQuiz ()
    {
        noQuiz++;
        if(noQuiz>=arrQuestion.size())
        {
            noQuiz = arrQuestion.size()-1;
            game.endQuiz();
        }
        return arrQuestion.get(noQuiz);
    }
    public  void getInfo()
    {
        user.getInformation(game);
    }
    public  void saveInfo()
    {
        user.saveInformation(game);
    }
}
