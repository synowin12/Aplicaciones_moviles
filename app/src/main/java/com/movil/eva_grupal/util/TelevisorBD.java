package com.movil.eva_grupal.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TelevisorBD extends SQLiteOpenHelper {

    public TelevisorBD(Context context){
        super(context,Constantes.NOMBRE_BD,null,Constantes.VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE "+Constantes.NOMBRE_TABLA+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " marca TEXT NOT NULL, "+
                        " modelo TEXT NOT NULL, "+
                        " tienda TEXT NOT NULL, "+
                        " precio_compra REAL NOT NULL,"+
                        " precio_venta REAL NOT NULL,"+
                        " descripcion TEXT NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
