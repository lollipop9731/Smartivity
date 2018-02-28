package com.example.lorenz.activitytogo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageViewTest extends AppCompatActivity implements View.OnClickListener {

    private static final int SWIPE_MIN_DISTANCE = 10;
    private static final int SWIPE_MAX_OFF_PATH = 150;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private float xcurrentPos,ycurrentPos;

    ImageView baumstamm;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_test);

        baumstamm = (ImageView)findViewById(R.id.baumstamm_image);

        baumstamm.setOnClickListener(this);

        gestureDetector = new GestureDetector(this,new MyGestureDetector());

        gestureListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        };
        baumstamm.setOnTouchListener(gestureListener);

    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                    return false;
                };

                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX)>SWIPE_THRESHOLD_VELOCITY){
                    Toast.makeText(getApplicationContext(),"Left Swipe",Toast.LENGTH_LONG).show();
                    baumstamm.setTranslationX(e2.getX());
                }else if(e2.getX()-e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX)>SWIPE_THRESHOLD_VELOCITY){
                    Toast.makeText(getApplicationContext(),"Right Swipe",Toast.LENGTH_LONG).show();



                    xcurrentPos = baumstamm.getX();
                    ycurrentPos = 50;

                    Animation animation = new TranslateAnimation(xcurrentPos,xcurrentPos+550,ycurrentPos,ycurrentPos);
                        animation.setDuration(1000);
                        animation.setFillAfter(true);
                        animation.setFillEnabled(true);

                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                xcurrentPos -= 250;
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }


                        });
                    baumstamm.startAnimation(animation);
                    };





            }catch (Exception e){
                //nothing
            }
            return false;
        }

    }
    @Override
    public void onClick(View v){

    }
}
