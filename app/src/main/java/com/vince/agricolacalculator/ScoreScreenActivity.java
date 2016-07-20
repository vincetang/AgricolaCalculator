package com.vince.agricolacalculator;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;

public class ScoreScreenActivity extends AppCompatActivity {

    private TableRow playersRow, fieldsRow, pasturesRow, grainsRow, vegetablesRow, sheepRow,
            wildBoarRow, cattleRow, unusedFarmRow, stablesRow, clayRoomsRow, stoneRoomsRow,
            familyMembersRow, bonusRow, totalRow;

    // Table layout parameters
    private static final TableRow.LayoutParams params = new TableRow.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
            ActionBar.LayoutParams.WRAP_CONTENT,1.0f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Score Screen");
        setSupportActionBar(toolbar);


        // Table Rows
        playersRow = (TableRow) findViewById(R.id.playersRow);
        fieldsRow = (TableRow) findViewById(R.id.fieldsRow);
        pasturesRow = (TableRow) findViewById(R.id.pasturesRow);
        grainsRow = (TableRow) findViewById(R.id.grainsRow);
        vegetablesRow = (TableRow) findViewById(R.id.vegetablesRow);
        sheepRow = (TableRow) findViewById(R.id.sheepRow);
        wildBoarRow = (TableRow) findViewById(R.id.wildBoarRow);
        cattleRow = (TableRow) findViewById(R.id.cattleRow);
        unusedFarmRow = (TableRow) findViewById(R.id.unusedFarmRow);
        stablesRow = (TableRow) findViewById(R.id.stablesRow);
        clayRoomsRow = (TableRow) findViewById(R.id.clayRoomsRow);
        stoneRoomsRow = (TableRow) findViewById(R.id.stoneRoomsRow);
        familyMembersRow = (TableRow) findViewById(R.id.familyMembersRow);
        bonusRow = (TableRow) findViewById(R.id.bonusRow);
        totalRow = (TableRow) findViewById(R.id.totalRow);

        fillTable();

    }

    private void fillTable() {
        for (Player player : MainActivity.players) {

            // player name
            TextView nameText = new TextView(this);
            nameText.setText(player.getName());
            nameText.setPadding(2, 0, 2, 0);

            nameText.setLayoutParams(params);

            int darkColour;
            int lightColour;
            if (player.getColour() == Player.PlayerColour.BLUE) {
                darkColour = 0xFF66b3ff;
                lightColour = 0xFFb3d9ff;
            } else if (player.getColour() == Player.PlayerColour.NATURAL) {
                darkColour = 0xFFdfbe9f;
                lightColour = 0xFFecd8c6;
            } else if (player.getColour() == Player.PlayerColour.PURPLE) {
                darkColour = 0xFFcc99ff;
                lightColour = 0xFFe5ccff;
            } else {
                darkColour = 0xFFff3333;
                lightColour = 0xFFff8080;
            }
            nameText.setBackgroundColor(darkColour);
            playersRow.addView(nameText);

            // all game points
            for ( Map.Entry<String, Integer> entry : player.getScores().entrySet() ){
                String key = entry.getKey();
                String value = entry.getValue().toString();

                TextView textView = new TextView(this);
                textView.setText(value);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundColor(lightColour);
                textView.setLayoutParams(params);
                TableRow row = getRow(key);
                row.addView(textView);
            }

            TextView totalText = new TextView(this);

            totalText.setText(String.format("%d", player.getTotalScore()));
            totalText.setLayoutParams(params);
            totalText.setTypeface(null, Typeface.BOLD);
            totalText.setBackgroundColor(darkColour);
            totalText.setGravity(Gravity.CENTER);
            totalRow.addView(totalText);
        }
    }

    private TableRow getRow(String key) {
        Log.d("ROW_PROCESSING", key);
        switch (key.toLowerCase()) {
            case "fields":
                return fieldsRow;
            case "pastures":
                return pasturesRow;
            case "grains":
                return grainsRow;
            case "vegetables":
                return vegetablesRow;
            case "sheep":
                return sheepRow;
            case "wild boar":
                return wildBoarRow;
            case "cattle":
                return cattleRow;
            case "unused farmyard spaces":
                return unusedFarmRow;
            case "stables":
                return stablesRow;
            case "clay hut rooms":
                return clayRoomsRow;
            case "stone house rooms":
                return stoneRoomsRow;
            case "family members":
                return familyMembersRow;
            case "bonus points":
                return bonusRow;
        }
        return null;
    }

}
