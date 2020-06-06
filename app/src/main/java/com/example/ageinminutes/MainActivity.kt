package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }

    }

    fun clickDatePicker(view : View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        var dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "The chosen year is "+year , Toast.LENGTH_LONG).show()
            val selectedDate = "${selectedDayOfMonth}/${selectedMonth+1}/${selectedYear}"
            tvSelectedDate.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateToMinutes = theDate!!.time / 6000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMinutes = currentDate!!.time / 6000
            val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
            tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
        }, year, month, day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }


}
