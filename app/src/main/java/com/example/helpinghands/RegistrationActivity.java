package com.example.helpinghands;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class RegistrationActivity extends AppCompatActivity {


//    private FirebaseAuth mAuth;
//    private DatabaseReference mDatabase;
//    private ProgressDialog mregProgress;



    Button button1,button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        button1=findViewById(R.id.helperButton);
        button2=findViewById(R.id.seekingButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegistrationActivity.this,StudentRegistrationActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegistrationActivity.this,OrganizationRegistrationActivity.class);
                startActivity(intent);
            }
        });


        //firebase
       // mAuth = FirebaseAuth.getInstance();
        //write the following code in onclick method of submit button
        //registerUser("Amogh","amoghkalyanshetti@gmail.com","amogh123");

    }



}
