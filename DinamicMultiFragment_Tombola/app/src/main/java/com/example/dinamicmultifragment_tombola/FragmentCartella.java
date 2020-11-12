package com.example.dinamicmultifragment_tombola;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentCartella extends Fragment implements View.OnClickListener{
    //implementtiamo l'interfaccia OnClickListener in modo tale da invocare il metodo onClick su tutti
    //gli elementi cliccabili del layout

    //Stato del frammento
    private int numeri[]; //i numeri della cartella

    public FragmentCartella() {
        //costruttore vuoto di default
    }

    public void setNumeri(int[] numeri) {
        this.numeri = numeri;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //creiamo una view facendo l'inflate
        View v = inflater.inflate(R.layout.layout_cartella, container, false);

        //otteniamo tutte le view cliccabili all'interno della table (ovvero i bottoni)
        ArrayList<View> buttons = v.findViewById(R.id.tableCartella).getTouchables();
        int i = 0;

        //Settiamo l'onClick ed il testo di ogni pulsante
        //L'onClick associato è quello specificato in questo file
        for(View view : buttons) {
            Button b = (Button) view;
            if(b != null && numeri != null) {
                b.setText(""+numeri[i]);
                i++;
                b.setOnClickListener(this);
            }
        }
        return v;
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        Object tag = b.getTag();

        //Controlliamo se la cella della cartella è gia stata cliccata o meno
        //e cambiamo il colore a sua volta
        if(tag == null || tag.toString().equals("false")) {
            b.setTag("true");
            b.setBackgroundColor(Color.GREEN);
        }
        else if(tag.toString().equals("true")) {
            b.setTag("false");
            b.setBackgroundColor(Color.LTGRAY);
        }
    }
}
