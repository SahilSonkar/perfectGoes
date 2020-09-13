package com.example.perfectgoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    private static final String TAG = "SignUp";
    ProgressDialog pd;
    private Button AlreadyHaveAnaccount,submitSignUp;
    private ImageView imageView;
    private String name,username,email,password,phoneNo;
    private TextInputLayout fullNameSign,UserNameSign,EmailSign,PasswordSign,PhoneNoSign;
    private ProgressBar progressBar;
    private RelativeLayout rel;

    FirebaseAuth mAuth;



    FirebaseDatabase rootNodeUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //        Firebase set Up
        mAuth = FirebaseAuth.getInstance();
        rootNodeUser =FirebaseDatabase.getInstance();
        reference =rootNodeUser.getReference("User");

        Context context;
        pd=new ProgressDialog(SignUp.this);
        rel =findViewById(R.id.relLoading);
        progressBar =findViewById(R.id.progress);
        AlreadyHaveAnaccount=findViewById(R.id.haveanAccount);
        submitSignUp =findViewById(R.id.signup);
        fullNameSign =findViewById(R.id.fullname);
        UserNameSign =findViewById(R.id.username);
        EmailSign =findViewById(R.id.email);
        PasswordSign =findViewById(R.id.passwordS);
        PhoneNoSign =findViewById(R.id.phone);
        AlreadyHaveAnaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,login.class));
            }
        });

        submitSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regd();
            }
        });

    }


    public boolean validatePassword(){
        if(password.isEmpty()){
            PasswordSign.setError("Check Your Passowrd");
            return false;
        }else{
            PasswordSign.setError(null);
            PasswordSign.setErrorEnabled(false);
            return true;
        }

    }
    public boolean validatePhoneNo() {
        if(phoneNo.isEmpty()){
            PhoneNoSign.setError("Check Your Phone");
            return false;
        }else{
            PhoneNoSign.setError(null);
            PhoneNoSign.setErrorEnabled(false);
            return true;
        }
    }
    public boolean validateEmail(){

        String match="^[A-Za-z0-9+_.-]+@(.+)$";
        if(email.isEmpty()){
            EmailSign.setError("check Email");
            return false;
        }else if(!email.matches(match)){
            EmailSign.setError("check Email Pattern");
            return false;
        }else{
            EmailSign.setError(null);
            EmailSign.setErrorEnabled(false);
            return true;
        }
    }
    public boolean validateusername(){
        String NoWhiteSpaces = "(?=\\s+$)";
        if(username.isEmpty())
        {
            UserNameSign.setError("check Username");
            return false;
        }
        else if(username.length()>=15)
        {
            UserNameSign.setError("Username is too long");
            return false;
        }
        else{
            UserNameSign.setError(null);
            UserNameSign.setErrorEnabled(false);
            return true;
        }
    }
    public boolean validateName(){

        if(name.isEmpty()){
            fullNameSign.setError("Please check Name");
            return false;}
        else
        {
            fullNameSign.setError(null);
            fullNameSign.setErrorEnabled(false);
            return true;
        }
    }

    public void regd()
    {

        name =fullNameSign.getEditText().getText().toString();
        username =UserNameSign.getEditText().getText().toString();
        email = EmailSign.getEditText().getText().toString();
        password =PasswordSign.getEditText().getText().toString();
        phoneNo =PhoneNoSign.getEditText().getText().toString();

        if(!validateName() || !validateusername() || !validateEmail()
                || !validatePhoneNo() || !validatePassword())
            return;

        pd.setMessage("Loading..");
        pd.show();
        signUpToNewUser(email,password);
    }
    public void signUpToNewUser(final String email, final String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            pd.dismiss();
                            Toast.makeText(SignUp.this, "Done", Toast.LENGTH_SHORT).show();
                            HashMap<String, String> hashMap = new HashMap();
                            hashMap.put("name",name);
                            hashMap.put("username",username);
                            hashMap.put("email",email);
                            hashMap.put("password",password);
                            hashMap.put("phoneNo",phoneNo);
                            reference.setValue(hashMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        pd.dismiss();
                                        Toast.makeText(SignUp.this, "Data Stored" +
                                                "", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            Intent intent=new Intent(SignUp.this,UserProfile.class);
                            startActivity(intent);
//
                        } else {
//                             If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            pd.dismiss();
                            Toast.makeText(SignUp.this, "Failed", Toast.LENGTH_SHORT).show();
//                            updateUI(null);

                        }

                        // ...
                    }
                })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUp.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
