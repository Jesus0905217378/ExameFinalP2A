package com.example.examenfinalprogra2;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class pantalla2 extends AppCompatActivity {

    private EditText et_id, et_placa, et_marca, et_modelo, et_piloto, et_telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        et_id = (EditText)findViewById(R.id.txt_id);
        et_placa = (EditText)findViewById(R.id.txt_placa);
        et_marca = (EditText)findViewById(R.id.txt_marca);
        et_modelo = (EditText)findViewById(R.id.txt_modelo);
        et_piloto = (EditText)findViewById(R.id.txt_piloto);
        et_telefono = (EditText)findViewById(R.id.txt_telefono);
    }
    public void LimpiarDatos(){
        et_id.setText("");
        et_placa.setText("");
        et_marca.setText("");
        et_modelo.setText("");
        et_piloto.setText("");
        et_telefono.setText("");

    }

    // insert Taxis
    public void insert(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administrador",null,1 );
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String id = et_id.getText().toString();
        String placa = et_placa.getText().toString();
        String marca = et_marca.getText().toString();
        String modelo = et_modelo.getText().toString();
        String piloto = et_piloto.getText().toString();
        String telefono = et_telefono.getText().toString();

        if(!id.isEmpty() && !placa.isEmpty() && !marca.isEmpty() && !modelo.isEmpty() && !piloto.isEmpty() && !telefono.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("id",id);
            registro.put("placa",placa);
            registro.put("marca",marca);
            registro.put("modelo",modelo);
            registro.put("piloto",piloto);
            registro.put("telefono",telefono);

            BaseDeDatos.insert("tb_taxis", null, registro);
            BaseDeDatos.close();

            LimpiarDatos();
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    // Leer Taxis
    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administrator",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String id = et_id.getText().toString();
        final String JDBC = "select placa, marca, modelo, piloto, telefono from tb_taxis where id=";

        if(!id.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery(("select placa, marca, modelo, piloto, telefono from tb_taxis where id=")+id,null);
            if(fila.moveToFirst()){
                et_placa.setText(fila.getString(0));
                et_marca.setText(fila.getString(1));
                et_modelo.setText(fila.getString(2));
                et_piloto.setText(fila.getString(3));
                et_telefono.setText(fila.getString(4));

                BaseDeDatos.close();
            }else{
                Toast.makeText(this, "El registro no existe", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Por favor ingresa id", Toast.LENGTH_SHORT).show();
        }
    }

    public void Limpia(View view){
        LimpiarDatos();
    }

    public void Salir(View view){
        Button btn;
        btn = (Button)findViewById(R.id.botonSalirT);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pantalla2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}