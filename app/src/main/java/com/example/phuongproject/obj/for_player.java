package com.example.phuongproject.obj;

import android.content.Context;
import android.content.SharedPreferences;

public class for_player {
    private String nameData = "appData";
    public int money;
    public void saveInformation(Context ct)
    {
        SharedPreferences settings = ct.getSharedPreferences(nameData, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("money", money );
        editor.commit();
    }
    public void getInformation(Context ct)
    {
        SharedPreferences settings = ct.getSharedPreferences(nameData, 0);
        money = settings.getInt("money", 10);

    }

}
