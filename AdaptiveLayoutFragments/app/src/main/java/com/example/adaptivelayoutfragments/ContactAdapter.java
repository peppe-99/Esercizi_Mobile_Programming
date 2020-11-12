package com.example.adaptivelayoutfragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private LayoutInflater inflater;


    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> contacts) {
        super(context, resource, contacts);

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.contact_layout, null);
        }

        Contact contact = getItem(position);

        TextView tvNomeContatto = convertView.findViewById(R.id.tvNomeContatto);
        TextView tvNumeroContatto = convertView.findViewById(R.id.tvNumeroContatto);

        tvNomeContatto.setText(contact.getNome());
        tvNumeroContatto.setText(contact.getNumero());

        return convertView;
    }
}
