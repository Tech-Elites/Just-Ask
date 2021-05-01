package com.example.just_ask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class StudentLanding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_landing);
        getSupportActionBar().setTitle("");

    }
    public void askdoubt(View view){
        Intent i = new Intent(StudentLanding.this, Student_display_teachers.class);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(StudentLanding.this).toBundle());

    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(StudentLanding.this, LoginPage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(StudentLanding.this).toBundle());

    }

    public void studentProfile(View view) {
        Intent i = new Intent(StudentLanding.this, StudentProfile.class);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(StudentLanding.this).toBundle());
    }
}