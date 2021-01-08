package com.ch8n.morse

import com.github.ajalt.mordant.TermColors
import com.jakewharton.picnic.Table
import com.jakewharton.picnic.table

/***
 * Author : Chetan Gupta
 * Website : Chetangupta.net
 * Series : BigBrainKotlin - BBK-6 : https://chetangupta.net/bbk6/
 * Convert a given message to Morse Code or A given morse code to String message
 * License : This work is licensed under a Creative Commons International License
 */
object Res {

    // Provide coloring and text options
    private val STYLE = TermColors()

    // inline styling of string
    fun stylize(style: (TermColors) -> String): String {
        return style.invoke(STYLE)
    }

    // Dimension values
    object DimenRes {
        const val pad_2 = 2
    }


    // String values
    object StringRes {
        const val app_name = "Big-Brain-Kotlin: Morse Code"
        const val options_title = "Options:"
        const val select_title = "Select:"
        const val try_again_title = "Again?"

        object Spanners {
            val options = listOf(Options.ENCODE, Options.DECODE)
        }
    }


    // styled Strings values
    object StyledRes {
        val optionTitle by lazy { STYLE.bold(STYLE.white(StringRes.options_title)) }

        val options by lazy {
            StringRes.Spanners.options.mapIndexed { index, option ->
                STYLE.gray(" ${index + 1}) ${option.label}")
            }
        }

        val selectTitle by lazy { STYLE.bold(STYLE.white(StringRes.select_title)) }

        val selectOption by lazy {
            StringRes.Spanners.options.mapIndexed { index, option ->
                STYLE.brightBlue(" ${index + 1}) ${option.label}")
            }
        }

        val enterUserMessageTitle by lazy { "Enter Your ${STYLE.brightGreen("Message")}:" }

        val enterUserMorseTitle by lazy { "Enter Your ${STYLE.brightGreen("Morse Code")}:" }

        val tryAgainTitle by lazy { STYLE.bold(STYLE.white(StringRes.try_again_title)) }

    }


    // Merged Styles components
    object View {
        val morseOptionsTable by lazy {
            table {
                cellStyle {
                    border = true
                    paddingLeft = DimenRes.pad_2
                    paddingRight = DimenRes.pad_2
                }
                row(STYLE.bold(STYLE.yellow(StringRes.app_name)))
                row(
                    """
${StyledRes.optionTitle}
${StyledRes.options.joinToString(separator = "\n")}
                    """.trimIndent()
                )
            }
        }

        fun displayResultTable(result: String): Table {
            return table {
                cellStyle {
                    border = true
                    paddingLeft = DimenRes.pad_2
                    paddingRight = DimenRes.pad_2
                }
                row(result)
            }
        }
    }

}

