package com.example.permessi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvPermessoRichiesto;
    private Button buttonRequestCameraPermission;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPermessoRichiesto = findViewById(R.id.tvPermessoRichiesto);
        buttonRequestCameraPermission = findViewById(R.id.buttonRequestCameraPermission);
        aggiornaTextView();
    }

    private void aggiornaTextView() {

        //In questo modo controlliamo se l'utente ci ha fornito il permesso della fotocamera
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            tvPermessoRichiesto.setText("Permesso camera: true");
        }
        else {
            tvPermessoRichiesto.setText("Permesso camera: false");
        }
    }

    public void richiediPermessoFotocamera(View view) {

        //Controlliamo se l'utente non ci ha già fornito il permesso
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //Se l'utente non ci ha già fornito il permesso, lo richiediamo cosi
            //Questo metodo lancia il metodo onRequestPermissionsResult, che andiamo a sovrascrivere
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 666);
        }
    }

    @Override //Questo metodo viene lanciato ogni qual volta viene richiesto un permesso e prende come input
    //il codice della richiesta, i permessi richiesti ed un array di interi che indicherà se il permesso i-esimo è stato dato o meno
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 666: {
                //Controlliamo se il primo permesso è stato richiesto o meno
                //Nel nostro caso avremo solamente il permesso della fotocamera
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    aggiornaTextView();
                } else { //In entrambi i casi invochiamo il metodo aggiornaTextView
                    aggiornaTextView();
                }
            }
        }
    }
}