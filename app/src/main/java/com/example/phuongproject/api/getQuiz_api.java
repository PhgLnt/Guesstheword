package com.example.phuongproject.api;


import android.os.AsyncTask;

import com.example.phuongproject.DATA;
import com.example.phuongproject.obj.quiz_class;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class getQuiz_api extends AsyncTask<Void, Void, Void> {

    String data;

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
     //           .url("http://172.17.34.219/duoihinhbatchu/getQuiz.php")
     //           .url("http://192.168.3.59/duoihinhbatchu/getQuiz.php")
     //           .url("http://172.30.129.169/duoihinhbatchu/getQuiz.php")
                .url("http://192.168.1.153/duoihinhbatchu/getQuiz.php")


                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            data = responseBody.string();
        } catch (IOException e) {
            data = null;
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (data != null) {
            try {
                DATA.getData().arrQuiz.clear();
                JSONArray array = new JSONArray(data);
                for(int i = 0; i <array.length(); i++)
                {
                    JSONObject jsonObject = array.getJSONObject(i);
                    quiz_class quiz = new quiz_class();
                    quiz.name = jsonObject.getString("name");
                    quiz.answer = jsonObject.getString("answer");
                    quiz.image = jsonObject.getString("image");
                    DATA.getData().arrQuiz.add(quiz);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            // There 's no quiz at all
        }
    }
}


