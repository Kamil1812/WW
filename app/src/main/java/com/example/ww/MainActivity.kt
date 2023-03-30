package com.example.ww

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Algorytm: KMP


        // Algorytm: BM


        // Algorytm: RK


        // Button
        findViewById<Button>(R.id.btn).setOnClickListener {

            var text = findViewById<EditText>(R.id.EditText1)
            var text2 = findViewById<EditText>(R.id.EditText2)

            if (!text.text.isEmpty() && !text2.text.isEmpty()) {
                var ilosc_elementow = findViewById<EditText>(R.id.EditText1).text.toString().toInt()
                var ile_razy = findViewById<EditText>(R.id.EditText2).text.toString().toInt()



                // Generowanie łańcucha
                var losowa = java.util.Random()
                var stringBuilder = StringBuilder()
                for (i in 1..ilosc_elementow) {
                    stringBuilder.append(losowa.nextInt(10))
                }
                var lancuch = stringBuilder.toString()
                findViewById<TextView>(R.id.textView_lancuch).text = lancuch



                // Wzorzec
                var losowa2 = java.util.Random()
                var stringBuilder2 = StringBuilder()
                for (i in 1..1) {
                    stringBuilder2.append(losowa2.nextInt(10))
                }
                var wzorzec = stringBuilder2.toString()
                findViewById<TextView>(R.id.textView_wzorzec).text = wzorzec



                // Algorytm: Brute Force
                fun BF(tekst: String, wzor: String): Pair<Int?, Long> {
                    val x = tekst.length
                    val y = wzor.length

                    for (i in 0..x - y) {
                        var j = 0
                        while (j < y && wzor[j] == tekst[i + j]) {
                            j++
                        }
                        if (j == y) {
                            return Pair(i, 0)
                        }
                    }

                    return Pair(null, 0)
                }

                var BF_wynik_textView = findViewById<TextView>(R.id.BF_wynik)

                val wynikBF = BF(lancuch, wzorzec)
                if (wynikBF.first != null)
                {
                    BF_wynik_textView.text = "Znaleziono wzorzec na pozycji ${wynikBF.first}"
                }
                else
                {
                    BF_wynik_textView.text = "Nie znaleziono wzorca"
                }
            }
        }
    }
}