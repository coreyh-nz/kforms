package nz.coreyh.kforms.component.form.input

import io.kvision.core.Container
import io.kvision.core.onEvent
import io.kvision.html.Input
import io.kvision.html.InputType
import nz.coreyh.kforms.common.extension.addCssClasses

class CsInput(
    name: String,
    type: InputType = InputType.TEXT,
    init: CsInput.() -> Unit,
) : Input(type) {
    init {
        id = name
        onEvent {
            // kvision doesn't automatically bind the input to the value property...
            input = {
                value = this@CsInput.getElement()?.asDynamic().value as? String
            }
            change = {
                value = this@CsInput.getElement()?.asDynamic().value as? String
            }
        }

        addCssClasses(
            "w-full rounded-lg border border-neutral-300 bg-neutral-50 px-3 py-2 text-sm ",
            "text-neutral-900 shadow-sm outline-none transition",
            "placeholder:text-neutral-400",
            "hover:border-neutral-400",
            "focus:border-neutral-600 focus:bg-white focus:ring-2 focus:ring-neutral-300",
            "data-invalid:border-red-600",
        )

        init.invoke(this)
    }
}

fun Container.csInput(
    name: String,
    type: InputType = InputType.TEXT,
    init: CsInput.() -> Unit,
) = CsInput(name, type, init).also { add(it) }
