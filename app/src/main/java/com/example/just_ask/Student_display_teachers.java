package com.example.just_ask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("teachers");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren())
                {
                    if(Integer.parseInt(snapshot1.child("current").getValue().toString())==1){
                        TeacherDetail td = new TeacherDetail(snapshot1.child("name").getValue().toString());
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
