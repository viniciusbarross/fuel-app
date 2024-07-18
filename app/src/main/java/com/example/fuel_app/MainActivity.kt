package com.example.fuel_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var etConsumoComb1: TextInputEditText
    private lateinit var etConsumoComb2: TextInputEditText
    private lateinit var etValorComb1: TextInputEditText
    private lateinit var etValorComb2: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etConsumoComb1 = findViewById(R.id.etConsumoComb1)
        etConsumoComb2 = findViewById(R.id.etConsumoComb2)
        etValorComb1 = findViewById(R.id.etValorComb1)
        etValorComb2 = findViewById(R.id.etValorComb2)
    }

    fun btMaisBaratoOnClick(view: View) {
        if (validateFields()) {
            val valorPorKmComb1 = etValorComb1.text.toString().toDouble() / etConsumoComb1.text.toString().toDouble()
            val valorPorKmComb2 = etValorComb2.text.toString().toDouble() / etConsumoComb2.text.toString().toDouble()

            if (valorPorKmComb1 < valorPorKmComb2) {
                Toast.makeText(this, "O combustível 1 é mais rentável", Toast.LENGTH_LONG).show()
            } else if (valorPorKmComb1 > valorPorKmComb2) {
                Toast.makeText(this, "O combustível 2 é mais rentável", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Os dois combustíveis são igualmente rentáveis", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true

        if (etConsumoComb1.text.isNullOrEmpty()) {
            etConsumoComb1.error = "Campo obrigatório"
            isValid = false
        }
        if (etConsumoComb2.text.isNullOrEmpty()) {
            etConsumoComb2.error = "Campo obrigatório"
            isValid = false
        }
        if (etValorComb1.text.isNullOrEmpty()) {
            etValorComb1.error = "Campo obrigatório"
            isValid = false
        }
        if (etValorComb2.text.isNullOrEmpty()) {
            etValorComb2.error = "Campo obrigatório"
            isValid = false
        }

        return isValid
    }

    fun btBuscarComb2OnClick(view: View) {
        val intent = Intent(this, ListarActivity::class.java)
        getResult2.launch(intent)
    }

    fun btBuscarComb1OnClick(view: View) {
        val intent = Intent(this, ListarActivity::class.java)
        getResult.launch(intent)
    }

    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            if (it.data != null) {
                val retorno = it.data?.getIntExtra("codRetorno", 0)
                if (retorno == 1) {
                    etConsumoComb1.setText("10")
                } else {
                    etValorComb1.setText("15")
                }
            }
        }
    }

    private val getResult2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            if (it.data != null) {
                val retorno = it.data?.getIntExtra("codRetorno", 0)
                if (retorno == 1) {
                    etConsumoComb2.setText("10")
                } else {
                    etValorComb2.setText("15")
                }
            }
        }
    }
}
