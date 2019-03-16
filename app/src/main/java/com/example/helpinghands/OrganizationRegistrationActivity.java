package com.example.helpinghands;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class OrganizationRegistrationActivity extends AppCompatActivity {
    Button postrequest;
    private static final String[] Cities = new String[]{
            "Aurangabad", "Amravati", "Ahmedpur", "pune", "mumbai"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_registration);


        EditText editText = findViewById(R.id.City_ID);
        ArrayAdapter <String> adapter =new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Cities);

        Button postrequest;


        postrequest=findViewById(R.id.postrequestID);
        postrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent=new Intent(OrganizationRegistrationActivity.this,PostRequestActivity.class);
                startActivity(loginIntent);
            }
        });




    }
}
