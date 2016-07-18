package com.vince.agricolacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    public  enum GameMode { EASY, NORMAL, HARD }
    public  enum ScoreMode { COUNT, POINTS }

    public static GameMode gameMode;
    public static ScoreMode scoreMode;
    public static ArrayList<Player> players;

    private Spinner spinner;
    private Button scoreByCount, scoreByPoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            toolbar.setTitle("Agricola Calculator");
        }

        setSupportActionBar(toolbar);

        setGameModeSpinner();

        scoreByCount = (Button) findViewById(R.id.scoreByCount);
        scoreByPoints = (Button) findViewById(R.id.scoreByPoints);

        scoreByCount.setOnClickListener(this);
        scoreByPoints.setOnClickListener(this);

        players = new ArrayList<Player>();
    }

    private void setGameModeSpinner() {
        spinner = (Spinner) findViewById(R.id.mode_spinner);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mode_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        if (spinner != null) {
            spinner.setAdapter(adapter);

            // Set default value to Normal
            spinner.setSelection(1);

            spinner.setOnItemSelectedListener(this);
        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected
        String modeSelected = (String) parent.getItemAtPosition(position);
        switch (modeSelected.toLowerCase()) {
            case "easy":
                gameMode = GameMode.EASY;
                break;
            case "normal":
                gameMode = GameMode.NORMAL;
                break;
            case "hard":
                gameMode = GameMode.HARD;
                break;
            default:
                gameMode = GameMode.NORMAL;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        gameMode = GameMode.NORMAL;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scoreByCount:
                scoreMode = ScoreMode.COUNT;
                break;
            case R.id.scoreByPoints:
                scoreMode = ScoreMode.POINTS;
                break;
            default:
                scoreMode = ScoreMode.POINTS;
        }

        // load up player selection activity
        Intent intent = new Intent(MainActivity.this, PlayerSelectActivity.class);
        startActivity(intent);
    }

    public static boolean isColourAvailable(String colour) {
        for (Player player : players) {
            if (player.getColour() == colour) {
                return false;
            }
        }
        return true;
    }

}
