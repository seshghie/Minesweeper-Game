package cmpt276.assignment3.UI;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.RelativeLayout;
import android.widget.TextView;

import cmpt276.assignment3.R;


public class MainActivity extends AppCompatActivity
{
    //INITIALIZE VARIABLES
    RelativeLayout ws_Layout;
    TextView tv_Skip;
    TextView tv_Title1;
    TextView tv_Title2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DEFINE VARIABLES
        ws_Layout = findViewById(R.id.ws_Layout);
        tv_Skip = findViewById(R.id.tv_ws_Skip);
        tv_Title1 = findViewById(R.id.tv_ws_Title1);
        tv_Title2 = findViewById(R.id.tv_ws_Title2);


        startAnimations();
        Handler freezeTime = new Handler();
        freezeTime.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                //intent to run main menu
                //Intent launch_MMenu = MainMenu.mm_Intent(MainActivity.this);
                //startActivity(launch_MMenu);
            }
        }, 11000);


        ws_Layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //intent to run main menu
                Intent launch_MMenu = MainMenu.mm_Intent(MainActivity.this);
                startActivity(launch_MMenu);
            }

        });

    }

    private void startAnimations()
    {

        Animation anim_Title1 = AnimationUtils.loadAnimation(this, R.anim.ws_title1_anim);
        Animation anim_Title2 = AnimationUtils.loadAnimation(this, R.anim.ws_title2_anim);
        Animation anim_Skip = AnimationUtils.loadAnimation(this, R.anim.ws_skip_anim);

        ws_Layout.setBackgroundResource(R.drawable.bg_menu);
        tv_Skip.startAnimation(anim_Skip);
        tv_Title1.startAnimation(anim_Title1);
        tv_Title2.startAnimation(anim_Title2);

    }



}