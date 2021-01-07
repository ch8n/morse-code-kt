package com.ch8n.morse

fun String.toOption(): Options {
    return when {
        Options.ENCODE.label in this -> Options.ENCODE
        Options.DECODE.label in this -> Options.DECODE
        else -> Options.ERROR
    }
}

enum class Options(val label: String, val value: Int) {
    ERROR(label = "ERROR", value = 0),
    ENCODE(label = "Encode", value = 1),
    DECODE(label = "Decode", value = 1)
}