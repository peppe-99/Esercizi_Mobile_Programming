package com.example.threadasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
    private int imgID;
    private int contatore = 0;
    private Integer nextImg = 1;


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

    public void aumentaContatore(View view) {
        contatore++;
        tvContatore.setText(Integer.toString(contatore));
    }

    public void loadNextImg(View view) {
        switch (nextImg) {
            case 1:
                imgID = R.drawable.img1;
                nextImg = 2;
                break;
            case 2:
                imgID = R.drawable.img2;
                nextImg = 3;
                break;
            case 3:
                imgID = R.drawable.img3;
                nextImg = 1;
                break;
        }
        new LoadImgTask().execute(imgID);

    }

    //L'input sarò l'indice dell'immagine da caricare
    //Usiamo altri interi per indicare a che punto del caricamento siamo
    //Il risultato è una Bitmap
    class LoadImgTask extends AsyncTask<Integer, Integer, Bitmap> {

        @Override //Prima dell'esecuzione rendiamo visibile la progress bar
        protected void onPreExecute() {
            progressBar.setProgress(0);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override //In background
        protected Bitmap doInBackground(Integer... imgID) {
            //Prendiamo l'immagine
            Bitmap img = BitmapFactory.decodeResource(getResources(), imgID[0]);

            //Simuliamo in ritardo ed il progresso del caricamento
            for(int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i*10); //chiama la funzione onProgressUpdate
            }

            return img;
        }

        @Override //Aggiorniamo la progress bar di 10 in volta
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override //Alla fine dell'esecuzione facciamo scompare la progress bar e mostriamo l'immagine
        protected void onPostExecute(Bitmap bitmap) {
            progressBar.setVisibility(View.INVISIBLE);
            progressBar.setProgress(0);
            imgView.setImageBitmap(bitmap);
        }
    }
}