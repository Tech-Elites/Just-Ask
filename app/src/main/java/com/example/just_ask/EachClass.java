package com.example.just_ask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EachClass extends AppCompatActivity {

    String name,class1,subject,uid;
    String Q="";
    TextView a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_class);

        Bundle b = getIntent().getExtras();
        name=b.getString("name");
        class1=b.getString("class");
        subject=b.getString("subject");

        a=findViewById(R.id.eachName);
        a.setText(name);
        a=findViewById(R.id.eachSubject);
        a.setText(subject);
        a=findViewById(R.id.eachClass);
        a.setText(class1);
    }

    public void questionSubmit(View view) {
        EditText b = findViewById(R.id.eachQuestion);
        Q=b.getText().toString();


    }
}