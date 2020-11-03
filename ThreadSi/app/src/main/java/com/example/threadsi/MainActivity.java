package com.example.threadsi;

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
    private Bitmap img;
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
        //il main thread rende visibile la pogress bar e lancia un nuovo thread
        progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {

                //questo thread si ferma per 8 secondi per simulare il caricamente dell'imagine
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Sceglie l'immagine da caricare
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

                //Poichè non può interagire con l'interfaccia grafica, usiamo questo metodo che
                //lancia un nuovo thread come se fosse il main thread cosi da poter modificare la UI
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imgView.setImageBitmap(img);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start(); //il thread viene startato
    }


    public void aumentaContatore(View view) {
        contatore++;
        tvContatore.setText(Integer.toString(contatore));
    }
}