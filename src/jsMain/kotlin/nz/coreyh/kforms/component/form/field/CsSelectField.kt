package nz.coreyh.kforms.component.form.field

import io.kvision.html.button
import io.kvision.html.div
import nz.coreyh.kforms.component.form.CsForm
import nz.coreyh.kforms.component.form.input.CsInputSelect
import kotlin.reflect.KProperty1

class CsSelectField<T>(
    key: KProperty1<*, *>,
    label: String,
    required: Boolean = false,
    options: List<Pair<String, String>>,
    init: CsSelectField<T>.() -> Unit,
) : CsField<String>(key.name, label, required) {
    override val inputWidget = CsInputSelect(options) {}

    init {
        add(labelWidget)
        div(className = "relative") {
            add(inputWidget)
            button(
                "",
                icon = "fa-solid fa-chevron-down",
                className = "absolute inset-y-0 right-0 flex items-center pr-3 text-gray-500",
            ) {
                onClick { inputWidget.getElement()?.focus() }
            }
        }
        add(errorWidget)

        init.invoke(this)
    }

    override fun getValue(): String = inputWidget.value ?: ""
}

fun <T> CsForm<T>.csSelectField(
    key: KProperty1<T, *>,
    label: String,
    required: Boolean = false,
    options: List<Pair<String, String>>,
    init: CsSelectField<T>.() -> Unit = {},
) = CsSelectField(key, label, required, options, init).also {
    bind(key, it)
    add(it)
}
