package com.example.fuel_app

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListarActivity : AppCompatActivity() {
    private lateinit var lvCombustivel: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        lvCombustivel = findViewById(R.id.lvCombustivel)

        val combustiveis = resources.getStringArray(R.array.combustivel)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, combustiveis)
        lvCombustivel.adapter = adapter

        lvCombustivel.setOnItemClickListener { parent, view, position, id ->
            val codSelecionado = position + 1
            intent.putExtra("codRetorno", codSelecionado)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
