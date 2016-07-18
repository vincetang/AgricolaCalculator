package com.vince.agricolacalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class PlayerSelectActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnBlue, btnPurple, btnNatural, btnRed;
    private Player player;
    private EditText playerNameInput;

    private Button nextButton;

    private Player.PlayerColour playerColour;
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            toolbar.setTitle("Select Player Colour");
        }

        setSupportActionBar(toolbar);

        // Player nickname
        playerNameInput = (EditText) findViewById(R.id.playerName);
        playerNameInput.setSingleLine();
        playerNameInput.setImeOptions(EditorInfo.IME_ACTION_DONE);


        // set default text
        int playerNumber = MainActivity.players.size() + 1;
        playerName = "Player" + playerNumber;
        playerNameInput.setHint(playerName);

        // Buttons
        btnBlue = (ImageButton) findViewById(R.id.btnBlue);
        btnPurple = (ImageButton) findViewById(R.id.btnPurple);
        btnNatural = (ImageButton) findViewById(R.id.btnNatural);
        btnRed = (ImageButton) findViewById(R.id.btnRed);
        nextButton = (Button) findViewById(R.id.nextButton);

        // set listeners
        btnBlue.setOnClickListener(this);
        btnPurple.setOnClickListener(this);
        btnNatural.setOnClickListener(this);
        btnRed.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        // set images
        btnBlue.setImageResource(R.drawable.blue);
        btnPurple.setImageResource(R.drawable.purple);
        btnNatural.setImageResource(R.drawable.natural);
        btnRed.setImageResource(R.drawable.red);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBlue:
                playerColour = Player.PlayerColour.BLUE;
                break;
            case R.id.btnPurple:
                playerColour = Player.PlayerColour.PURPLE;
                break;
            case R.id.btnNatural:
                playerColour = Player.PlayerColour.NATURAL;
                break;
            case R.id.btnRed:
                playerColour = Player.PlayerColour.RED;
                break;
            case R.id.nextButton:
                // create player and add to players list
                player = new Player(playerName, playerColour);
                MainActivity.players.add(player);

                Intent intent = null;

                if (MainActivity.scoreMode == MainActivity.ScoreMode.POINTS) {
                    intent = new Intent(PlayerSelectActivity.this, ScoreByPoints.class);

                } else {
                    intent = new Intent(PlayerSelectActivity.this, ScoreByCount.class);
                }

                intent.putExtra("player", player);
                startActivity(intent);
                break;
        }

    }
}
