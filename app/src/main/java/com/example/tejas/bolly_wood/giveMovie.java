package com.example.tejas.bolly_wood;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import java.util.Random;

public class giveMovie extends AppCompatActivity {

    private static final String TAG = "giveMovieLogs";
    //private String movieName = "ek aur ek gyarah";
    private String movieName;
    String[] movieArray = {"ek aur ek gyarah","godfather","the shining","psycho","apocalypse now","Apollo 13"};
    private TextView displayMaskedMovieName;
    private TextView promptNextGuess;
    private TextView numGuessesRemaining;
    private TextView guessesRemaining;
    private TextView guessesMade;
    private TextView scoreView;
    private TextView scoreLabel;
    private TextView successMessage;
    private EditText playerGuess;
    private Button enterGuess;
    private Button proceed;
    //private String guessedLetters = "aeiou";
    private String guessedLetters = "AEIOU";
    //private String guessesMadeString = "Guesses made: ";
    private String guessesMadeString = "Incorrect Guesses:";
    private int valueGuessRemaining = 10;
    private int score = 0;


    public void userMovieInput(View v) {
        Log.i(TAG, "Inside UserMovieInput().");
        Intent intent = new Intent(this, userInputActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_give_movie);
        // Layout
        RelativeLayout giveMovieLayout = new RelativeLayout(this);

        // Text View - Masked Movie name.
        //TextView displayMaskedMovieName = new TextView(this);
        int rnd = new Random().nextInt(movieArray.length);
        movieName = movieArray[rnd].toUpperCase();
        displayMaskedMovieName = new TextView(this);
        String maskedMovieName = maskMovieName(movieName, guessedLetters);
        displayMaskedMovieName.setText(maskedMovieName);
        displayMaskedMovieName.setTextSize(22);
        displayMaskedMovieName.setId(1);

        // Edit Text - User guess.
        //EditText playerGuess = new EditText(this);
        playerGuess = new EditText(this);
        //playerGuess.setTextSize(22);
        InputFilter[] charFilter = new InputFilter[1];
        charFilter[0] = new InputFilter.LengthFilter(1);
        playerGuess.setFilters(charFilter);
        playerGuess.setId(2);

        // Text View - Prompt Next Guess
        //final TextView promptNextGuess = new TextView(this);
        promptNextGuess = new TextView(this);
        promptNextGuess.setText("Next Guess");
        promptNextGuess.setTextSize(22);
        promptNextGuess.setId(3);

        // Text View - Guesse Made
        guessesMade = new TextView(this);
        guessesMade.setText(guessesMadeString);
        guessesMade.setId(6);

        scoreView = new TextView(this);
        scoreView.setText(String.valueOf(score));
        scoreView.setId(8);
        successMessage = new TextView(this);

        proceed = new Button(this);
        // Button - Enter the guess.
        enterGuess = new Button(this);
        enterGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(giveMovie.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);// to hide the keyboard
                char playerGuessValue = playerGuess.getText().toString().charAt(0);
                playerGuessValue = Character.toUpperCase(playerGuessValue);
                if(guessedLetters.indexOf(playerGuessValue)!=-1) {
                    playerGuess.setText("");
                    imm = (InputMethodManager) getSystemService(giveMovie.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    return;
                }
                guessedLetters = guessedLetters + playerGuessValue;
                //guessesMadeString = guessesMadeString + playerGuessValue;
                guessesMade.setText(guessesMadeString);
                if(movieName.indexOf(playerGuessValue) == -1) {
                    valueGuessRemaining--;
                    guessesMadeString = guessesMadeString + playerGuessValue;
                    guessesMade.setText(guessesMadeString);
                    numGuessesRemaining.setText(String.valueOf(valueGuessRemaining));

                }
                else {
                    String maskedMovieName = maskMovieName(movieName, guessedLetters);
                    System.out.println(maskedMovieName);
                    if(maskedMovieName.indexOf('_') == -1) {
                        successMessage.setText(R.string.successMessage);
                        proceed.setVisibility(View.VISIBLE);
                        score++;
                        scoreView.setText(String.valueOf(score));;
                    }
                    displayMaskedMovieName.setText(maskedMovieName);

                }
                imm = (InputMethodManager) getSystemService(giveMovie.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                playerGuess.setText("");
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userMovieInput(v);
            }
        });

        enterGuess.setText("Enter");
        proceed.setText("PROCEED");
        // Text View - Number of Guesses Remaining
        guessesRemaining = new TextView(this);
        guessesRemaining.setText("Number of Guesses Remaining");
        guessesRemaining.setId(4);
        numGuessesRemaining = new TextView(this);
        numGuessesRemaining.setText(String.valueOf(valueGuessRemaining));
        numGuessesRemaining.setId(5);

