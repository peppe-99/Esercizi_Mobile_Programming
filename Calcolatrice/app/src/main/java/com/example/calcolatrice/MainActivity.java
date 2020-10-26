package com.example.calcolatrice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.renderscript.Script;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvEspressione;
    private TextView tvMemory;
    private TextView tvRisultato;
    private String espressione;
    private String memory;
    private String risultato;
    private Button clickedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvEspressione = (TextView) findViewById(R.id.tvEspressione);
        tvMemory = (TextView) findViewById(R.id.tvMemory);
        tvRisultato = (TextView) findViewById(R.id.tvRisultato);

        if(savedInstanceState != null) {
            espressione = savedInstanceState.getString("Espressione");
            memory = savedInstanceState.getString("Memory");
            risultato = savedInstanceState.getString("Risultato");

            tvEspressione.setText(espressione);
            tvRisultato.setText(risultato);
            tvMemory.setText(memory);
            
        } else {
            espressione = "";
            memory = "";
            risultato = "";
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("Espressione", espressione);
        outState.putString("Memory", memory);
        outState.putString("Risultato", risultato);
        super.onSaveInstanceState(outState);
    }

    public void premiNumero(View view) {
        clickedButton = (Button) view;
        espressione += (String) clickedButton.getText();
        tvEspressione.setText(espressione);
    }

    public void premiOperazione(View view) {
        clickedButton = (Button) view;
        String operazione = (String) clickedButton.getText();
        espressione = (String) tvEspressione.getText();

        if(!espressione.equals("")) {
            if (espressione.charAt(espressione.length() - 1) != '+' && espressione.charAt(espressione.length() - 1) != '-' && espressione.charAt(espressione.length() - 1) != '*' && espressione.charAt(espressione.length() - 1) != '/' && espressione.charAt(espressione.length() - 1) != '.') {
                espressione += (String) clickedButton.getText();
                tvEspressione.setText(espressione);
            }
        }
    }

    public void cancellaEspressione(View view) {
        espressione = "";
        tvEspressione.setText(espressione);
    }

    public void salvaMemoria(View view) {
        risultato = (String) tvRisultato.getText();
        if(risultato.equals("")) {
            memory = "0";
        } else {
            memory = risultato;
        }
        tvMemory.setText(memory);
    }

    public void cancellaMemory(View view) {
        memory = "";
        tvMemory.setText(memory);
    }

    public void usaMemoria(View view) {
        espressione = (String) tvEspressione.getText();
        memory = (String) tvMemory.getText();
        if(!memory.equals("")){
            if(espressione.equals("")) {
                espressione += memory;
            }
            if(espressione.charAt(espressione.length()-1) == '+' || espressione.charAt(espressione.length()-1) == '-' || espressione.charAt(espressione.length()-1) == '*' || espressione.charAt(espressione.length()-1) == '/' || espressione.charAt(espressione.length()-1) == '.') {
                espressione += memory;
            }
            tvEspressione.setText(espressione);
        }
    }

    public void premiInvio(View view) {
        espressione = (String) tvEspressione.getText();
        if(!espressione.equals("")){
            if (espressione.charAt(espressione.length() - 1) != '+' && espressione.charAt(espressione.length() - 1) != '-' && espressione.charAt(espressione.length() - 1) != '*' && espressione.charAt(espressione.length() - 1) != '/' && espressione.charAt(espressione.length() - 1) != '.') {
                risultato = Double.toString(eval(espressione));
                tvRisultato.setText(risultato);
            }
        }
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}