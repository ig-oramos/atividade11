package com.example.atividade11

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.createTable
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.REAL
import org.jetbrains.anko.db.BLOB
import org.jetbrains.anko.db.PRIMARY_KEY
import org.jetbrains.anko.db.UNIQUE

class ListaComprasDatabase(context: Context) : ManagedSQLiteOpenHelper(ctx = context,
    name = "listaCompras.db", version = 1) {
    override fun onCreate(db: SQLiteDatabase) {
        // Criando tabelas
        db.createTable("produto", true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE, "nome" to TEXT,
            "quantidade" to INTEGER, "valor" to REAL, "foto" to BLOB)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Atualizando o bd
        
    }
}