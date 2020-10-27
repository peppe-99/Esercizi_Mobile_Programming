package com.example.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Suggerimento extends Activity {

    private TextView tvQuesito;
    private TextView tvRispota;
    private String testoQuesito;
    private boolean rispostaQuesito;
    private boolean rispotaMostrata = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suggerimento);

        tvQuesito = findViewById(R.id.tvQuesito);
        tvRispota = findViewById(R.id.tvRisposta);

        Intent i = getIntent();
        testoQuesito = i.getStringExtra("Testo Quesito");
        rispostaQuesito = i.getBooleanExtra("Risposta", rispostaQuesito);

        tvQuesito.setText(testoQuesito);
        setReturnIntent();
    }

    private void setReturnIntent() {
        Intent data = new Intent();
        data.putExtra("Risposta Mostrata", rispotaMostrata);
        setResult(RESULT_OK, data);
    }

    public void tornaAlleDomande(View v) {
        //torniamo alla precedente activity come se avessimo cliccato il bottone back
        onBackPressed();
    }

    public void mostraSuggerimento(View view) {
        tvRispota.setText("La risposta corretta è: " + rispostaQuesito);
        rispotaMostrata = true;
        setReturnIntent();
    }
}
