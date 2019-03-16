package com.example.helpinghands;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class StudentRegistrationActivity extends AppCompatActivity {


    //firebase elements
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ProgressDialog mregProgress;

    //ui elements
    private EditText nameField;
    private EditText emailField;
    private EditText passField;
    private EditText edField;
    private EditText mobileField;
    private EditText localityField;

    String mobileNo,education,locality;

    Button nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        mregProgress = new ProgressDialog(this);
        //firebase
         mAuth = FirebaseAuth.getInstance();
        nextButton=findViewById(R.id.signup_nextButtonID);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nameField=findViewById(R.id.signup_nameID);
                emailField=findViewById(R.id.signup_emailID);

                edField=findViewById(R.id.signup_EducationID);
               // localityField=findViewById(R.id.Signup_legacyID);

                education=edField.getText().toString();
                //locality=localityField.getText().toString();

                String displayname=nameField.getText().toString();
                String emailId=emailField.getText().toString();

                passField=findViewById(R.id.signup_passwordID);


                String password=passField.getText().toString();

                if(!TextUtils.isEmpty(displayname) || !TextUtils.isEmpty(emailId) || !TextUtils.isEmpty(password))
                {
                    mregProgress.setTitle("Registering User");
                    mregProgress.setMessage("Please wait while we create your account !");
                    mregProgress.setCanceledOnTouchOutside(false);
                    mregProgress.show();
                    registerUser(displayname,emailId,password);
                }

            }
        });
        //registerUser("Amogh","amoghkalyanshetti@gmail.com","amogh123");

    }

    private void registerUser(final String displayName, final String displayEmail, final String displayPassword)
    {
        mAuth.createUserWithEmailAndPassword(displayEmail,displayPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    FirebaseUser currentUser=FirebaseAuth.getInstance().getCurrentUser();
                    String uid=currentUser.getUid();

                    mDatabase=FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                    HashMap<String,String> userMap=new HashMap<>();
                    userMap.put("name",displayName);
                    userMap.put("email",displayEmail);
                    userMap.put("password",displayPassword);
                    userMap.put("education",education);
                   // userMap.put("locality",locality);
                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                mregProgress.dismiss();
                                Intent mainIntent=new Intent(StudentRegistrationActivity.this,MainActivity.class);
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
                    Toast.makeText(StudentRegistrationActivity.this,"Cannot Sign in. Please check the form and try again",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
