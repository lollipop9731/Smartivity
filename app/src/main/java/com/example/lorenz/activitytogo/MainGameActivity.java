package com.example.lorenz.activitytogo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainGameActivity extends AppCompatActivity implements View.OnClickListener {
    //Todo Add possibility to hide text with click on the view or spwipe upwards? Maybe animation with rotation
    //Todo Pause Menu !!
    //Todo add current points next to teamname


    private static final int SWIPE_MIN_DISTANCE = 10;
    private static final int SWIPE_MAX_OFF_PATH = 150;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    public int points;
    Typeface typefacenew;
    ImageView wordcard;
    TextView pointsText, countdown, teamname;
    BitmapDrawable bitmapDrawable;
    View.OnTouchListener gestureListener;
    private float xcurrentPos, ycurrentPos, displaywidth;
    private Paint paint = new Paint();
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        //initalizing

        pointsText = (TextView) findViewById(R.id.points);
        wordcard = (ImageView) findViewById(R.id.empty_wordcard);
        countdown = (TextView) findViewById(R.id.countdown_tv);
        teamname = (TextView) findViewById(R.id.teamname_main_tv);


        //initial number of points
        points = 0;

        //initial team name on first start
        teamname.setText(getTeamName("1"));

        //teamcolor on first start
        teamname.setTextColor(getTeamColor("1"));

        //calculation animation
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        displaywidth = (float) displayMetrics.widthPixels;
        xcurrentPos = (float) ((displaywidth / displayMetrics.density) + 200);

        final CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                Date date = new Date(l);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("m:ss");
                String format = simpleDateFormat.format(date);
                long sec = l / 1000;
                // countdown.setText(Long.toString(l / 1000));
                countdown.setText(format);
            }

            @Override
            public void onFinish() {

            }
        }.start();


        //Typeface for custom font
        typefacenew = Typeface.createFromAsset(getAssets(), "fonts/itckrist.ttf");

        //Typeface for TextViews
        teamname.setTypeface(typefacenew);

        //First Drawable with random word
        bitmapDrawable = writeOnDrawable(getWordfromPool());
        wordcard.setImageDrawable(bitmapDrawable);

        //Todo make counter more beautiful and fuctional -> alarm after 60seconds
        wordcard.setOnClickListener(this);

        gestureDetector = new GestureDetector(this, new MyGestureDetector());

        gestureListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        };
        wordcard.setOnTouchListener(gestureListener);


    }

    @Override
    public void onClick(View v) {

    }

    /**
     * @param view         The view that should be animated
     * @param duration     duration of animation in millis
     * @param distance     the distance -> postive or negative
     * @param propertyname "x" or "y"
     */
    public void animateImageView(final View view, int duration, final float distance, String propertyname) {

        //center position
        int x_pos = (int) (displaywidth / 2) - (view.getWidth() / 2);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, propertyname, distance);
        objectAnimator.setDuration(duration);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //spwipe to the right
                if (distance > 0) {


                    int x_pos = (int) (displaywidth / 2) - (view.getWidth() / 2);
                    view.setX(x_pos);
                    bitmapDrawable = writeOnDrawable(getWordfromPool());
                    wordcard.setImageDrawable(bitmapDrawable);

                    //swipe to the left
                } else {

                    //center position of textview
                    int x_pos = (int) (displaywidth / 2) - (view.getWidth() / 2);
                    view.setX(x_pos);
                    //new random word from pool
                    bitmapDrawable = writeOnDrawable(getWordfromPool());
                    wordcard.setImageDrawable(bitmapDrawable);

                }

            }
        });
        objectAnimator.start();

    }

    /**
     * Writes Text on  given drawablebackground
     *
     * @param text custom text
     * @return BitmapDrwable with the text on the drawable
     */
    public BitmapDrawable writeOnDrawable(String text) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.textfeld).copy(Bitmap.Config.ARGB_8888, true);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.weiss));
        paint.setTextSize(100);
        paint.setTypeface(typefacenew);
        paint.setTextAlign(Paint.Align.CENTER);

        Canvas canvas = new Canvas(bitmap);

        int x_pos = (canvas.getWidth() / 2);
        int y_pos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
        canvas.drawText(text, x_pos, y_pos, paint);

        return new BitmapDrawable(getResources(), bitmap);

    }

    /**
     * @return a random word from the wordpoolarray
     */
    public String getWordfromPool() {
        Resources resources = getResources();
        String[] wordpool = resources.getStringArray(R.array.Wordpool);

        Random random = new Random();
        int i = random.nextInt(wordpool.length);
        return wordpool[i];
    }

    /**
     * @param teamnumber 1-6
     * @return name of the team
     */
    public String getTeamName(String teamnumber) {
        SharedPreferences sharedPreference = getSharedPreferences(R.string.SharedPreferenceName + teamnumber, MODE_PRIVATE);
        //returns null if no teammname
        String name = sharedPreference.getString(getResources().getString(R.string.SPnames) + teamnumber, null);
        return name;

    }

    public int getTeamColor(String teamnumber) {
        SharedPreferences sharedPreference = getSharedPreferences(R.string.SharedPreferenceName + teamnumber, MODE_PRIVATE);
        //returns null if no teammname
        int color = sharedPreference.getInt(getResources().getString(R.string.SPcolors) + teamnumber, 0);
        return color;

    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                    return false;
                }
                ;


                //swipe to the left
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                    //image goes to the left -> right answer


                    animateImageView(wordcard, 270, (float) (-0.75 * displaywidth), "x");


                    points = points + 1;


                    //swipe to right -> wrong answer
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    animateImageView(wordcard, 300, (float) (1.5 * displaywidth), "x");
                    points = points - 1;
                }
                ;


                pointsText.setText(Integer.toString(points));


            } catch (Exception e) {
                //nothing
            }
            return false;
        }

    }


}
