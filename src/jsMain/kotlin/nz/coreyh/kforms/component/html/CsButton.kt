package nz.coreyh.kforms.component.html

import io.kvision.core.Container
import io.kvision.html.Button
import io.kvision.html.ButtonType
import nz.coreyh.kforms.common.extension.addCssClasses

enum class CsButtonVariant {
    PRIMARY,
    SECONDARY,
    GHOST,
}

class CsButton(
    text: String,
    variant: CsButtonVariant = CsButtonVariant.PRIMARY,
    fullWidth: Boolean = false,
    type: ButtonType = ButtonType.BUTTON,
    init: CsButton.() -> Unit = {},
) : Button(text, type = type) {
    init {
        addCssClasses(
            "inline-flex items-center justify-center",
            "rounded-lg px-3 py-2 text-sm font-medium",
            "border shadow-sm",
            "transition focus:outline-none focus:ring-2 focus:ring-offset-1",
        )

        if (fullWidth) {
            addCssClasses("w-full")
        }

        when (variant) {
            CsButtonVariant.PRIMARY ->
                addCssClasses(
                    "bg-neutral-900 text-neutral-50 border-neutral-900",
                    "hover:bg-neutral-800 hover:border-neutral-800",
                    "focus:ring-neutral-300",
                    "disabled:bg-neutral-300 disabled:border-neutral-300 disabled:text-neutral-500 disabled:cursor-not-allowed",
                )

            CsButtonVariant.SECONDARY ->
                addCssClasses(
                    "bg-neutral-100 text-neutral-900 border-neutral-300",
                    "hover:bg-neutral-200 hover:border-neutral-400",
                    "focus:ring-neutral-300",
                    "disabled:bg-neutral-100 disabled:border-neutral-200 disabled:text-neutral-400 disabled:cursor-not-allowed",
                )

            CsButtonVariant.GHOST ->
                addCssClasses(
                    "bg-transparent text-neutral-800 border-transparent",
                    "hover:bg-neutral-100 hover:border-neutral-200",
                    "focus:ring-neutral-300",
                    "disabled:text-neutral-400 disabled:hover:bg-transparent disabled:border-transparent disabled:cursor-not-allowed",
                )
        }

        init(this)
    }
}

fun Container.csButton(
    text: String,
    variant: CsButtonVariant = CsButtonVariant.PRIMARY,
    fullWidth: Boolean = false,
    type: ButtonType = ButtonType.BUTTON,
    init: CsButton.() -> Unit = {},
): CsButton = CsButton(text, variant, fullWidth, type, init).also { add(it) }
