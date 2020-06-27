/*
Handles storing/accessing game settings set by user/default
 */

package cmpt276.assignment3.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import cmpt276.assignment3.R;

public class OptionsScreen extends AppCompatActivity
{

    //Initialize variables
    ConstraintLayout os_Layout;
    Spinner sp_BSize;
    Spinner sp_Mines;
    String bs_Selected;
    String mines_Selected;
    Button btn_Clear;
    Button btn_Save;
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);
        os_Layout = findViewById(R.id.os_Layout);
        os_Layout.setBackgroundResource(R.drawable.bg_options);
        setupDropDowns();
        save_Data();
        clear_PlayTally();
    }

    public static Intent os_Intent (Context context)
    {
        Intent intent = new Intent(context, OptionsScreen.class);
        return intent;
    }

    private void setupDropDowns()
    {
        //Board Size
        sp_BSize = findViewById(R.id.sp_os_BSize);
        ArrayAdapter<String> bsize_Adapter = new ArrayAdapter<String>(OptionsScreen.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.arr_BSize));
        bsize_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_BSize.setAdapter(bsize_Adapter);

        sp_BSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                bs_Selected = sp_BSize.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        //Mines
        sp_Mines = findViewById(R.id.sp_os_Mines);
        ArrayAdapter<String> mines_Adapter = new ArrayAdapter<String>(OptionsScreen.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.arr_Mines));
        mines_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_Mines.setAdapter(mines_Adapter);

        sp_Mines.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                mines_Selected = sp_Mines.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


    }

    private void save_Data()
    {
        btn_Save = findViewById(R.id.btn_os_Save);
        btn_Save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                mEditor = mPreferences.edit();
                mEditor.putString("BSize", bs_Selected);
                mEditor.putString("Mines", mines_Selected);
                mEditor.commit();
                Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clear_PlayTally()
    {
        btn_Clear = findViewById(R.id.btn_os_Clear);
        btn_Clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                mEditor = mPreferences.edit();
                mEditor.putInt("Total", 0);
                mEditor.commit();
                Toast.makeText(getApplicationContext(), "Play tally has been reset!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}