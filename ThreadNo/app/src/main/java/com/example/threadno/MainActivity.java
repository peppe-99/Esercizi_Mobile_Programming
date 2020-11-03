package com.example.threadno;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonCaricaImg, buttonContatore;
    private ImageView imgView;
    private TextView tvContatore;
    private ProgressBar progressBar;
    private int contatore = 0;
    private int nextImg = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCaricaImg = findViewById(R.id.buttonCaricaImg);
        buttonContatore = findViewById(R.id.buttonContatore);
        imgView = findViewById(R.id.imgView);
        tvContatore = findViewById(R.id.tvContatore);
        progressBar = findViewById(R.id.progressBar);

    }

    public void loadNextImg(View view) {
        //Rendiamo visibile la progress bar. In realtà non verrà mai visualizzata poichè tutte le modifiche
        //saranno visibili alla fine dell'esecuzione del metodo
        progressBar.setVisibility(View.VISIBLE);

        // Simuliamo un caricamento di 8 secondi, facendo una sleep di 8 secondi sul main thread
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Scegliamo la corretta img da caricare
        Bitmap img = null;
        switch (nextImg) {
            case 1:
                img = BitmapFactory.decodeResource(getResources(), R.drawable.img1);
                nextImg = 2;
                break;
            case 2:
                img = BitmapFactory.decodeResource(getResources(), R.drawable.img2);
                nextImg = 3;
                break;
            case 3:
                img = BitmapFactory.decodeResource(getResources(), R.drawable.img3);
                nextImg = 1;
                break;
        }
        //Carichiamo la img e rendiamo invisibile la progress bar (che in realtà non vedremo mai)
        imgView.setImageBitmap(img);
        progressBar.setVisibility(View.INVISIBLE);
    }

    //Se eseguiamo questo metodo dopo il precedente. La variabile contatore continuerà ad aumentare
    //però le modifiche saranno visibili solamente alla fine del metodo precedente.
    public void aumentaContatore(View view) {
        contatore++;
        tvContatore.setText(Integer.toString(contatore));
    }
}