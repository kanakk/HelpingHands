package com.example.helpinghands.organization;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helpinghands.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OrganizationRegistrationActivity extends AppCompatActivity {
    Button postrequest;
    EditText organizationNameField;
    EditText cityNameField;
    EditText localityField;
    EditText emailField;
    EditText passwordField;

    String organizationName,cityName,locality,email,password;

    //firebase elements
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ProgressDialog mregProgress;

    private static final String[] Cities = new String[]{
            "Aurangabad", "Amravati", "Ahmedpur", "pune", "mumbai"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_registration);

        mregProgress = new ProgressDialog(this);
        //firebase
        mAuth = FirebaseAuth.getInstance();

    //    EditText editText = findViewById(R.id.City_ID);
        ArrayAdapter <String> adapter =new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Cities);

      //  Button postrequest;




        postrequest=findViewById(R.id.postrequestID);
        postrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                emailField=findViewById(R.id.organizationEmailID);
                organizationNameField=findViewById(R.id.Organization_ID);
                cityNameField=findViewById(R.id.City_ID);
                localityField=findViewById(R.id.LocalityID);
                passwordField =findViewById(R.id.passwordIDOrg);



                organizationName=organizationNameField.getText().toString();
                cityName=cityNameField.getText().toString();
                locality=localityField.getText().toString();
                email=emailField.getText().toString();
                Toast.makeText(getApplicationContext(), email+" "+cityName+" "+password, Toast.LENGTH_SHORT).show();

                password=passwordField.getText().toString();


                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(organizationName) || !TextUtils.isEmpty(locality))
                {
                    Toast.makeText(OrganizationRegistrationActivity.this, "Hereeeeeeeee", Toast.LENGTH_SHORT).show();
                    mregProgress.setTitle("Registering Organization");
                    mregProgress.setMessage("Please wait while we create your account !");
                    mregProgress.setCanceledOnTouchOutside(false);
                    mregProgress.show();
                    registerOrganization();
                }




                //Intent loginIntent=new Intent(OrganizationRegistrationActivity.this, PostRequestActivity.class);
                //startActivity(loginIntent);
            }
        });




    }
    private void registerOrganization()
    {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    FirebaseUser currentUser=FirebaseAuth.getInstance().getCurrentUser();
                    String uid=currentUser.getUid();

                    mDatabase= FirebaseDatabase.getInstance().getReference().child("Organizations").child(uid);

                    HashMap<String,String> userMap=new HashMap<>();
                    userMap.put("organization name",organizationName);
                    userMap.put("email",email);
                    userMap.put("password",password);
                    userMap.put("city name",cityName);
                    userMap.put("locality",locality);
                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                mregProgress.dismiss();
                                Intent mainIntent=new Intent(OrganizationRegistrationActivity.this, OrganizationalTimeLineActivity.class);
                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);////pressing back will not go to the register page
                                startActivity(mainIntent);
                                finish();
                            }
                        }
                    });


                }
                else
                {
                    mregProgress.hide();
                    Toast.makeText(OrganizationRegistrationActivity.this,"Cannot Sign in. Please check the form and try again",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
