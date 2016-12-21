package com.example.tejas.bolly_wood;

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
    String[] movieArray = {"ek aur ek gyarah","godfather","the shining"};
    private TextView displayMaskedMovieName;
    private TextView promptNextGuess;
    private TextView numGuessesRemaining;
    private TextView guessesRemaining;
    private TextView guessesMade;
    private EditText playerGuess;
    private Button enterGuess;
    //private String guessedLetters = "aeiou";
    private String guessedLetters = "AEIOU";
    //private String guessesMadeString = "Guesses made: ";
    private String guessesMadeString = "Incorrect Guesses:";
    private int valueGuessRemaining = 10;
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


        // Button - Enter the guess.
        enterGuess = new Button(this);
        enterGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char playerGuessValue = playerGuess.getText().toString().charAt(0);
                playerGuessValue = Character.toUpperCase(playerGuessValue);
                if(guessedLetters.indexOf(playerGuessValue)!=-1) {
                    playerGuess.setText("");
                    InputMethodManager imm = (InputMethodManager) getSystemService(giveMovie.INPUT_METHOD_SERVICE);
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
                    displayMaskedMovieName.setText(maskedMovieName);
                }
                playerGuess.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(giveMovie.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
        enterGuess.setText("Enter");

        // Text View - Number of Guesses Remaining
        guessesRemaining = new TextView(this);
        guessesRemaining.setText("Number of Guesses Remaining");
        guessesRemaining.setId(4);
        numGuessesRemaining = new TextView(this);
        numGuessesRemaining.setText(String.valueOf(valueGuessRemaining));
        numGuessesRemaining.setId(5);



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
            /*switch(movieName.charAt(i)) {
                case 'a': maskedName = maskedName + movieName.charAt(i) + ' '; break;
                case 'e': maskedName = maskedName + movieName.charAt(i) + ' '; break;
                case 'i': maskedName = maskedName + movieName.charAt(i) + ' '; break;
                case 'o': maskedName = maskedName + movieName.charAt(i) + ' '; break;
                case 'u': maskedName = maskedName + movieName.charAt(i) + ' '; break;
                case ' ': maskedName = maskedName + "/ "; break;
                default: maskedName = maskedName + "_ "; break;
            }*/
        }
        return maskedName;
    }
}