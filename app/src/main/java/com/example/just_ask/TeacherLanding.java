package com.example.just_ask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherLanding extends AppCompatActivity {

    int current;
    FirebaseUser user;
    Button newQ;
    TextView countdownTeacher1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_landing);
        newQ=findViewById(R.id.newQuestion);
        user= FirebaseAuth.getInstance().getCurrentUser();

//        newQ.setVisibility(View.INVISIBLE);
//        check();
        countdownTeacher1=findViewById(R.id.countdownTeacher);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(TeacherLanding.this, LoginPage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(TeacherLanding.this).toBundle());
        finish();
    }



    public void newQ(View view) {
        newQ.setVisibility(View.INVISIBLE);
        FirebaseDatabase.getInstance().getReference().child("teachers").child(user.getUid()).child("current").setValue(1)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(TeacherLanding.this, "S", Toast.LENGTH_SHORT).show();
                            try{

                                new CountDownTimer(30000, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        countdownTeacher1.setText("Seconds remaining: " + millisUntilFinished / 1000);
                                    }

                                    public void onFinish() {
                                        countdownTeacher1.setText("Done!");
                                        
                                    }
                                }.start();
                            }
                            catch (Exception e){

                            }
                        }
                        else{
                            Toast.makeText(TeacherLanding.this, "U", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

}