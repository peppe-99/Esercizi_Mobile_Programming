package com.example.quiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Quesito> quesiti = new ArrayList<Quesito>();
    private int quesito_corrente = 0;
    private int risposte_corrette_non_valide = 0;
    private int rispote_corrette_valide = 0;
    private int risposte_totali = 0;

    private boolean[] suggerimentoVisto;

    private TextView tvNumeroQuesito;
    private TextView tvQuesito;
    private TextView tvRisposteCorretteValide;
    private TextView tvRisposteCorretteNonValide;
    private TextView tvRisposteTotali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quesiti.add(new Quesito("Il gallo fa le uova?", false));
        quesiti.add(new Quesito("Il risultato di 1 + 1 è 2 ?", true));
        quesiti.add(new Quesito("Mischiare rosso e blu ci da il viola?", true));
        quesiti.add(new Quesito("Dio è un maiale?", true));
        quesiti.add(new Quesito("Il risultato di 6 - 5 è 2?", false));
        quesiti.add(new Quesito("Il risultato di 6 * 5 è 65?", false));

        suggerimentoVisto = new boolean[quesiti.size()];
        for(Boolean b : suggerimentoVisto) {
            b = false;
        }

        tvNumeroQuesito = findViewById(R.id.tvNumeroQuesito);
        tvQuesito = (TextView) findViewById(R.id.tvQuesito);
        tvRisposteCorretteValide = findViewById(R.id.tvRisposteCorretteValide);
        tvRisposteCorretteNonValide = findViewById(R.id.tvRisposteCorretteNonValide);
        tvRisposteTotali = findViewById(R.id.tvRisposteTotali);

        aggionraQuesito();
    }

    private void aggionraQuesito() {
        tvNumeroQuesito.setText("Quesito n°" + (quesito_corrente + 1));
        tvQuesito.setText(quesiti.get(quesito_corrente).getTestoQuesito());
        tvRisposteCorretteValide.setText("Risposte corrette valie: " + rispote_corrette_valide);
        tvRisposteCorretteNonValide.setText("Risposte corrette non valide: " + risposte_corrette_non_valide);
        tvRisposteTotali.setText("Risposte totali: " + risposte_totali);
    }

    public void domandaPrecedente(View view) {
        if(quesito_corrente == 0) {
            quesito_corrente = quesiti.size()-1;
        } else {
            quesito_corrente--;
        }
        aggionraQuesito();
    }

    public void domandaSuccessiva(View view) {
        quesito_corrente++;
        quesito_corrente = quesito_corrente % quesiti.size();
        aggionraQuesito();
    }

    public void rispondiVero(View view) {
        risposte_totali++;
        Quesito q = quesiti.get(quesito_corrente);
        boolean risposta = q.getRisposto();

        if(risposta == true) {
            if(suggerimentoVisto[quesito_corrente] == false) {
                rispote_corrette_valide++;
            }
            else {
                risposte_corrette_non_valide++;
            }
        }

        domandaSuccessiva(null);
    }

    public void risondiFalso(View view) {
        risposte_totali++;
        Quesito q = quesiti.get(quesito_corrente);
        boolean risposta = q.getRisposto();

        if(risposta == false) {
            if(suggerimentoVisto[quesito_corrente] == false) {
                rispote_corrette_valide++;
            }
            else {
                risposte_corrette_non_valide++;
            }
        }

        domandaSuccessiva(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 666 && resultCode == RESULT_OK && data != null){
            suggerimentoVisto[quesito_corrente] = data.getBooleanExtra("Risposta Mostrata", false); //se non trova la chiave ritorna false
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void mostraSuggerimento(View view) {
        //La risoluzione dell'intent sarà esplicita
        Intent i = new Intent();
        i.setClass(getApplicationContext(), Suggerimento.class);
        i.putExtra("Testo Quesito", quesiti.get(quesito_corrente).getTestoQuesito());
        i.putExtra("Risposta", quesiti.get(quesito_corrente).getRisposto());
        startActivityForResult(i, 666);
    }
}