package com.ch8n.morse

/***
 * Author : Chetan Gupta
 * Website : Chetangupta.net
 * Series : BigBrainKotlin - BBK-6 : https://chetangupta.net/bbk6/
 * Convert a given message to Morse Code or A given morse code to String message
 * License : This work is licensed under a Creative Commons International License
 */


/***
 * Convert String input from user to Options that can be
 * performed in script.
 */
fun String.toOption(): Options {
    return when {
        Options.ENCODE.label in this -> Options.ENCODE
        Options.DECODE.label in this -> Options.DECODE
        else -> Options.ERROR
    }
}


/***
 * Option menu actions
 */
enum class Options(val label: String, val value: Int) {
    ERROR(label = "Error", value = 0),
    ENCODE(label = "Encode", value = 1),
    DECODE(label = "Decode", value = 1)
}