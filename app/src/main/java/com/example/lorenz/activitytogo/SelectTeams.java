package com.example.lorenz.activitytogo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.FragmentManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

public class SelectTeams extends AppCompatActivity implements TeamOptionsDialog.EditNameDialogListener {

    SeekBar teamsnumber_seekbar;
    ImageView team_1, team_2, team_3, team_4, team_5, team_6;
    int counter = 0;
    int prevprogress;
    View thumbview;

    TextView selectTeams_text;
    //Teamnamen
    TextView team1_tv, team2_tv, team3_tv, team4_tv, team5_tv, team6_tv;

    ArrayList<ObjectAnimator> objectAnimators = new ArrayList<>();
    ObjectAnimator[] objectAnimators1 = new ObjectAnimator[2];


    @Override
    public void onFinishedEditDialog(String inputText, int color) {
        team1_tv.setText(inputText);
        DrawableCompat.setTint(team_1.getDrawable(), ContextCompat.getColor(getApplicationContext(), color));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_teams);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //inflate layout for custom seekbarthumb
        thumbview = LayoutInflater.from(SelectTeams.this).inflate(R.layout.thumb_layout, null, false);


        teamsnumber_seekbar = (SeekBar) findViewById(R.id.seekbar_teams);

        selectTeams_text = (TextView) findViewById(R.id.selectTeams_tv);

        Typeface schrift = Schriftarttypeface();
        selectTeams_text.setTypeface(schrift);

        team_1 = (ImageView) findViewById(R.id.team_1);
        team1_tv = (TextView) findViewById(R.id.team_1_tv);
        team1_tv.setTypeface(schrift);

        team_2 = (ImageView) findViewById(R.id.team_2);
        team2_tv = (TextView) findViewById(R.id.team_2_tv);
        team2_tv.setTypeface(schrift);

        team_3 = (ImageView) findViewById(R.id.team_3);
        team3_tv = (TextView) findViewById(R.id.team_3_tv);
        team3_tv.setTypeface(schrift);

        team_4 = (ImageView) findViewById(R.id.team_4);
        team4_tv = (TextView) findViewById(R.id.team_4_tv);
        team4_tv.setTypeface(schrift);

        team_5 = (ImageView) findViewById(R.id.team_5);
        team5_tv = (TextView) findViewById(R.id.team_5_tv);
        team5_tv.setTypeface(schrift);

        team_6 = (ImageView) findViewById(R.id.team_6);
        team6_tv = (TextView) findViewById(R.id.team_6_tv);
        team6_tv.setTypeface(schrift);

//Set Image und text Invisible
        team_2.setVisibility(View.INVISIBLE);
        team2_tv.setVisibility(View.INVISIBLE);

        team_3.setVisibility(View.INVISIBLE);
        team3_tv.setVisibility(View.INVISIBLE);

        team_4.setVisibility(View.INVISIBLE);
        team4_tv.setVisibility(View.INVISIBLE);

        team_5.setVisibility(View.INVISIBLE);
        team5_tv.setVisibility(View.INVISIBLE);

        team_6.setVisibility(View.INVISIBLE);
        team6_tv.setVisibility(View.INVISIBLE);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.team_1:
                        FragmentManager fragmentManager = getFragmentManager();
                        TeamOptionsDialog teamOptionsDialog = new TeamOptionsDialog();

