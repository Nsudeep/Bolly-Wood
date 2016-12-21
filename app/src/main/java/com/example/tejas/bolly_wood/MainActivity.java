package com.example.tejas.bolly_wood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Inside OnCreate.");
    }

    // Start Game : Easy
    public void startGameEasy(View view) {
        Log.i(TAG, "Inside startGameEasy.");
        Intent intent = new Intent(this, giveMovie.class);
        startActivity(intent);
    }

    // Start Game : Medium
    public void startGameMedium(View view) {
        Log.i(TAG, "Inside startGameMedium.");
    }

    // Start Game : Hard
    public void startGameHard(View view) {
        Log.i(TAG, "Inside startGameHard.");
    }

    // Show High Scores
    public void showScores(View view) {
        Log.i(TAG, "Inside showScores.");
    }

    // Show Rules.
    public void showRules(View view) {
        Log.i(TAG, "Inside showRules.");
    }
}
