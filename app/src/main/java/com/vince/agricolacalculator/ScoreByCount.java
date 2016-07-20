package com.vince.agricolacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.lang.reflect.Array;

public class ScoreByCount extends AppCompatActivity implements View.OnClickListener {

    public static final String[] ITEM_NAMES = { "fields", "pastures", "grains",
            "vegetables", "sheep", "wild boar", "cattle", "unused farmyard spaces",
            "stables", "clay hut rooms", "stone house rooms", "family members", "bonus points"};


    public static final String LOG_TAG = ScoreByCount.class.getCanonicalName();

    private ImageView itemImage;
    private TextView itemDescription;

    private Button btnPoints0, btnPoints1, btnPoints2, btnPoints3, btnPoints4, btnDone;
    private NumberPicker numberPicker;
    private int itemIndex;
    private String curItem, description;
    private Player player;
    private int playerIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_by_count);

        // Get intent extras
        Intent intent = getIntent();
        itemIndex = intent.getIntExtra("itemIndex", 0);
        curItem = ITEM_NAMES[itemIndex];
        playerIndex = intent.getIntExtra("playerIndex", -1);

        if (playerIndex == -1) {
            Log.e(ScoreByCount.class.getName(), "Error retrieving player index");
            return;
        }

        player = MainActivity.players.get(playerIndex);

        Log.d(LOG_TAG, "Got player " + player.getName());
        Log.d(LOG_TAG, "Colour: " + player.getColour());
        Log.d(LOG_TAG, "Scores: " + player.getScores().toString());

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle(curItem);
//        setSupportActionBar(toolbar);

        // Initialize image
        itemImage = (ImageView) findViewById(R.id.itemImage);

        //initialize description
        itemDescription = (TextView) findViewById(R.id.itemText);

        // Initialize Buttons
        btnPoints0 = (Button) findViewById(R.id.btnPoints0);
        btnPoints1 = (Button) findViewById(R.id.btnPoints1);
        btnPoints2 = (Button) findViewById(R.id.btnPoints2);
        btnPoints3 = (Button) findViewById(R.id.btnPoints3);
        btnPoints4 = (Button) findViewById(R.id.btnPoints4);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        btnDone = (Button) findViewById(R.id.btnDone);

        // Set onClick listeners
        btnPoints0.setOnClickListener(this);
        btnPoints1.setOnClickListener(this);
        btnPoints2.setOnClickListener(this);
        btnPoints3.setOnClickListener(this);
        btnPoints4.setOnClickListener(this);
        btnDone.setOnClickListener(this);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(15);

        if (itemIndex <= 6) {
            btnPoints0.setVisibility(View.VISIBLE);
            btnPoints1.setVisibility(View.VISIBLE);
            btnPoints2.setVisibility(View.VISIBLE);
            btnPoints3.setVisibility(View.VISIBLE);
            btnPoints4.setVisibility(View.VISIBLE);
            btnPoints0.setEnabled(true);
            btnPoints1.setEnabled(true);
            btnPoints2.setEnabled(true);
            btnPoints3.setEnabled(true);
            btnPoints4.setEnabled(true);

            numberPicker.setVisibility(View.INVISIBLE);
            numberPicker.setEnabled(false);
            btnDone.setVisibility(View.INVISIBLE);
            btnDone.setEnabled(false);
        } else {
            numberPicker.setVisibility(View.VISIBLE);
            numberPicker.setEnabled(true);
            btnDone.setVisibility(View.VISIBLE);
            btnDone.setEnabled(true);

            btnPoints0.setVisibility(View.INVISIBLE);
            btnPoints1.setVisibility(View.INVISIBLE);
            btnPoints2.setVisibility(View.INVISIBLE);
            btnPoints3.setVisibility(View.INVISIBLE);
            btnPoints4.setVisibility(View.INVISIBLE);
            btnPoints0.setEnabled(false);
            btnPoints1.setEnabled(false);
            btnPoints2.setEnabled(false);
            btnPoints3.setEnabled(false);
            btnPoints4.setEnabled(false);

        }

        update();

    }
    private void update() {
        updateItemImage();
        updateItemDescription();
        updateButtonText();
    }


    private void updateItemImage() {
        switch (itemIndex) {
            case 0:
                itemImage.setImageResource(R.drawable.placeholder);
                break;
            case 1:
                itemImage.setImageResource(R.drawable.placeholder);
                break;
            case 2:
                itemImage.setImageResource(R.drawable.placeholder);
                break;
            case 3:
                itemImage.setImageResource(R.drawable.placeholder);
                break;
            case 4:
                itemImage.setImageResource(R.drawable.placeholder);
                break;
            case 5:
                itemImage.setImageResource(R.drawable.placeholder);
                break;
            case 6:
                itemImage.setImageResource(R.drawable.placeholder);
                break;
            case 7:
                itemImage.setImageResource(R.drawable.placeholder);
                break;
            case 8:
                itemImage.setImageResource(R.drawable.placeholder);
                break;
            case 9:
                itemImage.setImageResource(R.drawable.placeholder);
                break;
            case 10:
                itemImage.setImageResource(R.drawable.placeholder);
                break;
            case 11:
                itemImage.setImageResource(R.drawable.placeholder);
                break;
            case 12:
                itemImage.setImageResource(R.drawable.placeholder);
                break;
        }
    }

    private void updateItemDescription() {
        description = "How many <b>" + curItem + "</b> do you have?";
        itemDescription.setText(Html.fromHtml(description));
    }

    private void updateButtonText() {
       switch (itemIndex) {
           case 0:
               setButtonText("0-1", "2", "3", "4", "5+");
               break;
           case 1:
           case 3:
               setButtonText("0", "1", "2", "3", "4+");
               break;
           case 2:
           case 4:
               setButtonText("0", "1-3", "4-5", "6-7", "8+");
               break;
           case 5:
               setButtonText("0", "1-2", "3-4", "5-6", "7+");
               break;
           case 6:
               setButtonText("0", "1", "2-3", "4-5", "6+");
               break;
       }
    }

    private void setButtonText(String btn0Text, String btn1Text,
                               String btn2Text, String btn3Text,
                               String btn4Text) {
        btnPoints0.setText(btn0Text);
        btnPoints1.setText(btn1Text);
        btnPoints2.setText(btn2Text);
        btnPoints3.setText(btn3Text);
        btnPoints4.setText(btn4Text);
    }

    @Override
    public void onClick(View v) {
        // Add appropriate score
        int pointsToAdd = 0;
        switch (v.getId()) {
            case R.id.btnPoints0:
                pointsToAdd = -1;
                break;
            case R.id.btnPoints1:
                pointsToAdd = 1;
                break;
            case R.id.btnPoints2:
                pointsToAdd = 2;
                break;
            case R.id.btnPoints3:
                pointsToAdd = 3;
                break;
            case R.id.btnPoints4:
                pointsToAdd = 4;
                break;
            case R.id.btnDone:
                switch (itemIndex) {
                    case 7:
                        pointsToAdd = -1 * numberPicker.getValue();
                        break;
                    case 8:
                    case 9:
                        pointsToAdd = numberPicker.getValue();
                        break;
                    case 10:
                        pointsToAdd = 2 * numberPicker.getValue();
                        break;
                    case 11:
                        pointsToAdd = 3 * numberPicker.getValue();
                        break;
                }
        }
        player.addScore(curItem, pointsToAdd);

        if (itemIndex < ITEM_NAMES.length - 1) {
            // Load next intent
            Intent intent = new Intent(ScoreByCount.this, ScoreByCount.class);
            intent.putExtra("player", player);
            intent.putExtra("playerIndex", playerIndex);
            intent.putExtra("itemIndex", itemIndex + 1);
            startActivity(intent);
        } else {
            // Load the next player
            Intent intent = new Intent(ScoreByCount.this, PlayerSelectActivity.class);
            startActivity(intent);
        }

    }
}
