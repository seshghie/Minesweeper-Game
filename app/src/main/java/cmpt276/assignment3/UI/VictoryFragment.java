/*
Display pop up dialog box for game end and handle redirection to main menu
 */

package cmpt276.assignment3.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import cmpt276.assignment3.R;

public class VictoryFragment extends AppCompatDialogFragment
{
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        // Create view to show
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.victory_message, null);

        // Create button listener
        DialogInterface.OnClickListener ret_Listener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent launch_MMenu = MainMenu.mm_Intent(getContext());
                startActivity(launch_MMenu);
            }
        };

        // Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle("Game Over")
                .setView(v)
                .setPositiveButton(android.R.string.ok, ret_Listener)
                .create();


    }
}
