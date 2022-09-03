package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private Button instructionButton;
    private AlertDialog.Builder instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instructionButton = (Button) findViewById(R.id.button3);
        instructions = new AlertDialog.Builder(this);
        instructionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInstructions();
            }
        });
    }

    public void openInstructions(){
        instructions.setTitle("Instructions")
                .setMessage("You must enter 2 words of the same length, a 'beginning word' " +
                    "and a 'ending word'. The application will then attempt to construct a " +
                    "'ladder' between these 2 words by changing the 'beginning word' 1 letter " +
                    "at a time. There may not be a ladder between 2 words of the same length.")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }

    public void launchBuilder(View v){
        Intent i = new Intent(this, BuilderActivity.class);
        startActivity(i);
    }


    public void disable(View v){
        v.setEnabled(false);
        Button b = (Button) v;
        b.setText("Disabled");
    }
}