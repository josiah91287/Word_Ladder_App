package com.example.myapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class InstructionDialog extends AppCompatDialogFragment {
    public Dialog onCreateDialogue(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Instructions")
                .setMessage("You must enter 2 words of the same length, a 'beginning word'" +
                        "and and 'ending word'. The application will then attempt to construct a " +
                        "'ladder' between these 2 words by changing the beginning word 1 letter" +
                        "at a time. There may not be a ladder between 2 words of the same length")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }
}
