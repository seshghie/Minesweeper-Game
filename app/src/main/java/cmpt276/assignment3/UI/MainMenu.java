/*
Display main menu and handle relevant functions
 */

package cmpt276.assignment3.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;
import android.widget.Button;

import cmpt276.assignment3.Model.GameScreen;
import cmpt276.assignment3.Model.OptionsScreen;
import cmpt276.assignment3.R;

public class MainMenu extends AppCompatActivity
{
    ConstraintLayout mm_Layout;
    Button btn_Play;
    Button btn_Options;
    Button btn_Help;

    @Override
    public void onBackPressed()
    {
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        mm_Layout = findViewById(R.id.mm_Layout);
        mm_Layout.setBackgroundResource(R.drawable.bg_menu);
        setupPlayButton();
        setupOptionsButton();
        setupHelpButton();
    }

    private void setupHelpButton()
    {
        btn_Help = findViewById(R.id.btn_mm_Help);
        btn_Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent launch_Help = HelpScreen.hs_Intent(MainMenu.this);
                startActivity(launch_Help);
            }
        });
    }

    private void setupOptionsButton()
    {
        btn_Options = findViewById(R.id.btn_mm_Options);
        btn_Options.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent launch_Options = OptionsScreen.os_Intent(MainMenu.this);
                startActivity(launch_Options);
            }
        });
    }

    private void setupPlayButton()
    {
        btn_Play = findViewById(R.id.btn_mm_Play);
        btn_Play.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //launch game
                Intent launch_Game = GameScreen.gs_Intent(MainMenu.this);
                startActivity(launch_Game);
            }
        });
    }

    public static Intent mm_Intent (Context context)
    {
        Intent intent = new Intent(context, MainMenu.class);
        return intent;
    }
}