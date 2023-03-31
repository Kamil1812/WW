@file:Suppress("KotlinConstantConditions")

package com.example.ww

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Button
        findViewById<Button>(R.id.btn).setOnClickListener {

            val ilosc_elementow = findViewById<EditText>(R.id.EditText1).text.toString().toInt()
            val wzor = findViewById<EditText>(R.id.EditText2).text.toString()

            if (!ilosc_elementow.toString().isEmpty() && !wzor.isEmpty()) {

                val dlugosc_wzoru = wzor.length

                if (dlugosc_wzoru > ilosc_elementow)
                {
                    findViewById<TextView>(R.id.textView_error).text = "Wzorzec nie może być dłuższy od łańcucha!"

                    findViewById<TextView>(R.id.textView_lancuch).text = "Łańcuch:"
                    findViewById<TextView>(R.id.textView_wzorzec).text = "Wzorzec:"

                    findViewById<TextView>(R.id.BF_wynik).text = "Wynik:"
                    findViewById<TextView>(R.id.KMP_wynik).text = "Wynik:"
                    findViewById<TextView>(R.id.BM_wynik).text = "Wynik:"
                    findViewById<TextView>(R.id.RK_wynik).text = "Wynik:"
                }

                else if (dlugosc_wzoru <= ilosc_elementow)
                {

                    // Generowanie łańcucha
                    val losowa = java.util.Random()
                    val stringBuilder = StringBuilder()
                    for (i in 1..ilosc_elementow)
                    {
                        stringBuilder.append(losowa.nextInt(10))
                    }

                    val lancuch = stringBuilder.toString()
                    if (ilosc_elementow > 15)
                    {
                        findViewById<TextView>(R.id.textView_lancuch).text = "Zbyt długi łańcuch"
                    }

                    else if (ilosc_elementow <= 15)
                    {
                        findViewById<TextView>(R.id.textView_lancuch).text = lancuch
                    }

                    // Wzorzec
                    findViewById<TextView>(R.id.textView_wzorzec).text = wzor

                    // Czas
                    val BF_czas = findViewById<TextView>(R.id.BF_czas)
                    val KMP_czas = findViewById<TextView>(R.id.KMP_czas)
                    val BM_czas = findViewById<TextView>(R.id.BM_czas)
                    val RK_czas = findViewById<TextView>(R.id.RK_czas)





                    val BF_wynik_textView = findViewById<TextView>(R.id.BF_wynik)
                    val wynikBF = BF(lancuch, wzor)

                    if (wynikBF.first != null)
                    {
                        BF_wynik_textView.text = "Wzorzec występuję w łańcuchu"
                    }

                    else
                    {
                        BF_wynik_textView.text = "Nie znaleziono wzorca"
                    }

                    var czas  = measureTimeMillis{
                        BF(lancuch, wzor)
                    }
                    BF_czas.text = String.format("%s ms", czas)



                    val KMP_wynik_textView = findViewById<TextView>(R.id.KMP_wynik)
                    val wynikKMP = KMP(lancuch, wzor)

                    if (wynikKMP != -1)
                    {
                        KMP_wynik_textView.text = "Wzorzec występuję w łańcuchu"
                    }

                    else if (wynikKMP == -1)
                    {
                        KMP_wynik_textView.text = "Nie znaleziono wzorca"
                    }

                    czas = measureTimeMillis {
                        KMP(lancuch, wzor)
                    }
                    KMP_czas.text = String.format("%s ms", czas)



                    val BM_wynik_textView = findViewById<TextView>(R.id.BM_wynik)
                    val wynikBM = BM(lancuch, wzor)

                    if (wynikBM != -1)
                    {
                        BM_wynik_textView.text = "Wzorzec występuję w łańcuchu"
                    }

                    else if (wynikBM == -1)
                    {
                        BM_wynik_textView.text = "Nie znaleziono wzorca"
                    }

                    czas = measureTimeMillis {
                        BM(lancuch, wzor)
                    }
                    BM_czas.text = String.format("%s ms", czas)



                    val RK_wynik_textView = findViewById<TextView>(R.id.RK_wynik)
                    val wynikRK = RK(lancuch, wzor)

                    if (wynikRK != -1)
                    {
                        RK_wynik_textView.text = "Wzorzec występuję w łańcuchu"
                    }

                    else if (wynikRK == -1)
                    {
                        RK_wynik_textView.text = "Nie znaleziono wzorca"
                    }

                    czas = measureTimeMillis {
                        RK(lancuch, wzor)
                    }
                    RK_czas.text = String.format("%s ms", czas)
                }
            }

            else
            {
                findViewById<TextView>(R.id.textView_error).text = "Podaj dane!"

                findViewById<TextView>(R.id.textView_lancuch).text = "Łańcuch:"
                findViewById<TextView>(R.id.textView_wzorzec).text = "Wzorzec:"

                findViewById<TextView>(R.id.BF_wynik).text = "Wynik:"
                findViewById<TextView>(R.id.KMP_wynik).text = "Wynik:"
                findViewById<TextView>(R.id.BM_wynik).text = "Wynik:"
                findViewById<TextView>(R.id.RK_wynik).text = "Wynik:"
            }
        }
    }
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

    // Algorytm: KMP
    fun KMP(tekst: String, wzor: String): Int {
        val x = tekst.length
        val y = wzor.length

        val nps = Array(y) { 0 }
        var dlugosc = 0
        var i = 1
        while (i < y) {
            if (wzor[i] == wzor[dlugosc])
            {
                dlugosc++
                nps[i] = dlugosc
                i++
            }
            else
            {
                if (dlugosc != 0)
                {
                    dlugosc = nps[dlugosc - 1]
                }
                else
                {
                    nps[i] = 0
                    i++
                }
            }
        }

        var j = 0
        var i_s = 0
        while (i_s < x)
        {
            if (wzor[j] == tekst[i_s])
            {
                j++
                i_s++
            }

            if (j == y)
            {
                return i_s - j
            }
            else if (i_s < x && wzor[j] != tekst[i_s])
            {
                if (j != 0)
                {
                    j = nps[j - 1]
                }
                else
                {
                    i_s++
                }
            }
        }

        return -1
    }

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

    // Algorytm RK:
    fun RK(tekst: String, wzor: String): Int {
        val x = tekst.length
        val y = wzor.length
        if (y > x)
        {
            return -1
        }
        val pierwsza = 101

        var wzor_H = 0
        var tekst_H = 0
        var m = 1
        for (i in 0 until y)
        {
            wzor_H = (wzor_H * pierwsza + wzor[i].toInt()) % Int.MAX_VALUE
            tekst_H = (tekst_H * pierwsza + tekst[i].toInt()) % Int.MAX_VALUE
            if (i < y - 1) m = (m * pierwsza) % Int.MAX_VALUE
        }

        for (i in 0..x - y)
        {
            if (tekst_H == wzor_H && tekst.substring(i, i + y) == wzor)
            {
                return i
            }
            if (i < x - y)
            {
                tekst_H = ((tekst_H - tekst[i].toInt() * m) * pierwsza + tekst[i + y].toInt()) % Int.MAX_VALUE
                if (tekst_H < 0)
                {
                    tekst_H += Int.MAX_VALUE
                }
            }
        }

        return -1
    }
}