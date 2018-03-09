package com.example.lorenz.activitytogo;

import android.animation.ObjectAnimator;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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

public class SelectTeams extends AppCompatActivity {

    SeekBar teamsnumber_seekbar;
    ImageView team_1, team_2, team_3, team_4, team_5, team_6;
    int counter = 0;
    int prevprogress;
    View thumbview;
    TextView selectTeams_text;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_teams);

        thumbview = LayoutInflater.from(SelectTeams.this).inflate(R.layout.thumb_layout, null, false);


        teamsnumber_seekbar = (SeekBar) findViewById(R.id.seekbar_teams);

        selectTeams_text = (TextView) findViewById(R.id.selectTeams_tv);

        Typeface schrift = Schriftarttypeface();
        selectTeams_text.setTypeface(schrift);

        team_1 = (ImageView) findViewById(R.id.team_1);
        team_2 = (ImageView) findViewById(R.id.team_2);
        team_3 = (ImageView) findViewById(R.id.team_3);
        team_4 = (ImageView) findViewById(R.id.team_4);
        team_5 = (ImageView) findViewById(R.id.team_5);
        team_6 = (ImageView) findViewById(R.id.team_6);


        team_2.setVisibility(View.INVISIBLE);
        team_3.setVisibility(View.INVISIBLE);
        team_4.setVisibility(View.INVISIBLE);
        team_5.setVisibility(View.INVISIBLE);
        team_6.setVisibility(View.INVISIBLE);


        teamsnumber_seekbar.setThumb(getThumb(teamsnumber_seekbar.getProgress()));


        teamsnumber_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                //keeps track -> >0 increasing
                int diff = i - prevprogress;
                switch (i) {

                    case 0:
                        fadeanimateText(team_1, false);
                        seekBar.setThumb(getThumb(i));
                        break;


                    case 1:

                        if (diff > 0) {
                            //increasing
                            fadeanimateText(team_1, true);
                        } else {
                            //decreasing
                            fadeanimateText(team_2, false);
                        }
                        seekBar.setThumb(getThumb(i));

                        break;

                    case 2:
                        if (diff > 0) {
                            fadeanimateText(team_2, true);
                        } else {
                            fadeanimateText(team_3, false);
                        }
                        seekBar.setThumb(getThumb(i));

                        break;
                    case 3:
                        if (diff > 0) {
                            fadeanimateText(team_3, true);
                        } else {
                            fadeanimateText(team_4, false);
                        }
                        seekBar.setThumb(getThumb(i));
                        break;
                    case 4:
                        if (diff > 0) {
                            fadeanimateText(team_4, true);
                        } else {
                            fadeanimateText(team_5, false);
                        }
                        seekBar.setThumb(getThumb(i));

                        break;
                    case 5:
                        if (diff > 0) {
                            fadeanimateText(team_5, true);
                        } else {
                            fadeanimateText(team_6, false);
                        }
                        seekBar.setThumb(getThumb(i));

                        break;
                    case 6:
                        if (diff > 0) {
                            fadeanimateText(team_6, true);
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
    public void fadeanimateText(ImageView view, boolean fadein) {
        ObjectAnimator objectAnimator;

        if (fadein) {
            view.setVisibility(View.VISIBLE);
            objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        } else {
            objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1, 0);

        }
        objectAnimator.setDuration(400);
        objectAnimator.start();
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

    //Setzt die Schriftart
    public Typeface Schriftarttypeface() {
        AssetManager assetManager = getApplicationContext().getAssets();
        Typeface typeface = Typeface.createFromAsset(assetManager, "fonts/itckrist.ttf");
        return typeface;
    }
}
