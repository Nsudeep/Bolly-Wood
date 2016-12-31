package com.example.tejas.bolly_wood;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class userInputActivity extends AppCompatActivity {

    private EditText movieInput;
    private TextView maskedMovieText;
    private String maskedMovie;
    private Button enterMovie;
    private Button continueButton;
    private TextView aiDecisionText;
    private EditText userPositionInput;
    private Button positionAccepted;
    private TextView message;
    private String guessesMadeString = "Incorrect Guesses:";
    private TextView incorrectGuesses;
    private char userLetter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_input);
        RelativeLayout userInputLayout = new RelativeLayout(this);

        movieInput = new EditText(this);
        movieInput.setId(20);
        enterMovie = new Button(this);
        aiDecisionText = new TextView(this);
        aiDecisionText.setId(40);
        userPositionInput = new EditText(this);
        maskedMovieText = new TextView(this);
        maskedMovieText.setId(21);
        maskedMovieText.setTextSize(22);
        enterMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maskedMovie = movieInput.getText().toString().toUpperCase();
                System.out.println("masked movie is " + maskedMovie);
                maskedMovieText.setText(maskedMovie);
                movieInput.setVisibility(View.INVISIBLE);
                enterMovie.setVisibility(View.INVISIBLE);
                continueButton.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(giveMovie.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);// to hide the keyboard
                return;
            }
        });

        continueButton = new Button(this);
        continueButton.setId(30);
        continueButton.setText("Continue");
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLetter = 'A';
                aiDecisionText.setText("The AI chose letter"+ userLetter +"  and enter positions. Press N for an incorrect guess");
                userPositionInput.setVisibility(View.VISIBLE);
                positionAccepted.setVisibility(View.VISIBLE);
                return;
            }
        });
        positionAccepted = new Button(this);
        positionAccepted.setText("OK");
        positionAccepted.setId(40);
        positionAccepted.setVisibility(View.INVISIBLE);
        message = new TextView(this);
        message.setId(60);
        positionAccepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = userPositionInput.getText().toString();
                String[] strArr = input.split(",");
                int len = strArr.length;
                int[] intArray = new int[len];
                int num, flag=0;

                if((strArr[0].charAt(0) == 'N') || (strArr[0].charAt(0) == 'n'))
                {
                    flag = 1;
                    char incorrectGuess = 'B';
                    guessesMadeString += incorrectGuess;
                    incorrectGuesses.setText(guessesMadeString);
                }

                for(int i = 0; i < len && flag== 0; i++) {

                    intArray[i] = Integer.parseInt(strArr[i]);
                    num = intArray[i];
                    StringBuilder mm = new StringBuilder(maskedMovie);
                    mm.setCharAt(num-1, userLetter);
                    maskedMovie = mm.toString();
                }

                maskedMovieText.setText(maskedMovie);

                if(maskedMovie.indexOf('_') == -1) {
                    message.setText("The AI was able to successfully guess the movie");
                    positionAccepted.setVisibility(View.INVISIBLE);
                    userPositionInput.setVisibility(View.INVISIBLE);
                }

                userPositionInput.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(giveMovie.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);// to hide the keyboard
                return;
            }
        });
        incorrectGuesses = new TextView(this);
        incorrectGuesses.setText(guessesMadeString);
        incorrectGuesses.setId(50);

        enterMovie.setText("ENTER");
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());
        movieInput.setWidth(px);
        RelativeLayout.LayoutParams movieInputDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        movieInputDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        userInputLayout.addView(movieInput,movieInputDetails);

        RelativeLayout.LayoutParams enterMovieDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        enterMovieDetails.addRule(RelativeLayout.RIGHT_OF,movieInput.getId());
        enterMovieDetails.addRule(RelativeLayout.ALIGN_BOTTOM,movieInput.getId());
        userInputLayout.addView(enterMovie,enterMovieDetails);

        movieInput.setVisibility(View.VISIBLE);
        enterMovie.setVisibility(View.VISIBLE);

        //setContentView(userInputLayout);
        maskedMovieText.setWidth(px);

        RelativeLayout.LayoutParams maskedMovieTextDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        maskedMovieTextDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        userInputLayout.addView(maskedMovieText,maskedMovieTextDetails);

        RelativeLayout.LayoutParams continueButtonDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        continueButtonDetails.addRule(RelativeLayout.RIGHT_OF,maskedMovieText.getId());
        continueButtonDetails.addRule(RelativeLayout.ALIGN_BOTTOM,maskedMovieText.getId());
        userInputLayout.addView(continueButton);
        continueButton.setVisibility(View.INVISIBLE);

        RelativeLayout.LayoutParams aiDecisionTextDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        aiDecisionTextDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        aiDecisionTextDetails.addRule(RelativeLayout.BELOW,continueButton.getId());
        userInputLayout.addView(aiDecisionText,aiDecisionTextDetails);

        userPositionInput.setVisibility(View.INVISIBLE);
        userPositionInput.setWidth(px);

        RelativeLayout.LayoutParams userPositionInputDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        userPositionInputDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        userPositionInputDetails.addRule(RelativeLayout.BELOW,aiDecisionText.getId());
        userInputLayout.addView(userPositionInput,userPositionInputDetails);

        RelativeLayout.LayoutParams positionAcceptedDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        positionAcceptedDetails.addRule(RelativeLayout.CENTER_VERTICAL);
        positionAcceptedDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //positionAcceptedDetails.addRule(RelativeLayout.BELOW,userPositionInput.getId());
        userInputLayout.addView(positionAccepted,positionAcceptedDetails);

        RelativeLayout.LayoutParams messageDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        messageDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        messageDetails.addRule(RelativeLayout.BELOW,aiDecisionText.getId());
        userInputLayout.addView(message,messageDetails);

        RelativeLayout.LayoutParams incorrectGuessesDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        incorrectGuessesDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        incorrectGuessesDetails.addRule(RelativeLayout.BELOW,message.getId());
        incorrectGuessesDetails.addRule(RelativeLayout.ALIGN_BOTTOM,message.getId());
        userInputLayout.addView(incorrectGuesses,incorrectGuessesDetails);

        setContentView(userInputLayout);



    }
}