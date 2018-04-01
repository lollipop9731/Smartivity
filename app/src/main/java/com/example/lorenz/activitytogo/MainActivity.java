package com.example.lorenz.activitytogo;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView spielen,buch,stern,settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Actionbar ausblenden


        //Zuweisungen
        spielen = (TextView) findViewById(R.id.spielen_textview);
        buch = (TextView) findViewById(R.id.bücher_text);
        stern = (TextView) findViewById(R.id.star_textview);
        settings = (TextView) findViewById(R.id.settings_text);


        Typeface schrift = Schriftarttypeface();
        spielen.setTypeface(schrift);
        buch.setTypeface(schrift);
        stern.setTypeface(schrift);
        settings.setTypeface(schrift);


    }

    //Setzt die Schriftart
    public Typeface Schriftarttypeface(){
        AssetManager assetManager = getApplicationContext().getAssets();
        Typeface typeface = Typeface.createFromAsset(assetManager,"fonts/itckrist.ttf");
        return typeface;
    }

    /**
     * Wird aufgerufen, wenn auf das Spielen geklickt wird.
     * @param view
     */
    public void spielenclick(android.view.View view){


        Intent intent = new Intent(this, SelectTeamsActivity.class);
        startActivity(intent);
    }

    public void settingsclick(android.view.View view){

        Toast.makeText(getApplicationContext(),"Jetzt starten Einstellungen",Toast.LENGTH_SHORT).show();

    }
    public void buchclick(android.view.View view){

        Toast.makeText(getApplicationContext(),"So geht's!",Toast.LENGTH_SHORT).show();

        FragmentManager fragmentManager = getFragmentManager();
        TeamOptionsDialog teamOptionsDialog = new TeamOptionsDialog();
        teamOptionsDialog.show(fragmentManager, "TeamDialog");


    }

    public void starsclick(android.view.View view){

        Toast.makeText(getApplicationContext(),"Mehr Wörter",Toast.LENGTH_SHORT).show();
    }


}
