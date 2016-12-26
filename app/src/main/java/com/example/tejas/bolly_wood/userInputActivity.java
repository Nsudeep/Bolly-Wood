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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_input);
        RelativeLayout userInputLayout = new RelativeLayout(this);

        movieInput = new EditText(this);
        movieInput.setId(20);
        enterMovie = new Button(this);
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
        continueButton.setText("Continue");
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });
        maskedMovieText = new TextView(this);
        maskedMovieText.setId(21);
        maskedMovieText.setTextSize(22);
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

        setContentView(userInputLayout);

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


    }
}
