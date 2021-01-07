package com.ch8n.morse

import com.github.ajalt.mordant.TermColors
import com.jakewharton.picnic.Table
import com.jakewharton.picnic.table

object Res {
    private val STYLE = TermColors()

    fun stylize(style: (TermColors) -> String): String {
        return style.invoke(STYLE)
    }


    object DimenRes {
        const val pad_2 = 2
    }

    object StringRes {
        const val app_name = "Big-Brain-Kotlin: Morse Code"
        const val options_title = "Options:"
        const val select_title = "Select:"
        const val try_again_title = "Again?"
        const val yes = "Yes"
        const val no = "Bye"

        object Spanners {
            val options = listOf(Options.ENCODE, Options.DECODE)
        }
    }

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
        val yesMessage by lazy { STYLE.bold(STYLE.brightBlue(StringRes.yes)) }
        val noMessage by lazy { STYLE.bold(STYLE.brightRed(StringRes.no)) }
    }

    object View {
        val morseOptions by lazy {
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

        fun outputTable(result: String): Table {
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

