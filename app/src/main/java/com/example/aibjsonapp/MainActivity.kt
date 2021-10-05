package com.example.aibjsonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var Currencyspinner: Spinner
    lateinit var Datetext: TextView
    lateinit var userinputEURO: EditText
    lateinit var convertBTN: Button
    lateinit var TheResult: TextView
    private var curencyDetails: TheConvertDetails? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var selectedCurrencyy: Int = 0

        Datetext = findViewById(R.id.textViewDate)
        userinputEURO = findViewById(R.id.editTextEURO)
        convertBTN = findViewById(R.id.buttonConvert)
        TheResult = findViewById(R.id.textViewResult)

        /////////
        val currencyarray = arrayListOf("inr", "usd", "aud", "sar", "cny", "jpy")


        //val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, currencyarray)
        // Currencyspinner.adapter = arrayAdapter
        // Currencyspinner.onItemClickListener = object : AdapterView.OnItemSelectedListener



        if (Currencyspinner != null) {
            val arrayAdapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, currencyarray)
            Currencyspinner.adapter = arrayAdapter
            Currencyspinner.onItemClickListener = object : AdapterView.OnItemSelectedListener,
                AdapterView.OnItemClickListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    selectedCurrencyy = position
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                }
            }


        }
        convertBTN.setOnClickListener {
            var sel = userinputEURO.text.toString()
            var currency: Double = sel.toDouble()

            getCurrency(onResult = {
                curencyDetails = it

                when (selectedCurrencyy)
                {
                    0 -> disp(calc(curencyDetails?.eur?.inr?.toDouble(), currency));
                    1 -> disp(calc(curencyDetails?.eur?.usd?.toDouble(), currency));
                    2 -> disp(calc(curencyDetails?.eur?.aud?.toDouble(), currency));
                    3 -> disp(calc(curencyDetails?.eur?.sar?.toDouble(), currency));
                    4 -> disp(calc(curencyDetails?.eur?.cny?.toDouble(), currency));
                    5 -> disp(calc(curencyDetails?.eur?.jpy?.toDouble(), currency));
                }
            })
        }

    }
    private fun disp(calc: Double) {


        TheResult.text = "result " + calc
    }

    private fun calc(i: Double?, sel: Double): Double {
        var s = 0.0
        if (i != null) {
            s = (i * sel)
        }
        return s
    }
    
    private fun getCurrency(onResult: (TheConvertDetails?) -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        if (apiInterface != null) {
            apiInterface.getCurrency()?.enqueue(object : Callback<TheConvertDetails> {
                override fun onResponse(
                    call: Call<TheConvertDetails>,
                    response: Response<TheConvertDetails>
                ) {
                    onResult(response.body())

                }

                override fun onFailure(call: Call<TheConvertDetails>, t: Throwable) {
                    onResult(null)
                    Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_SHORT).show();
                }

            })

    }
}}