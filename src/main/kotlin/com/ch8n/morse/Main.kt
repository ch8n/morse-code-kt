package com.ch8n.morse

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.yg.kotlin.inquirer.components.promptConfirm
import com.yg.kotlin.inquirer.components.promptInput
import com.yg.kotlin.inquirer.components.promptList
import com.yg.kotlin.inquirer.core.KInquirer

/***
 * Author : Chetan Gupta
 * Website : Chetangupta.net
 * Series : BigBrainKotlin - BBK-6 : https://chetangupta.net/bbk6/
 * Convert a given message to Morse Code or A given morse code to String message
 * License : This work is licensed under a Creative Commons International License
 */


/**
 * Entry Point of the Application
 */
fun main(args: Array<String>) {
    PromptMorseOption().main(args)
    App(args).main(args)
}

/**
 * Prints the table of Menu Options
 */
class PromptMorseOption : NoOpCliktCommand() {
    override fun run() {
        super.run()
        echo(Res.View.morseOptionsTable)
    }
}


/**
 * Controller that Ask user for choice prompts from the given options,
 * and repeat functionality again on end.
 */
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

        val retry: Boolean = KInquirer.promptConfirm(Res.StyledRes.tryAgainTitle, default = false)
        if (retry) {
            run()
        }

    }

}

/**
 * Controller that takes input in English and convert it to morse code
 */
class MorseEncoder : CliktCommand() {
    override fun run() {
        val message: String = KInquirer.promptInput(
            Res.StyledRes.enterUserMessageTitle
        )
        val morse = Res.stylize { it.bold(message.toMorse()) }
        echo(Res.View.displayResultTable(morse))
    }
}


/**
 * Controller that takes input in Morse and convert it to English
 */
class MorseDecoder : CliktCommand() {

    override fun run() {
        val morse: String = KInquirer.promptInput(
            Res.StyledRes.enterUserMorseTitle
        )
        val message = Res.stylize { it.bold(morse.decodeMorse()) }
        echo(Res.View.displayResultTable(message))
    }
}


