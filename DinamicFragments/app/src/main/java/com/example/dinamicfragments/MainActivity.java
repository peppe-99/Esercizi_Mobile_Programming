package com.example.dinamicfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    FragmentManager fm;
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Otteniamo il riferimento al fragmentManager quando creiamo l'activity
        fm = getFragmentManager();
        tvMessage = findViewById(R.id.tvMessage);
    }

    //Per inserire il frammento A
    public void insertA(View view) {
        //Creo un oggetto di tipo FrammentoA
        FrammentoA fA = new FrammentoA();

        //Chiedo al fragmentManager di inziare una nuova transazione
        FragmentTransaction ft = fm.beginTransaction();

        //Aggiungo il frammento fA nel contenitore indicato come primo parametro e gli assegno un tag
        ft.add(R.id.contenitoreFrammenti, fA, "tagFrammentoA");

        //Faccio il commit della transazione
        ft.commit();
        tvMessage.setText("Frammento A inserito");
    }

    //Discorso analogo per il frammento B
    public void insertB(View view) {
        FrammentoB fB = new FrammentoB();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.contenitoreFrammenti, fB, "tagFrammentoB");
        ft.commit();
        tvMessage.setText("Frammento B inserito");
    }

    //Quando crediamo un frammento A o B e lo sostituiamo con l'altro, non viene distrutto anzi
    //vengono sovrapposti

    //Per rimuovere il frammento A dal contenitore bisogna
    public void removeA(View view) {

        //Ottenere il riferimento al frammento, tramite il TAG o ID
        FrammentoA fA = (FrammentoA) fm.findFragmentByTag("tagFrammentoA");

        //Se tale riferimento è null allora quel frammento non è presente nel contenitore
        if(fA != null) {

            //Altrimenti iniziamo una nuova transazione
            FragmentTransaction ft = fm.beginTransaction();

            //effettiamo la rimozione del frammento A
            ft.remove(fA);
            ft.commit();
            tvMessage.setText("Frammento A rimosso dal contenitore");
        }
        tvMessage.setText("Frammento A non presente");
    }

    //Discorso analogo per la rimozione del frammento B
    public void removeB(View view) {
        FrammentoB fB = (FrammentoB) fm.findFragmentByTag("tagFrammentoB");
        if(fB != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(fB);
            ft.commit();
            tvMessage.setText("Frammento B rimosso dal contenitore");
        }
        tvMessage.setText("Frammento B non presente");
    }

    //Con i precedenti due metodi possiamo rimuovere i frammenti dall'activity e distriggerli
    //Per riutilizzarli bisogna nuovamente ricrearli


    //Per sostituire il frammento A con il frammento B
    public void fA_to_fB(View view) {
        //Ottengo i riferimenti ad entrambi i frammenti
        FrammentoA fA = (FrammentoA) fm.findFragmentByTag("tagFrammentoA");
        FrammentoB fB = (FrammentoB) fm.findFragmentByTag("tagFrammentoB");

        //se A esiste
        if(fA != null) {
            //se b esiste
            if(fB != null) {
                //Inizio una nuova transazione
                FragmentTransaction ft = fm.beginTransaction();

                //rinpiazzo A con B
                ft.replace(R.id.contenitoreFrammenti, fB);
                ft.commit();
                tvMessage.setText("Ho sostituito A con B");
            }
            //altrimenti lo creo
            else insertB(null);
        }
    }

    //Discorso analogo per sostituire il frammento B con il frammento A
    public void fB_to_fA(View view) {
        FrammentoA fA = (FrammentoA) fm.findFragmentByTag("tagFrammentoA");
        FrammentoB fB = (FrammentoB) fm.findFragmentByTag("tagFrammentoB");
        if(fA != null) {
            if(fB != null) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.contenitoreFrammenti, fA);
                ft.commit();
                tvMessage.setText("Ho sostituito B con A");
            }
            else insertA(null);
        }
    }

    public void attachA(View view) {
        Fragment fA = fm.findFragmentByTag("tagFrammentoA");
        if(fA != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.attach(fA);
            ft.commit();
            tvMessage.setText("Attach di A");
        }
    }

    public void detachA(View view) {
        Fragment fA = fm.findFragmentByTag("tagFrammentoA");
        if(fA != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.detach(fA);
            tvMessage.setText("Detach di A");
            ft.commit();
        }
    }

    public void attachB(View view) {
        Fragment fB = fm.findFragmentByTag("tagFrammentoB");
        if(fB != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.attach(fB);
            ft.commit();
            tvMessage.setText("Attach di B");
        }
    }

    public void detachB(View view) {
        Fragment fB = fm.findFragmentByTag("tagFrammentoB");
        if(fB != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.detach(fB);
            ft.commit();
            tvMessage.setText("Detach di B");
        }
    }

    //i metodi attach e detach permettono di attaccare e staccare un frammento, senza eliminarlo
}