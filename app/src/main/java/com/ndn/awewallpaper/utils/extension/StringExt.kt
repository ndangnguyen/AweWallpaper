package com.ndn.awewallpaper.utils.extension

import android.util.Patterns
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

fun String.toInt(): Int {
    return try {
        Integer.parseInt(this)
    } catch (e: NumberFormatException) {
        Integer.MIN_VALUE
    }
}

fun String.toDouble(): Double {
    return try {
        java.lang.Double.parseDouble(this)
    } catch (e: NumberFormatException) {
        Double.MIN_VALUE
    }
}

@Throws(ParseException::class)
fun String.toDate(format: String): Date {
    val parser = SimpleDateFormat(format, Locale.getDefault())
    return parser.parse(this)
}

@Throws(ParseException::class)
fun String.toDateWithFormat(inputFormat: String, outputFormat: String): String {
    val gmtTimeZone = TimeZone.getTimeZone("UTC")
    val inputDateTimeFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
    inputDateTimeFormat.timeZone = gmtTimeZone

    val outputDateTimeFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
    outputDateTimeFormat.timeZone = gmtTimeZone
    return outputDateTimeFormat.format(inputDateTimeFormat.parse(this))
}

@Throws(ParseException::class)
fun String.toDateWithFormat(inputFormat: String, outputFormat: String,
        outputTimeZone: TimeZone = TimeZone.getDefault()): String {
    val gmtTimeZone = TimeZone.getTimeZone("UTC")
    val inputDateTimeFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
    inputDateTimeFormat.timeZone = gmtTimeZone

    val outputDateTimeFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
    outputDateTimeFormat.timeZone = outputTimeZone
    return outputDateTimeFormat.format(inputDateTimeFormat.parse(this))
}

fun String.validWithPattern(pattern: Pattern): Boolean {
    return pattern.matcher(toLowerCase()).find()
}

fun String.validWithPattern(regex: String): Boolean {
    return Pattern.compile(regex).matcher(this).find()
}

fun List<String>.toStringWithFormatPattern(format: String): String {
    if (this.isEmpty()) {
        return ""
    }
    val builder = StringBuilder()
    for (s in this) {
        builder.append(s)
        builder.append(format)
    }
    var result = builder.toString()
    result = result.substring(0, result.length - format.length)
    return result
}

fun String.removeWhitespaces(): String {
    return this.replace("[\\s-]*".toRegex(), "")
}

fun String.subString(beginInDex: Int, endIndex: Int): String {
    return this.substring(beginInDex, endIndex)
}

fun String.insert(index: Int, contentInsert: String): String {
    val builder = StringBuilder(this)
    builder.insert(index, contentInsert)
    return builder.toString()
}

fun List<String>.convertStringToListStringWithFormatPattern(format: String): String {
    if (this.isEmpty()) {
        return ""
    }
    val builder = StringBuilder()
    for (s in this) {
        builder.append(s)
        builder.append(format)
    }
    var result = builder.toString()
    result = result.substring(0, result.length - format.length)
    return result
}

fun String.toIntOrZero() = if (this.toIntOrNull() == null) 0 else this.toInt()

fun String.isNumeric(): Boolean = this matches "-?\\d+(\\.\\d+)?".toRegex()

private const val STR_MAX_CHAR_COUNT = 14
fun String.splitStringToDisplay(): String {
    if (this.isBlank()) {
        return ""
    }

    var stringToFormat = this
    if (stringToFormat.length > STR_MAX_CHAR_COUNT) {
        stringToFormat = stringToFormat.substring(0, STR_MAX_CHAR_COUNT) + "..."
    }
    return stringToFormat
}

fun String.containsWebUrl() = Patterns.WEB_URL.matcher(this).find()
