package com.example.helpinghands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.helpinghands.organization.PostRequestActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

    }
    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser==null)//The user has not signed in
        {
            sendToRegistration();
            Toast.makeText(this, "User has not signed in", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "User has already signed in", Toast.LENGTH_SHORT).show();
            sendToAfterLoginPage();
        }
    }

    private void sendToRegistration()
    {
        Intent startIntent=new Intent(MainActivity.this,RegistrationActivity.class);
        startActivity(startIntent);
        finish();//to disable the back button
    }
    private void sendToAfterLoginPage()
    {
        Intent startIntent=new Intent(MainActivity.this, PostRequestActivity.class);
        startActivity(startIntent);
        finish();//to disable the back button
    }
}
