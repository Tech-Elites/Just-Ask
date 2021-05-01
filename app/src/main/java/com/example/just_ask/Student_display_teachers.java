package com.example.just_ask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Student_display_teachers extends AppCompatActivity {

    CustomAdapter ca;
    ListView lv;
    ArrayList<TeacherDetail> arrayList  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_display_teachers);
        lv=findViewById(R.id.teacherListStudent);

        getSupportActionBar().setTitle("");

        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent i = new Intent(Student_display_teachers.this, EachClass.class);
                        i.putExtra("name",arrayList.get(position).getName());
                        i.putExtra("class",arrayList.get(position).class1);
                        i.putExtra("subject",arrayList.get(position).subject);
                        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(Student_display_teachers.this).toBundle());
                    }
                }
        );

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("teachers");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren())
                {
                    if(Integer.parseInt(snapshot1.child("current").getValue().toString())==1){
                        TeacherDetail td = new TeacherDetail(snapshot1.child("name").getValue().toString(),
                                snapshot1.child("class").getValue().toString(),
                                snapshot1.child("subject").getValue().toString());
                        arrayList.add(td);
                    }
                }
                updateList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void updateList(){
        try {
            ca=new CustomAdapter(this,arrayList);
            lv.setAdapter(ca);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
