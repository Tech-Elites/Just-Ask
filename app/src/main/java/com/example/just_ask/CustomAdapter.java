package com.example.just_ask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<TeacherDetail> {

    public CustomAdapter(@NonNull Context context, ArrayList<TeacherDetail> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;


        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.customlistview_teacher_details, parent, false);
        }

        try{
            TeacherDetail currentUser = getItem(position);

            TextView tname = currentItemView.findViewById(R.id.issueName);
            tname.setText(currentUser.getName());
        }catch (Exception e){
            System.out.println(e);
        }

        return currentItemView;
    }
}
