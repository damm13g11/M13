package com.example.m13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.m13.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crearDB();

    }

    //Este método se ejecuta en el onCreate, es decir, solo se ejecuta una vez al crear la app.
    public void crearDB(){
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null){
            Toast.makeText(MainActivity.this, "Base de datos creada", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Error al crear la base de datos", Toast.LENGTH_LONG).show();
        }
    }
}