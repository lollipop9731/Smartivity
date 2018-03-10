package com.example.lorenz.activitytogo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lorenz.activitytogo.R;

/**
 * Created by Lorenz on 09.03.2018.
 */

public class TeamOptionsDialog extends DialogFragment {

    EditText team_name;
    String team_name_string;
    TextView team1_name_new;

    private EditNameDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //interface captures information during on Attach lifecycle

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            listener = (EditNameDialogListener) context;

        } catch (ClassCastException e) {

            throw new ClassCastException(context.toString()
                    + " must implement EditNameDialogListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle bundle) {

        final Dialog dialog = new Dialog(getActivity());

        //damit der Dialog den eigenen Titel nimmt
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.team_options_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        //Okay Button

        dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                team_name_string = team_name.getText().toString();

                listener.onFinishedEditDialog(team_name_string, R.color.colorAccent);

                dismiss();
            }

        });

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        team_name = (EditText) getDialog().findViewById(R.id.team_name);
        team1_name_new = (TextView) getDialog().findViewById(R.id.team_1_tv);
    }

    /**
     * Interface To SelectTeams Activity -> receive new Teamname
     */
    public interface EditNameDialogListener {
        void onFinishedEditDialog(String inputText, int color);
    }


}
