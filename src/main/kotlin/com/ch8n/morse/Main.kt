package com.ch8n.morse

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.mordant.TermColors
import com.jakewharton.picnic.table

fun main(args: Array<String>) {
    Splash().main(args)
    App(args).main(args)
}

class Splash : NoOpCliktCommand() {
    override fun run() {
        super.run()

        val c = TermColors()

        val result = table {
            cellStyle {
                border = true
                paddingLeft = 2
                paddingRight = 2
            }
            row(c.bold(c.yellow("Big-Brain-Kotlin: Morse Code")))
            row(
                """
            ${c.bold("Options:")}
            ${c.gray("1) Encode")}
            ${c.gray("2) Decode")}
            """.trimIndent()
            )
        }

        echo(result)

    }
}

class App(private val args: Array<String>) : CliktCommand() {

    private val inputChoiceText = with(TermColors()) { "Enter option ${brightBlue("1")} or ${brightBlue("2")}" }

    private val choice: String by option()
        .prompt(inputChoiceText)

    override fun run() {

        when (choice) {
            "1" -> MorseEncoder().main(args)
            "2" -> MorseDecoder().main(args)
            else -> echo("Invalid option")
        }
    }

}


class MorseEncoder : CliktCommand() {

    private val inputMessageText = with(TermColors()) {
        "Enter Your ${brightGreen("Message")}"
    }

    private val message: String by option(help = "String to encode")
        .prompt(inputMessageText)

    override fun run() {
        echo(message.toMorse())
    }
}

class MorseDecoder : CliktCommand() {

    private val inputMorseText = with(TermColors()) {
        "Enter ${brightGreen("Morse code")}"
    }

    private val message: String by option(help = "String to decode")
        .prompt(inputMorseText)

    override fun run() {
        echo(message.decodeMorse())
    }
}


fun String.toMorse(): String {
    val morseMapper = morseCodeConverter
    val sentense = this
    val words = sentense.split(" ")
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