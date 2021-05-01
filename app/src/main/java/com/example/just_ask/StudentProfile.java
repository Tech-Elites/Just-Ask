package com.example.just_ask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentProfile extends AppCompatActivity {

    TextView n,s,a,y;
    String name, stream;
    String age, year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        n=findViewById(R.id.studentName);
        s=findViewById(R.id.studentStream);
        a=findViewById(R.id.studentAge);
        y=findViewById(R.id.studentYear);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("students").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name=snapshot.child("name").getValue().toString();
                stream=snapshot.child("stream").getValue().toString();
                age=(snapshot.child("age").getValue().toString());
                year=(snapshot.child("year").getValue().toString());
                n.setText(name);
                s.setText(stream);
                a.setText(age);
                y.setText(year);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}