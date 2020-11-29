package com.example.datastoragesql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    //Otteniam le colonne della tabella Studente
    final static String[] columnsStudente = {
            SchemaDB.TavolaStudente._ID,
            SchemaDB.TavolaStudente.COLUMN_NAME,
            SchemaDB.TavolaStudente.COLUMN_VOTO
    };

    private final static String CREATE_TABLE =
            "CREATE TABLE " + SchemaDB.TavolaStudente.TABLE_NAME + "(" +
            SchemaDB.TavolaStudente._ID + " INTEGER PRIMARY KEY, " +
            SchemaDB.TavolaStudente.COLUMN_NAME + " TEXT NOT NULL, " +
            SchemaDB.TavolaStudente.COLUMN_VOTO + " INTEGER NOT NULL); ";

    private final Context context;

    public DatabaseOpenHelper(Context context) {
        super(context, SchemaDB.TavolaStudente.TABLE_NAME, null, 1);
        this.context =  context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }



    void deleteDatabase() {
        context.deleteDatabase(SchemaDB.TavolaStudente.TABLE_NAME);
    }
}