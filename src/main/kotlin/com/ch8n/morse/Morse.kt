package com.ch8n.morse

/***
 * Author : Chetan Gupta
 * Website : Chetangupta.net
 * Series : BigBrainKotlin - BBK-6 : https://chetangupta.net/bbk6/
 * Convert a given message to Morse Code or A given morse code to String message
 * License : This work is licensed under a Creative Commons International License
 */


/**
 * Checkout BigBrainKotlin - BBK-6 : https://chetangupta.net/bbk6/
 * map of all supported characters morse character
 */
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

/**
 * Checkout BigBrainKotlin - BBK-6 : https://chetangupta.net/bbk6/
 * map of supported english characters
 */
val morseCodeConverter: Map<String, String>
        get() {
                return morseCodeTranslator.entries.associate { (key, value) -> value.toLowerCase() to key }
        }

/**
 * Checkout BigBrainKotlin - BBK-6 : https://chetangupta.net/bbk6/
 * convert string to Morse
 */
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

/**
 * Checkout BigBrainKotlin - BBK-6 : https://chetangupta.net/bbk6/
 * convert morse to english
 */
fun String.decodeMorse(): String {
        return this
                .replace("  ", " ")
                .split(" ")
                .map { morseCodeTranslator[it] }
                .joinToString("")
}