package com.example.institutoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtMateria = findViewById<EditText>(R.id.txtMateria)
        val spinner = findViewById<Spinner>(R.id.spinnerAsistencia)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnConsultar = findViewById<Button>(R.id.btnConsultar)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)

        db = DBHelper(this)

        val opciones = arrayOf(
            "Presente",
            "Ausente"
        )

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            opciones
        )

        spinner.adapter = adaptador

        btnGuardar.setOnClickListener {

            val res = db.insertar(
                txtNombre.text.toString(),
                txtMateria.text.toString(),
                spinner.selectedItem.toString()
            )

            if(res){
                Toast.makeText(this,"Asistencia guardada",Toast.LENGTH_SHORT).show()

                txtNombre.setText("")
                txtMateria.setText("")
            }
        }

        btnConsultar.setOnClickListener {

            val cursor = db.consultar()

            var datos = ""

            while(cursor.moveToNext()){

                datos +=
                    "Alumno: ${cursor.getString(1)}\n" +
                            "Materia: ${cursor.getString(2)}\n" +
                            "Asistencia: ${cursor.getString(3)}\n\n"
            }

            txtResultado.text = datos
        }
    }
}