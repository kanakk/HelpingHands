package com.example.helpinghands.organization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.helpinghands.R;

public class PostRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);
        Toast.makeText(this, "In Request here", Toast.LENGTH_SHORT).show();
    }
}
