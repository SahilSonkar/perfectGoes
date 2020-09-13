package com.example.perfectgoes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASHSCREEN = 5000;
    //Variables
    Animation top_anim,down_anim;
    private ImageView imageView;
    private TextView slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        top_anim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        down_anim = AnimationUtils.loadAnimation(this, R.anim.down_anmation);

        imageView=findViewById(R.id.imageView);
        slogan =findViewById(R.id.slogan);
        imageView.setAnimation(down_anim);
        slogan.setAnimation(top_anim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run () {
                Intent intent=new Intent(MainActivity.this,login.class);
                Pair[] pair= new Pair[1];
                pair[0]=new Pair<View,String>(imageView,"ImageTransition");
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pair);
                startActivity(intent,options.toBundle());
                finish();
//                startActivity(new Intent(MainActivity.this,SignUp.class));

            }
        },SPLASHSCREEN);
    }
}
