package com.example.customlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contatto> {

    private LayoutInflater inflater;

    public CustomAdapter(Context context, int resource, List<Contatto> objects) {
        super(context, resource, objects);

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            //Se la view è null, alllora viene creata la struttura dell'elemento della lista senza riempirlo
            Log.d("DEBUG", "Inflating View");
            convertView = inflater.inflate(R.layout.list_element, null);
        }
        //Altrimenti se la view non è vuota, per ogni elemento della lista

        //Otteniamo il riferimento all'oggetto della lista
        Contatto contatto = getItem(position);
        Log.d("DEBUG", "contatto: " + contatto);

        //Otteniamo il riferimento ai wighet del layout dell'elemento della lista
        TextView nomeTextView = (TextView) convertView.findViewById(R.id.nome_contatto);
        TextView numeroTextView = (TextView) convertView.findViewById(R.id.numero_contatto);
        ImageView fotoImageView = (ImageView) convertView.findViewById(R.id.foto_contatto);

        //Andiamo a riempire questi riferimenti con i parametri del contatto
        nomeTextView.setText(contatto.getNome());
        numeroTextView.setText(contatto.getNumero());
        fotoImageView.setImageDrawable(contatto.getFoto());

        return convertView;
    }
}
