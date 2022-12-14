package com.atom.android.bookshop.utils

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.atom.android.bookshop.R
import com.atom.android.bookshop.ui.BookApplication
import java.util.Calendar
import java.util.Locale

fun Context.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toast(resource: Int) {
    Toast.makeText(this, resource, Toast.LENGTH_SHORT).show()
}

fun Context.textSpannable(text: String?, number: String): Spannable {
    val spannable = SpannableString("$text $number")
    val sizeSpanOfText = RelativeSizeSpan(Constants.SIZE_SPAN_OF_TEXT)
    val sizeSpanOfNumber = RelativeSizeSpan(Constants.SIZE_SPAN_OF_NUMBER)
    text?.let {
        spannable.setSpan(
            sizeSpanOfText,
            Constants.FIRST_POSITION,
            text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            sizeSpanOfNumber,
            Constants.FIRST_POSITION,
            number.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val purple: Int = ContextCompat.getColor(this, R.color.red)
        val teal: Int = ContextCompat.getColor(this, R.color.black)
        spannable.setSpan(
            ForegroundColorSpan(purple),
            Constants.FIRST_POSITION,
            text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            ForegroundColorSpan(teal),
            Constants.FIRST_POSITION,
            text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return spannable
}

fun Context.pickDateTime(action: (year: Int, month: Int, day: Int, hour: Int, minute: Int) -> Unit) {
    Locale.setDefault(Locale(Constants.DEFAULT_LOCALE_LANGUAGE, Constants.DEFAULT_LOCALE_COUNTRY));
    val currentDateTime = Calendar.getInstance()
    val startYear = currentDateTime.get(Calendar.YEAR)
    val startMonth = currentDateTime.get(Calendar.MONTH)
    val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
    val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
    val startMinute = currentDateTime.get(Calendar.MINUTE)
    DatePickerDialog(
        this,
        R.style.AlertDialogStylePickerDate,
        DatePickerDialog.OnDateSetListener { _, year, month, day ->
            TimePickerDialog(
                this,
                R.style.AlertDialogStylePickerDate,
                TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                    action(
                        year,
                        month,
                        day,
                        hour,
                        minute
                    )
                },
                startHour,
                startMinute,
                true
            ).apply {
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                show()
            }
        },
        startYear,
        startMonth,
        startDay
    ).apply {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        show()
    }
}

fun Context.showAlertDialogNetwork() {
    AlertDialog.Builder(this).apply {
        setTitle(context.getString(R.string.title_alert_lost_network))
        setMessage(context.getString(R.string.mess_alert_lost_network))
        setPositiveButton(context.getString(R.string.text_confirm)) { _, _ -> }
        show()
    }
}

fun Context.registerNetwork() {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()
    connectivityManager?.requestNetwork(
        networkRequest,
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                BookApplication.isConnectedInternet = true
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                toast(R.string.mess_alert_lost_network)
                BookApplication.isConnectedInternet = false
            }
        })
}
