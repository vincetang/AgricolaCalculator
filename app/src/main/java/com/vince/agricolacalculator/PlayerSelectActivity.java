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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class PlayerSelectActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnBlue, btnPurple, btnNatural, btnRed;
    private Player player;
    private EditText playerNameInput;
    private ImageView checkMark;
    private Button nextButton, noMorePlayersButton;
    private boolean colourSelected;
    private Player.PlayerColour playerColour;
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Maxed out players
        if (MainActivity.players.size() == 4) {
            Intent i = new Intent(PlayerSelectActivity.this, ScoreScreenActivity.class);
            startActivity(i);
        }

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
        noMorePlayersButton = (Button) findViewById(R.id.noMorePlayersButton);

        // set listeners
        btnBlue.setOnClickListener(this);
        btnPurple.setOnClickListener(this);
        btnNatural.setOnClickListener(this);
        btnRed.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        noMorePlayersButton.setOnClickListener(this);

        // set images
        btnBlue.setImageResource(R.drawable.blue);
        btnPurple.setImageResource(R.drawable.purple);
        btnNatural.setImageResource(R.drawable.natural);
        btnRed.setImageResource(R.drawable.red);
        checkMark = (ImageView) findViewById(R.id.checkMark);

        checkMark.setVisibility(View.INVISIBLE);

        colourSelected = false;

        if (MainActivity.players.size() == 0) {
            noMorePlayersButton.setEnabled(false);
            noMorePlayersButton.setVisibility(View.INVISIBLE);
        } else {
            noMorePlayersButton.setEnabled(true);
            noMorePlayersButton.setVisibility(View.VISIBLE);
        }

        if (!MainActivity.isColourAvailable("blue")) {
            btnBlue.setEnabled(false);
            btnBlue.setAlpha(.2f);
        }
        if (!MainActivity.isColourAvailable("purple")) {
            btnPurple.setEnabled(false);
            btnPurple.setAlpha(.2f);
        }
        if (!MainActivity.isColourAvailable("red")) {
            btnRed.setEnabled(false);
            btnRed.setAlpha(.2f);
        }
        if (!MainActivity.isColourAvailable("natural")) {
            btnNatural.setEnabled(false);
            btnNatural.setAlpha(.2f);
        }

    }

    private void setCheckPosition(ImageButton imageButton) {
        checkMark.setVisibility(View.VISIBLE);
        checkMark.setX(imageButton.getX() + 30);
        checkMark.setY(imageButton.getY() + 30);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnBlue:
                playerColour = Player.PlayerColour.BLUE;
                setCheckPosition(btnBlue);
                colourSelected = true;
                break;
            case R.id.btnPurple:
                playerColour = Player.PlayerColour.PURPLE;
                setCheckPosition(btnPurple);
                colourSelected = true;
                break;
            case R.id.btnNatural:
                playerColour = Player.PlayerColour.NATURAL;
                setCheckPosition(btnNatural);
                colourSelected = true;
                break;
            case R.id.btnRed:
                playerColour = Player.PlayerColour.RED;
                setCheckPosition(btnRed);
                colourSelected = true;
                break;
            case R.id.nextButton:
                // create player and add to players list
                if (!colourSelected) {
                    Toast.makeText(this, "Choose a colour", Toast.LENGTH_SHORT).show();
                } else {

                    playerName = playerNameInput.getText().toString().matches("") ?
                            playerNameInput.getHint().toString() :
                            playerNameInput.getText().toString();

                    player = new Player(playerName, playerColour);
                    int playerIndex = MainActivity.players.size();
                    MainActivity.players.add(player);

                    Intent intent = null;

                    if (MainActivity.scoreMode == MainActivity.ScoreMode.POINTS) {
                        intent = new Intent(PlayerSelectActivity.this, ScoreByPoints.class);

                    } else {
                        intent = new Intent(PlayerSelectActivity.this, ScoreByCount.class);
                    }
                    intent.putExtra("itemIndex", 0);
                    intent.putExtra("playerIndex", playerIndex);
                    startActivity(intent);
                }
                break;
            case R.id.noMorePlayersButton:
                // show scores
                Intent i = new Intent(PlayerSelectActivity.this, ScoreScreenActivity.class);
                startActivity(i);
                break;
        }

    }
}
