package com.example.tejas.bolly_wood;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.*;
import java.util.*;
import android.util.Log;

/**
 * Created by tejas on 26/12/16.
 */

public class hangman {
    private String givenString;
    private ArrayList movieList;
    private Set<Character> evidenceAbsent;
    private Map evidencePresent;
    private char guess;
    private static final String TAG = "hangmanLogs";
    hangman(String s, Context appContext)  {
        givenString = s;
        movieList = new ArrayList();
        evidencePresent = new HashMap();
        evidenceAbsent = new HashSet<Character>();
        Log.i(TAG, "The given string is " + givenString);
        evidenceAbsent.add('A');
        evidenceAbsent.add('E');
        evidenceAbsent.add('I');
        evidenceAbsent.add('O');
        evidenceAbsent.add('U');
        // Set the evidence present and evidence absent.
        for(int i=0; i<givenString.length(); i++) {
            if(givenString.charAt(i) != '_') {
                evidencePresent.put(i, givenString.charAt(i));
                evidenceAbsent.remove(givenString.charAt(i));
            }
        }
        Log.i(TAG, "Evidence Present " + evidencePresent.toString());
        Log.i(TAG, "Evidence Absent " + evidenceAbsent.toString());
        int movieLength = s.length();

        // Load the list of movies with length equal to the given movie length.
        AssetManager am = appContext.getAssets();
        String fileName = "movies_" + String.valueOf(movieLength) + ".txt";
        Log.i(TAG, "Opening " + fileName);
        BufferedReader reader;
        try {
            reader = new BufferedReader(
                new InputStreamReader(appContext.getAssets().open(fileName)));
            String movieName;
            //Log.i(TAG, "Following movies match the given movie length:");
            movieName = reader.readLine();
            //Log.i(TAG, "The first movie is " + movieName);
            while((movieName = reader.readLine())!=null) {
                //Log.i(TAG, movieName);
                movieList.add(movieName);
            }
        }
        catch(IOException ex) {
            Log.i(TAG, "Caught IOException while trying to open " + fileName);
        }

    }
    public int probEvidenceGivenWord(String w) {
        for(int i=0; i<w.length(); i++) {
            char c = w.charAt(i);
            if(evidenceAbsent.contains(c))
                return 0;
            if(evidencePresent.get(i) == null && evidencePresent.values().contains(c))
                return 0;
            if(evidencePresent.get(i) != null && evidencePresent.get(i).toString().charAt(0) != c)
                return 0;
        }
        return 1;
    }
    public int probLetterGivenEvidence(char l) {
        int prob = 0;
        for(int i=0; i<movieList.size(); i++) {
            //Log.i(TAG, "prob word " + movieList.get(i) + " given evidence for letter " + l + " is " + probEvidenceGivenWord(movieList.get(i).toString()));
            if(movieList.get(i).toString().indexOf(l) == -1)
                continue;
            prob += probEvidenceGivenWord(movieList.get(i).toString());
        }
        return prob;
    }
    public char nextGuess() {
        String letters = "BCDFGHJKLMNPQRSTVWXYZ0123456789";
        int maxProb = 0;
        int maxIdx = 0;
        int prob;
        char c;
        for(int i=0; i<letters.length(); i++) {
            c = letters.charAt(i);
            if(evidenceAbsent.contains(c) == false && evidencePresent.containsValue(c) == false) {
                prob = probLetterGivenEvidence(c);
                Log.i(TAG, "The prob. of " + c + " = " + prob);
                if(prob > maxProb) {
                    maxProb = prob;
                    maxIdx = i;
                }
            }
        }
        guess = letters.charAt(maxIdx);
        return guess;
    }
    public void feedback(boolean isCorrect, ArrayList pos) {
        if(isCorrect == false) {
            evidenceAbsent.add(guess);
        }
        else {
            for (int i = 0; i < pos.size(); i++) {
                if(evidencePresent.get(pos.get(i)) != null) {
                    Log.i(TAG, "Wrong input given by the user, ignoring ...");
                }
                else {
                    evidencePresent.put(pos.get(i), guess);
                }
            }
        }
        Log.i(TAG, "After Feedback for letter " + guess);
        Log.i(TAG, "Evidence Present: " + evidencePresent);
        Log.i(TAG, "Evidence Absent: " + evidenceAbsent);
    }
}
