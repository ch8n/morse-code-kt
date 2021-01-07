package com.ch8n.morse

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.mordant.TermColors
import com.yg.kotlin.inquirer.components.promptList
import com.yg.kotlin.inquirer.core.KInquirer

fun main(args: Array<String>) {
    PromptMorseOption().main(args)
    App(args).main(args)
}


class PromptMorseOption : NoOpCliktCommand() {
    override fun run() {
        super.run()
        echo(Res.View.morseOptions)
    }
}

class App(private val args: Array<String>) : CliktCommand() {

    override fun run() {
        val choice: Options = KInquirer.promptList(
            Res.StyledRes.selectTitle, Res.StyledRes.selectOption
        ).toOption()

        when (choice) {
            Options.ERROR -> echo("Invalid option")
            Options.ENCODE -> MorseEncoder().main(args)
            Options.DECODE -> MorseDecoder().main(args)
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