package com.example.phuongproject.adapter;

import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.phuongproject.R;

import java.util.ArrayList;
import java.util.List;

public class answer_adapter extends ArrayAdapter<String> {

    private Context myContext;
    private ArrayList<String> arrList;

    public answer_adapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.myContext = context;
        this.arrList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View cV, @NonNull ViewGroup parent) {

        if(cV == null)
        {
            LayoutInflater inflater = (LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cV = inflater.inflate(R.layout.answer_cell,null);
        }
        TextView tv_answer = cV.findViewById(R.id.tv_answer);
        tv_answer.setText(this.arrList.get(position));
        return cV;
    }
}