                        teamOptionsDialog.show(fragmentManager, "TeamDialog");
                        break;


                }
            }
        };

        team_1.setOnClickListener(onClickListener);


        teamsnumber_seekbar.setThumb(getThumb(teamsnumber_seekbar.getProgress()));


        teamsnumber_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                //keeps track -> >0 increasing
                int diff = i - prevprogress;


                switch (i) {

                    case 0:
                        fadeanimateText(team_1, team1_tv, false);
                        fadeanimateText(team_2, team2_tv, false);
                        fadeanimateText(team_3, team3_tv, false);
                        fadeanimateText(team_4, team4_tv, false);
                        fadeanimateText(team_5, team5_tv, false);
                        fadeanimateText(team_6, team6_tv, false);
                        seekBar.setThumb(getThumb(i));
                        break;


                    case 1:

                        if (diff > 0) {
                            //increasing
                            fadeanimateText(team_1, team1_tv, true);
                        } else {
                            //decreasing

                            fadeanimateText(team_2, team2_tv, false);
                            fadeanimateText(team_3, team3_tv, false);
                            fadeanimateText(team_4, team4_tv, false);
                            fadeanimateText(team_5, team5_tv, false);
                            fadeanimateText(team_6, team6_tv, false);
                        }
                        seekBar.setThumb(getThumb(i));

                        break;

                    case 2:
                        if (diff > 0) {
                            fadeanimateText(team_1, team1_tv, true);
                            fadeanimateText(team_2, team2_tv, true);

                        } else {

                            fadeanimateText(team_3, team3_tv, false);
                            fadeanimateText(team_4, team4_tv, false);
                            fadeanimateText(team_5, team5_tv, false);
                            fadeanimateText(team_6, team6_tv, false);
                        }
                        seekBar.setThumb(getThumb(i));

                        break;
                    case 3:
                        if (diff > 0) {
                            fadeanimateText(team_1, team1_tv, true);
                            fadeanimateText(team_2, team2_tv, true);
                            fadeanimateText(team_3, team3_tv, true);

                        } else {

                            fadeanimateText(team_4, team4_tv, false);
                            fadeanimateText(team_5, team5_tv, false);
                            fadeanimateText(team_6, team6_tv, false);
                        }
                        seekBar.setThumb(getThumb(i));
                        break;
                    case 4:
                        if (diff > 0) {
                            fadeanimateText(team_1, team1_tv, true);
                            fadeanimateText(team_2, team2_tv, true);
                            fadeanimateText(team_3, team3_tv, true);
                            fadeanimateText(team_4, team4_tv, true);
                        } else {
                            fadeanimateText(team_5, team5_tv, false);
                            fadeanimateText(team_6, team6_tv, false);
                        }
                        seekBar.setThumb(getThumb(i));

                        break;
                    case 5:
                        if (diff > 0) {
                            fadeanimateText(team_1, team1_tv, true);
                            fadeanimateText(team_2, team2_tv, true);
                            fadeanimateText(team_3, team3_tv, true);
                            fadeanimateText(team_4, team4_tv, true);
                            fadeanimateText(team_5, team5_tv, true);
                        } else {
                            fadeanimateText(team_6, team6_tv, false);
                        }
                        seekBar.setThumb(getThumb(i));

                        break;
                    case 6:
                        if (diff > 0) {

                            fadeanimateText(team_1, team1_tv, true);
                            fadeanimateText(team_2, team2_tv, true);
                            fadeanimateText(team_3, team3_tv, true);
                            fadeanimateText(team_4, team4_tv, true);
                            fadeanimateText(team_5, team5_tv, true);
                            fadeanimateText(team_6, team6_tv, true);
                        }
                        seekBar.setThumb(getThumb(i));

                        break;
                }

                prevprogress = i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * @param view   the imageview that should fade in
     * @param fadein true for fade in, false for fade out
     */
    public void fadeanimateText(ImageView view, TextView textView, boolean fadein) {


        ObjectAnimator objectAnimator = null;


        try {

            if (fadein && view.getVisibility() == View.INVISIBLE) {
                view.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
                ObjectAnimator objectAnimator_text = ObjectAnimator.ofFloat(textView, "alpha", 0, 1);
                objectAnimators1[0] = objectAnimator;
                objectAnimators1[1] = objectAnimator_text;


            }
            if (!fadein && view.getVisibility() == View.VISIBLE) {
                //objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
                //ObjectAnimator objectAnimator_text = ObjectAnimator.ofFloat(textView, "alpha", 1, 0);
                // objectAnimators1[0]=objectAnimator;
                //objectAnimators1[1]=objectAnimator_text;
                view.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
            }

            //Weiß nicht warum, aber muss da bleiben.. sonst doppelte Animation
            objectAnimator.setDuration(400);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectAnimators1);
            animatorSet.setDuration(400);
            animatorSet.start();


        } catch (NullPointerException e) {

        }

    }

    public Drawable getThumb(int progress) {
        ((TextView) thumbview.findViewById(R.id.tvProgress)).setText(progress + "");

        thumbview.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(thumbview.getMeasuredWidth(), thumbview.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        thumbview.layout(0, 0, thumbview.getMeasuredWidth(), thumbview.getMeasuredHeight());
        thumbview.draw(canvas);

        return new BitmapDrawable(getResources(), bitmap);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //Setzt die Schriftart
    public Typeface Schriftarttypeface() {
        AssetManager assetManager = getApplicationContext().getAssets();
        Typeface typeface = Typeface.createFromAsset(assetManager, "fonts/itckrist.ttf");
        return typeface;
    }


}
