package com.example.just_ask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class TeacherLanding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_landing);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(TeacherLanding.this, LoginPage.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(TeacherLanding.this).toBundle());
    }

}