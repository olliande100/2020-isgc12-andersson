package com.evabui.labb2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int antalForsok;
    private String nuvarandeOrd;
    private List<Character> gissadeBokstaver;
    private char[] ordRepresentation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startaNyttSpel();
    }

    private void startaNyttSpel() {
        TextView v = findViewById(R.id.ordBox);
        nuvarandeOrd = new Ordgenerator().slumpaOrd();

        antalForsok = 7;
        gissadeBokstaver = new ArrayList<>();
        ordRepresentation = new char[nuvarandeOrd.length()];
        for(int i = 0; i < nuvarandeOrd.length(); i++) {
            ordRepresentation[i] = '_';
        }
        v.setText(new String(ordRepresentation));
        v = findViewById(R.id.antalBox);
        v.setText(String.format("Antal försök: %d", antalForsok));
        Button b = findViewById(R.id.okButton);
        b.setOnClickListener(this);
    }
     // hämtat från dokumentation https://developer.android.com/guide/topics/ui/notifiers/toasts
    private void visaToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void gissaBokstav (String bokstav) {
        char c = bokstav.charAt(0);
        if(gissadeBokstaver.contains(c)){
            // bokstaven redan gissad
            return;
        }
        gissadeBokstaver.add(c);

        if(nuvarandeOrd.contains(bokstav)) {
            for(int i = 0; i < nuvarandeOrd.length(); i++) {
                if(nuvarandeOrd.charAt(i) == c) {
                    ordRepresentation[i] = c;//multiplicera med 2
                }
            }
            TextView v = findViewById(R.id.ordBox);
            String s = new String(ordRepresentation);
            v.setText(s);
            if(s.equals(nuvarandeOrd)) {
               visaToast("VINST!!!!!!!");
               startaNyttSpel();
               return;
            }

        }
        else {
            TextView v = findViewById(R.id.antalBox);
            if(antalForsok - 1 == 0) {
                visaToast("FÖRLUST HAHAHA");
                startaNyttSpel();
                return;
            }
            antalForsok--;
            v.setText(String.format("Antal försök: %d", antalForsok));
        }
    }

    @Override
    public void onClick(View view) {
        EditText e = findViewById(R.id.gissaText);
        gissaBokstav(e.getText().toString());
    }
}