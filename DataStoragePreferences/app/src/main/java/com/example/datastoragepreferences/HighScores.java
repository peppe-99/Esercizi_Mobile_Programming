package com.example.datastoragepreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HighScores extends AppCompatActivity {

    private LinearLayout highscores;
    private ArrayList<TextView> tvScores = new ArrayList<>();
    private EditText etName;
    private int score;
    private int [] scores = new int[3];
    private String [] names = new String[3];

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        score = getIntent().getIntExtra("SCORE", -1);

        highscores = findViewById(R.id.listHighscores);
        tvScores.add((TextView) highscores.getChildAt(0));
        tvScores.add((TextView) highscores.getChildAt(1));
        tvScores.add((TextView) highscores.getChildAt(2));
        etName = findViewById(R.id.etName);

        sharedPreferences = getSharedPreferences("highscores", MODE_PRIVATE);

        scores[0] = sharedPreferences.getInt("FIRST", 0);
        scores[1] = sharedPreferences.getInt("SECOND", 0);
        scores[2] = sharedPreferences.getInt("THIRD", 0);

        names[0] = sharedPreferences.getString("NAME1", "FIRST");
        names[1] = sharedPreferences.getString("NAME2", "SECOND");
        names[2] = sharedPreferences.getString("NAME3", "THIRD");

        int i = 0;
        for(TextView tv : tvScores) {
            tv.setText(names[i] + ":" + scores[i]);
            i++;
        }

    }

    public void insertName(View view) {
        String name = etName.getText().toString();
        if(score > scores[2]) {
            if(score > scores[1]) {
                if(score > scores[0]) {
                    names[2] = names[1];
                    names[1] = names[0];
                    names[0] = name;
                    scores[2] = scores[1];
                    scores[1] = scores[0];
                    scores[0] = score;
                } else {
                    names[2] = names[1];
                    names[1] = name;
                    scores[2] = scores[1];
                    scores[1] = score;
                }
            } else {
                names[2] = name;
                scores[2] = score;
            }
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("FIRST", scores[0]);
        editor.putInt("SECOND", scores[1]);
        editor.putInt("THIRD", scores[2]);
        editor.putString("NAME1", names[0]);
        editor.putString("NAME2", names[1]);
        editor.putString("NAME3", names[2]);
        editor.putInt("SCORE", 0);
        editor.commit();

        finish();
    }
}