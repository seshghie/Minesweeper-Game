package cmpt276.assignment3.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;

import cmpt276.assignment3.R;


public class GameScreen extends AppCompatActivity
{
    ConstraintLayout gs_Layout;

    TextView tv_Mines;
    TextView tv_Scans;
    TextView tv_Total;

    int uMines;
    int uScans;
    int total_Plays;

    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;

    Button game_Buttons[][];
    int set_Rows;
    int set_Cols;
    int set_Mines;

    MediaPlayer discover_MP;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        gs_Layout = findViewById(R.id.gs_Layout);
        gs_Layout.setBackgroundResource(R.drawable.bg_play);

        tv_Mines = findViewById(R.id.tv_gs_MFound_a);
        tv_Scans = findViewById(R.id.tv_gs_Scans_a);
        tv_Total = findViewById(R.id.tv_gs_Total_a);

        uMines = 0;
        uScans = 0;

        tv_Mines.setText(String.valueOf(uMines));
        tv_Scans.setText(String.valueOf(uScans));

        mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor =  mPreferences.edit();

        total_Plays = mPreferences.getInt("Total", 0) + 1;
        tv_Total.setText(String.valueOf(total_Plays));
        mEditor.putInt("Total", total_Plays);
        mEditor.commit();

        createGameSpace();

    }

    public static Intent gs_Intent (Context context)
    {
        Intent intent = new Intent(context, GameScreen.class);
        return intent;
    }

    private void createGameSpace()
    {
        String bs_Selected = mPreferences.getString("BSize", "Four x Six");
        String mines_Selected = mPreferences.getString("Mines", "Six");


        if (bs_Selected.equals("Four x Six"))
        {
            set_Rows = 4;
            set_Cols = 6;
        }

        if (bs_Selected.equals("Five x Ten"))
        {
            set_Rows = 5;
            set_Cols = 10;
        }

        if (bs_Selected.equals("Six x Fifteen"))
        {
            set_Rows = 6;
            set_Cols = 15;
        }

        if (mines_Selected.equals("Six"))
        {
            set_Mines = 6;
        }

        if (mines_Selected.equals("Ten"))
        {
            set_Mines = 10;
        }

        if (mines_Selected.equals("Fifteen"))
        {
            set_Mines = 15;
        }

        if (mines_Selected.equals("Twenty"))
        {
            set_Mines = 20;
        }

        TableLayout game_Table = findViewById(R.id.tl_GameSpace);
        game_Buttons = new Button[set_Rows][set_Cols];

        for (int row = 0; row < set_Rows; row++)
        {
            TableRow table_Row = new TableRow(this);
            table_Row.setLayoutParams(new TableLayout.LayoutParams
                    (
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
                    ));
            game_Table.addView(table_Row);

            for (int col = 0; col < set_Cols; col++)
            {
                final int final_Col = col;
                final int final_Row = row;

                Button mine_Button = new Button(this);
                mine_Button.setLayoutParams(new TableRow.LayoutParams
                        (
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                        ));

                // Make text not clip on small buttons
                mine_Button.setPadding(0, 0, 0, 0);

                mine_Button.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        gridButtonClicked(final_Col, final_Row);
                    }
                });

                table_Row.addView(mine_Button);
                game_Buttons[row][col] = mine_Button;
            }
        }


    }

    private void gridButtonClicked(int col, int row)
    {
        discover_MP = MediaPlayer.create(this, R.raw.laser);
        Button clicked_Button = game_Buttons[row][col];

        // Lock Button Sizes:
        lockButtonSizes();

        // Play click sound effect
        discover_MP.start();

        // Scale and apply button background
        int newWidth = clicked_Button.getWidth();
        int newHeight = clicked_Button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_mine);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();



        clicked_Button.setBackground(new BitmapDrawable(resource, scaledBitmap));

    }



    private void lockButtonSizes()
    {
        for (int row = 0; row < set_Rows; row++)
        {
            for (int col = 0; col < set_Cols; col++)
            {
                Button button = game_Buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private int[][] place_Mines()
    {
        Random r_Mines = new Random();

        int [][] mine_Grid = new int[set_Rows][set_Cols];
        for (int i = 0; i < set_Rows; i++)
        {
            mine_Grid[i] = new int[set_Cols];
        }

        while (set_Mines > 0)
        {
            int row = r_Mines.nextInt(set_Rows);
            int col = r_Mines.nextInt(set_Cols);

            // -1 is the bomb
            if (mine_Grid[row][col] != -1)
            {
                mine_Grid[row][col] = -1;
                set_Mines--;
            }
        }

        return mine_Grid;
    }



}