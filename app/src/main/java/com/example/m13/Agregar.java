package com.example.m13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.m13.db.DbCervezas;

public class Agregar extends AppCompatActivity {

    EditText txtNombre, txtPais, txtTipo, txtMarca, txtPrecio;
    Button btnInsertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        txtNombre = findViewById(R.id.txtNombre);
        txtPais = findViewById(R.id.txtPais);
        txtTipo = findViewById(R.id.txtTipo);
        txtMarca = findViewById(R.id.txtMarca);
        txtPrecio = findViewById(R.id.txtPrecio);
        btnInsertar = findViewById(R.id.btnInsertar);

        btnInsertar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){

                DbCervezas dbCervezas = new DbCervezas(Agregar.this);
                long id = dbCervezas.insertarCerveza(
                        txtNombre.getText().toString(),
                        txtPais.getText().toString(),
                        txtTipo.getText().toString(),
                        txtMarca.getText().toString(),
                        Integer.parseInt(txtPrecio.getText().toString()));

                if (id > 0) {
                    Toast.makeText(Agregar.this, "Cerveza añadida", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(Agregar.this, "Error al agregar registro", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        txtNombre.setText("");
        txtPais.setText("");
        txtTipo.setText("");
        txtMarca.setText("");
        txtPrecio.setText("");
    }
}