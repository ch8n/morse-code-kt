package com.ch8n.morse

val morseCodeTranslator = mapOf<String, String>(
        ".-" to "A",
        "-..." to "B",
        "-.-." to "C",
        "-.." to "D",
        "." to "E",
        "..-." to "F",
        "--." to "G",
        "...." to "H",
        ".." to "I",
        ".---" to "J",
        "-.-" to "K",
        ".-.." to "L",
        "--" to "M",
        "-." to "N",
        "---" to "O",
        ".--." to "P",
        "--.-" to "Q",
        ".-." to "R",
        "..." to "S",
        "-" to "T",
        "..-" to "U",
        "...-" to "V",
        ".--" to "W",
        "-..-" to "X",
        "-.--" to "Y",
        "--.." to "Z",
        ".----" to "1",
        "..---" to "2",
        "...--" to "3",
        "....-" to "4",
        "....." to "5",
        "-...." to "6",
        "--..." to "7",
        "---.." to "8",
        "----." to "9",
        "-----" to "0",
        "" to " ",
        ".-.-.-" to ".",
        "--..--" to ",",
        "---..." to ".",
        "..--.." to "?",
        "-.-.--" to "!",
        "...---..." to "SOS",
        "-....-" to "''",
        "-..-." to "/",
        "-.--.-" to "()",
        ".--.-." to "@",
        "-...-" to "="
)

val morseCodeConverter: Map<String, String>
        get() {
                return morseCodeTranslator.entries.associate { (key, value) -> value.toLowerCase() to key }
        }

fun String.toMorse(): String {
        val morseMapper = morseCodeConverter
        val sentence = this
        val words = sentence.split(" ")
        val morse = words
                .map { word ->
                        return@map word
                                .toCharArray()
                                .map { alpha -> morseMapper.get(alpha.toString()) }
                                .joinToString(separator = " ")
                }.joinToString(separator = "   ")
        return morse
}


fun String.decodeMorse(): String {
        return this
                .replace("  ", " ")
                .split(" ")
                .map { morseCodeTranslator[it] }
                .joinToString("")
}