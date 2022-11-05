package com.example.examenfinalprogra2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table usuario(id_usuario int primary key, username text, password text)");

        BaseDeDatos.execSQL("create table tb_taxis(id int primary key, placa text, marca text, modelo text, piloto text, telefono text)");

        BaseDeDatos.execSQL("create table tb_buses(id int primary key, placa text, marca text, modelo text, piloto text, telefono text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos, int i, int i1) {

    }
}
