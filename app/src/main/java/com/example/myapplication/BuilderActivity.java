package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class BuilderActivity extends AppCompatActivity {
    ProgressBar pb;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.builder_activity);

        pb = findViewById(R.id.pb);
        textView = (TextView) findViewById(R.id.textView);
    }


    public void handleText(View v) {
        textView.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);

        EditText start = findViewById(R.id.startingWord);//Gets the starting word input from the application
        String startInput = start.getText().toString();

        EditText end = findViewById(R.id.endingWord);//Gets the ending word input from the application
        String endInput = end.getText().toString();

        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setVisibility(View.VISIBLE);
                //generateLadder(startInput, endInput);
                buildLadder(startInput, endInput);
            }
        }, 1000);
    }


    public void buildLadder(String startingWord, String endingWord) {
        InputStream is = getFile("dictionary");
        DictionaryBuilder d1 = new DictionaryBuilder(is);
        Collection<String> listOfWordsLength = null;

        try {
            listOfWordsLength = d1.getWordsOfLength(startingWord.length());
        } catch (IOException ex) {
            ex.getMessage();
        }
        LadderBuilder ladder = new LadderBuilder(listOfWordsLength);

        try {
            Deque<String> finalLadder = ladder.buildLadder(startingWord, endingWord);
            if (finalLadder == null) {//There is no ladder between the two words
                textToScreen("Error: There is no link between the words " + startingWord + " and "
                        + endingWord + ".");
            } else {
                textToScreen(finalLadder.toString());
            }
        } catch (IllegalArgumentException ex) {//The words are not of the same length.
            textToScreen("Error: The words " + startingWord + " and " + endingWord + " are not "
                    + "the same length.");
        }
    }

    public void textToScreen(String text) {
        textView.setText(text);
        pb.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.VISIBLE);
    }

    private InputStream getFile(String fileName) {
        InputStream file = null;
        try {
            file = getAssets().open(fileName);
        } catch (IOException ex) {
            ex.getMessage();
        }
        return file;
    }
}