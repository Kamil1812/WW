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

        // Button
        findViewById<Button>(R.id.btn).setOnClickListener {

            var text = findViewById<EditText>(R.id.EditText1)
            var text2 = findViewById<EditText>(R.id.EditText2)

            if (!text.text.isEmpty() && !text2.text.isEmpty()) {

                // Generowanie łańcucha
                var ilosc_elementow = findViewById<EditText>(R.id.EditText1).text.toString().toInt()
                var losowa = java.util.Random()
                var stringBuilder = StringBuilder()
                for (i in 1..ilosc_elementow) {
                    stringBuilder.append(losowa.nextInt(10))
                }
                var lancuch = stringBuilder.toString()
                findViewById<TextView>(R.id.textView_lancuch).text = lancuch

                // Wzorzec
                var wzor = findViewById<EditText>(R.id.EditText2).text.toString()
                findViewById<TextView>(R.id.textView_wzorzec).text = wzor


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
                val wynikBF = BF(lancuch, wzor)

                if (wynikBF.first != null)
                {
                    BF_wynik_textView.text = "Wzorzec występuję w łańcuchu"
                }

                else
                {
                    BF_wynik_textView.text = "Nie znaleziono wzorca"
                }

                // Algorytm: KMP



                // Algorytm BM:

                fun BM(tekst: String, wzor: String): Int {
                    val x = tekst.length
                    val y = wzor.length
                    if (y > x) return -1

                    val tab_przesuniec = IntArray(256) { y }
                    for (i in 0 until y - 1)
                    {
                        tab_przesuniec[wzor[i].toInt()] = y - 1 - i
                    }

                    var i = y - 1
                    var j = y - 1
                    while (i < x) {
                        if (tekst[i] == wzor[j])
                        {
                            if (j == 0) return i
                            i--
                            j--
                        }
                        else
                        {
                            i += maxOf(tab_przesuniec[tekst[i].toInt()], y - j)
                            j = y - 1
                        }
                    }

                    return -1
                }

                var BM_wynik_textView = findViewById<TextView>(R.id.BM_wynik)
                val wynikBM = BM(lancuch, wzor)

                if (wynikBM != -1)
                {
                    BM_wynik_textView.text = "Wzorzec występuję w łańcuchu"
                }

                else if (wynikBM == -1)
                {
                    BM_wynik_textView.text = "Nie znaleziono wzorca"
                }

                // Algorytm RK:
            }
        }
    }
}