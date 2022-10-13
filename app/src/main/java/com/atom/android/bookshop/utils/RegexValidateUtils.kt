package com.atom.android.bookshop.utils

import com.atom.android.bookshop.utils.Constants.EMAIL_PATTERN
import com.atom.android.bookshop.utils.Constants.NUMBER_PHONE_PATTERN
import com.atom.android.bookshop.utils.Constants.PASS_PATTERN
import java.util.regex.Pattern

fun isPhoneNumber(phoneNumber: String?): Boolean {
    return Pattern.matches(NUMBER_PHONE_PATTERN, phoneNumber)
}

fun isEmail(email: String?): Boolean {
    if (email.isNullOrEmpty())
        return false
    return Pattern.matches(EMAIL_PATTERN, email)
}

fun isPassword(pass: String?): Boolean {
    return Pattern.matches(PASS_PATTERN, pass)
}
