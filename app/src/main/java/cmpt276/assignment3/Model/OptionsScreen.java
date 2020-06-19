package cmpt276.assignment3.Model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import cmpt276.assignment3.R;
import cmpt276.assignment3.UI.HelpScreen;

public class OptionsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);
    }

    public static Intent os_Intent (Context context)
    {
        Intent intent = new Intent(context, OptionsScreen.class);
        return intent;
    }
}