package com.example.helpinghands;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class PostRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);
        Toast.makeText(this, "In postRequest", Toast.LENGTH_SHORT).show();
    }
}
