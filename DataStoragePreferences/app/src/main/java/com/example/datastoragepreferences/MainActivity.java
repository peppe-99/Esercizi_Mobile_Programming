package com.example.datastoragepreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout highscores;
    private ArrayList<TextView> tvScores;
    private TextView tvScore;
    private int score = 0;
    private int [] scores = {0,0,0};
    private String [] names = {"","",""};

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Otteniamo i riferimenti ai widget di nostro interesse
        highscores = findViewById(R.id.listHighscores);
        tvScore = findViewById(R.id.tvScore);

        //Creiamo un arraylist che avrà le TextView dei 3 punteggi migliori
        tvScores = new ArrayList<>();
        tvScores.add((TextView) highscores.getChildAt(0));
        tvScores.add((TextView) highscores.getChildAt(1));
        tvScores.add((TextView) highscores.getChildAt(2));

        //Otteniamo il riferimento alla preferenza in cui scriveremo/leggeremo i 3 punteggi migliori
        sharedPreferences = getSharedPreferences("highscores", MODE_PRIVATE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Quando l'activity si trova nello stato di resume (quando parte o ci ritorniamo)
        //andiamo a prendere dal file di preferenza
        //i nomdi ed i punteggi dei primi 3 classificati

        scores[0] = sharedPreferences.getInt("FIRST", 0);
        scores[1] = sharedPreferences.getInt("SECOND", 0);
        scores[2] = sharedPreferences.getInt("THIRD", 0);

        names[0] = sharedPreferences.getString("NAME1", "FIRST");
        names[1] = sharedPreferences.getString("NAME2", "SECOND");
        names[2] = sharedPreferences.getString("NAME3", "THIRD");

        //Li andiamo a settare nella UI
        int i = 0;
        for(TextView tv : tvScores) {
            tv.setText(names[i] + ":" + scores[i]);
            i++;
        }

        //Settiamo anche il punteggio
        //importante per non perderlo quando ruotiamo la device
        score = sharedPreferences.getInt("SCORE", score);
        tvScore.setText(score+"");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //Quando l'app viene distrutta, andiamo a scrivere il punteggio attuale del giocatore
        //nel file delle preferenze invece che nel bundle
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("SCORE", score);
        editor.commit();
        super.onSaveInstanceState(outState);
    }

    public void increaseScore(View view) {
        score++;
        tvScore.setText(score + "");
    }

    public void decreaseScore(View view) {
        if(score > 0) {
            score--;
            tvScore.setText(score + "");
        }
    }

    public void resetGame(View view) {
        if(score != 0) {
            score = 0;
            tvScore.setText(score + "");
        }
    }

    public void endGame(View view) {
        //se il giocatore ha ottenuto un punteggio superiore a quello del terzo migliore
        if (score > scores[2]) {
            //tramite l'intent lanciamo l'activity HighScores e le passiamo il punteggio del giocatore
            Intent i = new Intent();
            i.setClass(getApplicationContext(), HighScores.class);
            i.putExtra("SCORE", score);
            startActivity(i);
        }
    }

    public void resetHighscores(View view) {
        for(int i  = 0; i < 3; i++) {
            scores[i] = 0;
        }
        names[0] = "FIRST";
        names[1] = "SECOND";
        names[2] = "THIRD";

        //Per resettare la classifica andiamo a sovrascrivere i dati presenti nel file di preferenze
        //infatti andiamo a scrivere i nomi ed i punteggi di default


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("FIRST", scores[0]);
        editor.putInt("SECOND", scores[1]);
        editor.putInt("THIRD", scores[2]);
        editor.putString("NAME1", names[0]);
        editor.putString("NAME2", names[1]);
        editor.putString("NAME3", names[2]);
        editor.commit();

        int i = 0;
        for(TextView tv : tvScores) {
            tv.setText(names[i] + ":" + scores[i]);
            i++;
        }
    }
}