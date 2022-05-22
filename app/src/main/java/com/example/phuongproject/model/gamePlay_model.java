package com.example.phuongproject.model;

import com.example.phuongproject.DATA;

import com.example.phuongproject.gamePlay_Activity;
import com.example.phuongproject.obj.for_player;
import com.example.phuongproject.obj.quiz_class;

import java.util.ArrayList;

public class gamePlay_model {
    gamePlay_Activity game;
    ArrayList<quiz_class> arrQuiz;
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
        arrQuiz = new ArrayList<>(DATA.getData().arrQuiz);
        //arrQuiz.add(new quiz_class("", "", ""));
//        arrQuiz.add(new quiz_class("Min 1", "hungthu", "https://storage.googleapis.com/happy-quiz-vn.appspot.com/zq/quizzes/2f543be0-f450-11e7-a2d8-050901070303-compressed.jpg"));
//        arrQuiz.add(new quiz_class("Min 2", "soctrang", "https://i.ytimg.com/vi/mQxjJpr8Gvg/maxresdefault.jpg"));
    }
    public quiz_class getQuiz ()
    {
        noQuiz++;
        if(noQuiz>=arrQuiz.size())
        {
            noQuiz = arrQuiz.size()-1;
        }
        return arrQuiz.get(noQuiz);
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
