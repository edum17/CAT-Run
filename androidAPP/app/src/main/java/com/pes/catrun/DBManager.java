package com.pes.catrun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by RafaEscoz on 16/10/16.
 */

public class DBManager {
    public static final String TABLE_NAME = "Usuarios";
    public static final String CN_ID = "_id";
    public static final String CN_NAME = "nom";
    public static final String CN_USERNAME = "username";
    public static final String CN_LOGIN = "login";
    public static final String CN_TOKEN = "token";
    public static final String CN_PASSWD = "contrasenya";
    public static final String CN_TEAM = "equip";

    public static final String CREATE_TABLE_USUARIOS = "create table " +TABLE_NAME+ " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_USERNAME + " text,"
            + CN_LOGIN + " text,"
            + CN_TOKEN + " text,"
            + CN_PASSWD + " text,"
            + CN_TEAM + " text);";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
         helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generarContentValues(String nombre, String password) {
        ContentValues valores = new ContentValues();
        valores.put(CN_NAME, nombre);
        valores.put(CN_PASSWD, password);
        valores.put(CN_USERNAME, "loco");
        return valores;
    }


    public ContentValues generarContentValuesRegistreUsuari(String nombre, String password, String username) {
        ContentValues valores = new ContentValues();
        valores.put(CN_NAME, nombre);
        valores.put(CN_PASSWD, password);
        valores.put(CN_USERNAME, username);
        return valores;
    }



    public void insertarValoresRegistroUser (String nombre, String password, String username) {


        //en el segundo parametro podemos definir que campos permiten valores null
        db.insert(TABLE_NAME, null, generarContentValuesRegistreUsuari(nombre,password,username));


    }



    public void insertar (String nombre, String password) {


        //en el segundo parametro podemos definir que campos permiten valores null
        db.insert(TABLE_NAME, null, generarContentValues(nombre,password));


    }

    public void insertar2 (String nombre, String password) {


        db.execSQL("insert into "+TABLE_NAME+" values (null, '"+nombre+"' , "+password+")");

    }

    public Cursor cargarCursorUsuarios() {
        String[] columnas = new String[]{CN_NAME,CN_PASSWD};
        return db.query(TABLE_NAME, columnas,null,null,null,null,null);

    }


    public Cursor ComprobarUsuarioPassword(String nombre, String password) {
       return db.rawQuery("SELECT nom, contrasenya FROM "+TABLE_NAME+ " WHERE nom = ? AND contrasenya = ?", new String[] {nombre, password});

    }




}
