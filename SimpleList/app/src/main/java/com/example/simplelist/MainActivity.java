package com.example.simplelist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] contatti = {"Giuseppe", "Elisabetta", "Riccardo", "Aurora", "Nicola", "Gesualdo", "Lorena", "Massimiliano", "Edoardo", "Eleonora", "Pippo", "Pluto", "Topolino", "Paperino", "Crudelia", "Demonio"};
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Prendiamo il riferimento al ListView
        listView = (ListView) findViewById(R.id.listacontatti);

        //Andiamo a creare un ArrayAdapter passando il contesto (this), il layout del singolo elemento della lista,
        //l'id del widget del list element che verrà riempito e l'array di stringhe
        arrayAdapter = new ArrayAdapter<>(this, R.layout.list_element, R.id.contatto, contatti);

        //Settiamo l'array adapter al ListView
        listView.setAdapter(arrayAdapter);

        //Andiamo ad settare il listener del click su un elemento della lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override //parent è un riferimento al ListView, view è il riferimento della View cliccata e position è la sua posizione nella lista
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nomeContatto = (String) listView.getItemAtPosition(position);

                view.setBackgroundColor(Color.argb(255,28,200,160));

                Toast.makeText(getApplicationContext(), "Hai cliccato " + nomeContatto, Toast.LENGTH_SHORT).show();
            }
        });
    }
}