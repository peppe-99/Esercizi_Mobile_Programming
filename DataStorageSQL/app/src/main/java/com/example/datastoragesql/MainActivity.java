package com.example.datastoragesql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DatabaseOpenHelper dbHepler;
    private SQLiteDatabase db;
    private SimpleCursorAdapter adapterAll, adaptetFilter;
    private EditText etNome, etVoto;
    private ListView lvAll, lvFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Criamo il database open helper
        dbHepler = new DatabaseOpenHelper(getApplicationContext());

        //Otteniamo il riferimento ad un DB modificabile
        db = dbHepler.getWritableDatabase();

        //I Cursor conterranno i risultati delle query
        Cursor cursorAll = db.rawQuery("SELECT * FROM studente", null);
        Cursor cursorFilter = readSelectedEntries();

        //Creiamo i due cursor adapter
        adapterAll = new SimpleCursorAdapter(
                getApplicationContext(),                    //contesto
                R.layout.list_element,                      //layout dell'elemento della lista
                cursorAll,                                  //il cursore
                DatabaseOpenHelper.columnsStudente,         //i nomi delle colonne della tabella del database
                new int[] {R.id._id, R.id.nome, R.id.voto}, //id delle view nel layout
                0
        );

        adaptetFilter = new SimpleCursorAdapter(
                getApplicationContext(),                    //contesto
                R.layout.list_element,                      //layout dell'elemento della lista
                cursorFilter,                               //il cursore
                DatabaseOpenHelper.columnsStudente,         //i nomi delle colonne della tabella del database
                new int[] {R.id._id, R.id.nome, R.id.voto}, //id delle view nel layout
                0
        );

        etNome = findViewById(R.id.etNome);
        etVoto = findViewById(R.id.etVoto);
        lvAll = findViewById(R.id.listAllVote);
        lvFilter = findViewById(R.id.listVoteFiltering);

        lvAll.setAdapter(adapterAll);
        lvFilter.setAdapter(adaptetFilter);

        lvAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //otteniamo il cursor alla position cliccata
                SQLiteCursor cursor = (SQLiteCursor) lvAll.getItemAtPosition(position);

                //otteniamo id, nome e voto
                int _id = cursor.getInt(0);
                String nome = cursor.getString(1);
                int voto = cursor.getInt(2);

                Toast.makeText(getApplicationContext(), "Cancello l'elemento: " + _id + " " + nome + " " + voto, Toast.LENGTH_LONG).show();

                //Cancelliamo l'elemento dal db andando a specificare il nome della tabella, la condizione di eliminazione, la lista dei parametri da sostituire nella
                db.delete(SchemaDB.TavolaStudente.TABLE_NAME,           // nome della tabella
                        SchemaDB.TavolaStudente._ID + "=?", // condizione di eliminazione
                        new String[]{"" + _id});                        // parametri da sostituire nella condizione di eliminazione

                //aggiorniamo le liste
                updateLists();
            }
        });

    }

    private Cursor readSelectedEntries() {
        String [] projection = {SchemaDB.TavolaStudente._ID,
                SchemaDB.TavolaStudente.COLUMN_NAME,
                SchemaDB.TavolaStudente.COLUMN_VOTO
        };

        String sortOrder = SchemaDB.TavolaStudente.COLUMN_VOTO + " DESC";

        String selection = SchemaDB.TavolaStudente.COLUMN_VOTO + " > ?";

        String[] arg = {"25"};

        //Creiamo ed eseguiamo una query specificando
        Cursor cursor = db.query(
                SchemaDB.TavolaStudente.TABLE_NAME, // nome tabella
                projection,                         // quali colonne vogliamo esserci restituite
                selection,                          // il criterio di selezione della clausola WHERE
                arg,                                // gli argomenti da sostituire nella clausola where
                null,                      // la clausola groupBy
                null,                       // il criterio having
                sortOrder                          // il criterio di ordinamento ascendente o discendente
        );

        return cursor;
    }

    public void insertStudent(View view) {
        //Otteniamo ciò che ha scritto l'utente
        String nome = etNome.getText().toString();
        String voto = etVoto.getText().toString();
        Random rng = new Random();

        //Controlliamo se la lunghezza è maggiore di 0, per evitare di avere stringhe vuote
        if(nome.length() > 0 && voto.length() > 0) {
            //Creiamo un contentValues contentente le informazioni (colonna/valore) del record da inserire
            ContentValues values = new ContentValues();
            values.put(SchemaDB.TavolaStudente._ID, rng.nextInt(20000) + "");
            values.put(SchemaDB.TavolaStudente.COLUMN_NAME, nome);
            values.put(SchemaDB.TavolaStudente.COLUMN_VOTO, voto);
            //inseriamo il record specificando il nome della tabella ed il contentvalues
            db.insert(SchemaDB.TavolaStudente.TABLE_NAME, null, values);
        }

        updateLists();
    }


    private void updateLists() {
        //I due adapter rieseguono la query e notificano il db dei cambiamenti
        adapterAll.getCursor().requery();
        adapterAll.notifyDataSetChanged();
        adaptetFilter.getCursor().requery();
        adaptetFilter.notifyDataSetChanged();

    }

    public void clearAll(View view) {
        db.delete(SchemaDB.TavolaStudente.TABLE_NAME, null, null);
        updateLists();
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }


}