        scoreLabel = new TextView(this);
        scoreLabel.setText("Score: ");
        scoreLabel.setId(8);

        // Add rules.
        // tex View - masked movie name.
        RelativeLayout.LayoutParams maskedMovieNamesDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        maskedMovieNamesDetails.addRule(RelativeLayout.CENTER_VERTICAL);
        maskedMovieNamesDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        giveMovieLayout.addView(displayMaskedMovieName, maskedMovieNamesDetails);
        // editText - User guess.
        RelativeLayout.LayoutParams  playerGuessDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        playerGuessDetails.addRule(RelativeLayout.BELOW, displayMaskedMovieName.getId());
        playerGuessDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        playerGuessDetails.addRule(RelativeLayout.CENTER_VERTICAL);
        playerGuessDetails.setMargins(20,0,0,0);
        giveMovieLayout.addView(playerGuess, playerGuessDetails);
        // text View - prompt Next Guess.
        RelativeLayout.LayoutParams promptNextGuessDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        promptNextGuessDetails.addRule(RelativeLayout.LEFT_OF, playerGuess.getId());
        promptNextGuessDetails.addRule(RelativeLayout.ALIGN_BOTTOM, playerGuess.getId());
        promptNextGuessDetails.setMargins(0,0,20,0);
        giveMovieLayout.addView(promptNextGuess, promptNextGuessDetails);
        // Button - Get the guess.
        RelativeLayout.LayoutParams enterGuessDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        enterGuessDetails.addRule(RelativeLayout.RIGHT_OF, playerGuess.getId());
        enterGuessDetails.addRule(RelativeLayout.ALIGN_BOTTOM, playerGuess.getId());
        giveMovieLayout.addView(enterGuess, enterGuessDetails);
        // text View - No. of guesses remaining.
        RelativeLayout.LayoutParams guessesRemainingDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        guessesRemainingDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        guessesRemainingDetails.setMargins(0, 20, 0, 0);
        giveMovieLayout.addView(guessesRemaining, guessesRemainingDetails);
        RelativeLayout.LayoutParams numGuessesRmainingDetails =  new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        numGuessesRmainingDetails.addRule(RelativeLayout.BELOW, guessesRemaining.getId());
        numGuessesRmainingDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        numGuessesRmainingDetails.setMargins(0, 20, 0, 0);
        giveMovieLayout.addView(numGuessesRemaining, numGuessesRmainingDetails);
        // Text View - Guesses Made.
        RelativeLayout.LayoutParams guessesMadeDetails =  new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        guessesMadeDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        guessesMadeDetails.addRule(RelativeLayout.BELOW, numGuessesRemaining.getId());
        guessesMadeDetails.setMargins(0, 20, 0, 0);
        giveMovieLayout.addView(guessesMade, guessesMadeDetails);

        setContentView(giveMovieLayout);

        RelativeLayout.LayoutParams scoreViewDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        scoreViewDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        scoreViewDetails.addRule(RelativeLayout.BELOW, guessesMade.getId());
        giveMovieLayout.addView(scoreLabel,scoreViewDetails);

        RelativeLayout.LayoutParams scoreValueDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        scoreValueDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        scoreValueDetails.addRule(RelativeLayout.ALIGN_PARENT_END, scoreLabel.getId());
        giveMovieLayout.addView(scoreView,scoreValueDetails);

        RelativeLayout.LayoutParams successMessageDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );


        successMessageDetails.addRule(RelativeLayout.BELOW, enterGuess.getId());
        successMessageDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        giveMovieLayout.addView(successMessage,successMessageDetails);

        RelativeLayout.LayoutParams proceedDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        proceedDetails.addRule(RelativeLayout.RIGHT_OF, successMessage.getId());
        proceedDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        giveMovieLayout.addView(proceed, proceedDetails);
        proceed.setVisibility(View.INVISIBLE);
        //proceed.setVisibility(View.VISIBLE);
    }
    protected String maskMovieName(String movieName, String guessedLetters) {
        String maskedName = "";
        for(int i=0; i<movieName.length(); i++) {
            if(movieName.charAt(i) == ' ') {
                maskedName = maskedName + "/ ";
            }
            else if(guessedLetters.indexOf(movieName.charAt(i))!=-1) {
                maskedName = maskedName + movieName.charAt(i) + ' ';
            }
            else {
                maskedName = maskedName + "_ ";
            }
        }
        return maskedName;
    }
}