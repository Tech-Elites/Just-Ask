package com.example.just_ask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherLanding extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_landing);
    }

    public void current(View view){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        String current=user.getUid();
        DatabaseReference mRef=database.getReference().child("teachers").child(current).child("current");
        mRef.setValue("1");
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(TeacherLanding.this, LoginPage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(TeacherLanding.this).toBundle());
        finish();
    }

}