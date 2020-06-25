package cmpt276.assignment3.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import cmpt276.assignment3.R;

public class HelpScreen extends AppCompatActivity
{
    ConstraintLayout hs_Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);
        hs_Layout = findViewById(R.id.hs_Layout);
        hs_Layout.setBackgroundResource(R.drawable.bg_help);
    }

    public static Intent hs_Intent (Context context)
    {
        Intent intent = new Intent(context, HelpScreen.class);
        return intent;
    }

}