package com.example.customlist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public String[] contatti = {"Giuseppe", "Elisabetta", "Riccardo", "Aurora", "Nicola", "Gesualdo", "Lorena", "Massimiliano", "Edoardo", "Eleonora", "Pippo", "Pluto", "Topolino", "Paperino", "Crudelia", "Demonio"};
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listacontatti);
        CustomAdapter adapter = new CustomAdapter(this, R.layout.list_element, new ArrayList<Contatto>());

        listView.setAdapter(adapter);

        for (String s : contatti) {
            adapter.add(new Contatto(s, "3460262828", getResources().getDrawable(R.drawable.placeholder)));
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contatto contatto = (Contatto) listView.getItemAtPosition(position);

                view.setBackgroundColor(Color.argb(255,28, 200, 160));

                Toast.makeText(getApplicationContext(), "Hai cliccato su " + contatto.getNome(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}