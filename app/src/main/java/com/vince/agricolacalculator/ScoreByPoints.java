package com.vince.agricolacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class ScoreByPoints extends AppCompatActivity {


    private Player player;
    private int playerIndex, itemIndex;
    private String curItem;

    private ImageView imageView;
    private NumberPicker numberPicker;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_by_points);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Get intent extras
        Intent intent = getIntent();
        itemIndex = intent.getIntExtra("itemIndex", 0);
        curItem = ScoreByCount.ITEM_NAMES[itemIndex];
        playerIndex = intent.getIntExtra("playerIndex", -1);

        if (playerIndex == -1) {
            Log.e(ScoreByCount.class.getName(), "Error retrieving player index");
            return;
        }

        player = MainActivity.players.get(playerIndex);
    }

}
