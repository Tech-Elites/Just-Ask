package com.example.just_ask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    String email,password;
    FirebaseAuth firebaseAuth;
    ProgressBar p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        firebaseAuth = FirebaseAuth.getInstance();
        p=findViewById(R.id.progressBarLogin);
        p.setVisibility(View.INVISIBLE);
        getSupportActionBar().setTitle("");

    }

    public void loginPageLogin(View view) {
        EditText t1 = findViewById(R.id.loginPageEmail);
        EditText t2 = findViewById(R.id.loginPagePassword);
        email = t1.getText().toString();
        password = t2.getText().toString();
        login(email, password);
    }

    void login(String email, String password){
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            p.setVisibility(View.INVISIBLE);
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle("Details not filled!")
                    .setMessage("Fill all the details to proceed.")
                    .setPositiveButton("Ok",null)
                    .show();
        }
        else
        {
            p.setVisibility(View.VISIBLE);
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        FirebaseUser u= firebaseAuth.getCurrentUser();
                        if(u!=null)
                        {
                            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("teachers");
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    int flag=1;
                                    for(DataSnapshot dataSnapshot:snapshot.getChildren())
                                    {
                                        if(u.getUid().compareTo(dataSnapshot.getKey())==0)
                                        {
                                            p.setVisibility(View.INVISIBLE);
                                            Intent i = new Intent(LoginPage.this, TeacherLanding.class);
                                            startActivity(i, ActivityOptions.makeSceneTransitionAnimation(LoginPage.this).toBundle());
                                            finish();
                                            flag=0;
                                            break;
                                        }
                                    }
                                    if(flag==1)
                                    {
                                        Intent i = new Intent(LoginPage.this, StudentLanding.class);
                                        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(LoginPage.this).toBundle());
                                        finish();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                    else
                    {
                        p.setVisibility(View.INVISIBLE);
                        new AlertDialog.Builder(LoginPage.this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Error logging in.")
                                .setMessage("Invalid login credentials. Please check your credentials and try again.")
                                .setPositiveButton("Yes",null)
                                .show();
                    }
                }
            });
        }

    }

}