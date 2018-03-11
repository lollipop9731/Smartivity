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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.lorenz.activitytogo.R;

/**
 * Created by Lorenz on 09.03.2018.
 */

public class TeamOptionsDialog extends DialogFragment {

    EditText team_name;
    String team_name_string;
    TextView team1_name_new, test;
    int choosen_color;
    ImageView color_gelb, color_green, color_black, color_blue, color_purple, color_red, choosen_color_image;
    ArrayList<Integer> chosenColors = new ArrayList<>();


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
    public Dialog onCreateDialog(final Bundle bundle) {

        final Dialog dialog = new Dialog(getActivity());

        //damit der Dialog den eigenen Titel nimmt
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.team_options_dialog);
        //around of dialog should be transpartent otherwise ugly
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        choosen_color = 0;


        //find ID from colorfields
        color_gelb = (ImageView) dialog.findViewById(R.id.color_picker_yellow_id);
        color_red = (ImageView) dialog.findViewById(R.id.color_picker_red_id);
        color_green = (ImageView) dialog.findViewById(R.id.color_picker_green_id);
        color_black = (ImageView) dialog.findViewById(R.id.color_picker_black_id);
        color_blue = (ImageView) dialog.findViewById(R.id.color_picker_blue_id);
        color_purple = (ImageView) dialog.findViewById(R.id.color_picker_purple_id);




        //Okay Button

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.color_picker_yellow_id:

                        choosen_color = R.color.gelb;
                        setColorFieldAlphas(color_gelb, 0.15f);
                        choosen_color_image = color_gelb;

                        break;

                    case R.id.color_picker_purple_id:
                        choosen_color = R.color.purple;
                        setColorFieldAlphas(color_purple, 0.15f);
                        choosen_color_image = color_purple;

                        break;
                    case R.id.color_picker_black_id:
                        choosen_color = R.color.schwarz;
                        setColorFieldAlphas(color_black, 0.15f);
                        break;
                    case R.id.color_picker_blue_id:
                        choosen_color = R.color.blau;
                        setColorFieldAlphas(color_blue, 0.15f);
                        break;
                    case R.id.color_picker_green_id:
                        choosen_color = R.color.grün;
                        setColorFieldAlphas(color_green, 0.15f);
                        break;
                    case R.id.color_picker_red_id:
                        choosen_color = R.color.rot;
                        setColorFieldAlphas(color_red, 0.15f);
                        break;
                }


            }
        };


        checkColors(color_gelb, R.color.gelb, onClickListener);
        checkColors(color_black, R.color.schwarz, onClickListener);
        checkColors(color_purple, R.color.purple, onClickListener);
        checkColors(color_green, R.color.grün, onClickListener);
        checkColors(color_blue, R.color.blau, onClickListener);
        checkColors(color_red, R.color.rot, onClickListener);





        dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                team_name_string = team_name.getText().toString();
                if (choosen_color == 0) {
                    choosen_color = R.color.teamcion_lila;
                }

                listener.onFinishedEditDialog(team_name_string, choosen_color);

                if (team_name_string.length() > 0) {
                    chosenColors.add(choosen_color);
                    dismiss();

                } else {
                    Toast.makeText(getActivity(), R.string.PleaseEnterTeamName, Toast.LENGTH_LONG).show();
                }
            }

        });


        //.setOnClickListener(onClickListener);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();


        team_name = (EditText) dialog.findViewById(R.id.team_name);
        team1_name_new = (TextView) dialog.findViewById(R.id.team_1_tv);


    }

    /**
     * All views will have alpha, except view
     *
     * @param view  will have normal alpha
     * @param alpha the alpha value
     */
    public void setColorFieldAlphas(ImageView view, float alpha) {
        color_purple.setAlpha(alpha);
        color_green.setAlpha(alpha);
        color_gelb.setAlpha(alpha);
        color_red.setAlpha(alpha);
        color_blue.setAlpha(alpha);
        color_black.setAlpha(alpha);

        view.setAlpha(1f);

    }

    /**
     * Interface To SelectTeams Activity -> receive new Teamname
     */
    public interface EditNameDialogListener {
        void onFinishedEditDialog(String inputText, int color);
    }

    public void checkColors(ImageView colorimage, int color, View.OnClickListener onClickListener) {
        if (chosenColors.contains(color)) {
            colorimage.setAlpha(0.1f);
        } else {
            colorimage.setOnClickListener(onClickListener);
        }
    }


}
