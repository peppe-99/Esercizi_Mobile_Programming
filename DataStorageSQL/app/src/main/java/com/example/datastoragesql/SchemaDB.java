package com.example.datastoragesql;

import android.provider.BaseColumns;

// Con questa classe definiamo lo schema del DB, ovvero le tavole che lo compongono
public class SchemaDB {

    public SchemaDB() { }

    //Ogni tavola è definita nel seguente modo
    public static abstract class TavolaStudente implements BaseColumns {
        public static final String TABLE_NAME = "studente"; //nome della tavola
        //definiamo due colonne
        public static final String COLUMN_NAME = "nome";
        public static final String COLUMN_VOTO = "voto";
    }

    //Questa è solo una definizione del DB in quanto la vera creazione avverrà nel DBOpenHelper
}
