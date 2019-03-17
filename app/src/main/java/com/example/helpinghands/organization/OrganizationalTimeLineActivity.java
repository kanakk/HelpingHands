package com.example.helpinghands.organization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.helpinghands.R;

public class OrganizationalTimeLineActivity extends AppCompatActivity {



    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizational_time_line);
        imageView=findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OrganizationalTimeLineActivity.this,OrganizationProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
