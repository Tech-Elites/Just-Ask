package com.example.just_ask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    ProgressBar p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        p=findViewById(R.id.mainProgress);
        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
        if (u != null) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("teachers");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int flag = 1;
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        if (u.getUid().compareTo(dataSnapshot.getKey()) == 0) {
                            flag=0;
                            p.setVisibility(View.INVISIBLE);
                            Intent i = new Intent(MainActivity.this, TeacherLanding.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());

                            break;
                        }
                    }
                    if (flag == 1) {
                        Intent i = new Intent(MainActivity.this, StudentLanding.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else
        {
            Intent i = new Intent(MainActivity.this, LoginPage.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());

        }

    }
}