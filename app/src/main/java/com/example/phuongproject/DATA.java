package com.example.phuongproject;

import com.example.phuongproject.obj.quiz_class;

import java.util.ArrayList;

public class DATA {
    private  static DATA data;
    static {
        data = new DATA();
    }
    public static DATA getData()
    {
        return data;
    }
     public ArrayList<quiz_class> arrQuiz = new ArrayList<>();


}
