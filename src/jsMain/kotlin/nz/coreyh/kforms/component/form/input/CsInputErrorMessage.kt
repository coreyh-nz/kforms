package nz.coreyh.kforms.component.form.input

import io.kvision.core.Container
import io.kvision.html.Span
import nz.coreyh.kforms.common.extension.addCssClasses

class CsInputErrorMessage(
    init: CsInputErrorMessage.() -> Unit,
) : Span() {
    init {
        addCssClasses("text-xs text-red-600 italic min-h-[1rem]")
        init.invoke(this)
    }

    fun setError(errorMessage: String) {
        content = errorMessage
    }

    fun clearError() {
        content = null
    }
}

fun Container.csInputErrorMessage(init: CsInputErrorMessage.() -> Unit) = CsInputErrorMessage(init).also { add(it) }
