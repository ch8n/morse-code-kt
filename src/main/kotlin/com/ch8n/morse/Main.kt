package com.ch8n.morse

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.mordant.TermColors
import com.yg.kotlin.inquirer.components.promptConfirm
import com.yg.kotlin.inquirer.components.promptInput
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

        val retry: Boolean = KInquirer.promptConfirm(Res.StyledRes.tryAgainTitle, default = false)
        if (retry) {
            run()
        }

    }

}


class MorseEncoder : CliktCommand() {
    override fun run() {
        val message: String = KInquirer.promptInput(
            Res.StyledRes.enterUserMessageTitle
        )
        val morse = Res.stylize { it.bold(message.toMorse()) }
        echo(Res.View.outputTable(morse))
    }
}

class MorseDecoder : CliktCommand() {

    override fun run() {

        val morse: String = KInquirer.promptInput(
            Res.StyledRes.enterUserMorseTitle
        )
        val message = Res.stylize { it.bold(morse.decodeMorse()) }
        echo(Res.View.outputTable(message))
    }
}


