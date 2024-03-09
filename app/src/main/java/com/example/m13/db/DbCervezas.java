package com.example.m13.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.m13.models.Cerveza;

import java.util.ArrayList;

public class DbCervezas extends DbHelper {

    Context context;

    public DbCervezas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarCerveza(String nombre, String pais, String tipo, String marca, int precio) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("pais", pais);
            values.put("tipo", tipo);
            values.put("marca", marca);
            values.put("precio", precio);

            id = db.insert(TABLE_CERVEZAS, null, values);

        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public ArrayList<Cerveza> mostrarCervezas(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Cerveza> listaCervezas = new ArrayList<>();
        Cerveza cerveza = null;
        Cursor cursorCervezas = null;

        cursorCervezas = db.rawQuery("SELECT * FROM " + TABLE_CERVEZAS, null);

        if (cursorCervezas.moveToFirst()){
            do{
                cerveza = new Cerveza();
                cerveza.setId(cursorCervezas.getInt(0));
                cerveza.setNombre(cursorCervezas.getString(1));
                cerveza.setPais(cursorCervezas.getString(2));
                cerveza.setTipo(cursorCervezas.getString(3));
                cerveza.setMarca(cursorCervezas.getString(4));
                cerveza.setPrecio(cursorCervezas.getInt(5));

                listaCervezas.add(cerveza);
            } while (cursorCervezas.moveToNext());
        }
        cursorCervezas.close();

        return listaCervezas;
    }

    public Cerveza verCerveza(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cerveza cerveza = null;
        Cursor cursorCervezas = null;

        cursorCervezas = db.rawQuery("SELECT * FROM " + TABLE_CERVEZAS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorCervezas.moveToFirst()) {
            cerveza = new Cerveza();
            cerveza.setId(cursorCervezas.getInt(0));
            cerveza.setNombre(cursorCervezas.getString(1));
            cerveza.setPais(cursorCervezas.getString(2));
            cerveza.setTipo(cursorCervezas.getString(3));
            cerveza.setMarca(cursorCervezas.getString(4));
            cerveza.setPrecio(cursorCervezas.getInt(5));
        }

        cursorCervezas.close();

        return cerveza;
    }
    public boolean editarCerveza(int id, String nombre, String pais, String tipo, String marca, int precio) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CERVEZAS + " SET nombre = '" + nombre + "', pais = '" + pais + "', tipo = '" + tipo +
                    "', marca = '" + marca + "', precio = '" + precio + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarCerveza(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CERVEZAS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

}

