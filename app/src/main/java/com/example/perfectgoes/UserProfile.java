package com.example.perfectgoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.concurrent.ForkJoinPool;

public class UserProfile extends AppCompatActivity {

    private ImageView BackwardArrow;
    private TextView ForwardArrow;
    private String name,username,email,password,phoneNo;
    private Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        data =getIntent().getExtras();
        filldata();
        ForwardArrow =findViewById(R.id.forwardUserProfile);
        BackwardArrow =findViewById(R.id.backwardUserProfile);

        ForwardArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        BackwardArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, DashBoard.class));
            }
        });
    }
   public void filldata(){

        if(data !=null)
        {
            name =data.getString("name");
            username =data.getString("username");
            password =data.getString("password");
            phoneNo =data.getString("phoneNo");
            email =data.getString("email");
        }
   }
    public void start(){
        Intent intent =new Intent(UserProfile.this,DashBoard.class);
        intent.putExtras(data);
        startActivity(intent);
    }
}
