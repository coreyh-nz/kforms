package nz.coreyh.kforms.component.form.field

import io.kvision.html.InputType
import io.kvision.html.button
import io.kvision.html.div
import io.kvision.state.ObservableValue
import io.kvision.state.bind
import nz.coreyh.kforms.common.extension.addCssClasses
import nz.coreyh.kforms.component.form.CsForm
import nz.coreyh.kforms.component.form.input.CsInput
import kotlin.reflect.KProperty1

class CsPasswordField<T>(
    key: KProperty1<*, *>,
    label: String,
    required: Boolean = false,
    init: CsPasswordField<T>.() -> Unit,
) : CsField<String>(key.name, label, required) {
    private val textVisible = ObservableValue(false)
    override val inputWidget = CsInput(key.name) {}

    init {
        add(labelWidget)
        div(className = "relative") {
            add(inputWidget.apply { addCssClasses("pr-10") })
            button("", className = "absolute inset-y-0 right-0 flex items-center pr-3 text-gray-500") {
                onClick { textVisible.value = !textVisible.value }
                bind(textVisible, removeChildren = false) {
                    icon = "fa-solid ${if (it) "fa-eye-slash" else "fa-eye"}"
                    inputWidget.type = if (it) InputType.TEXT else InputType.PASSWORD
                }
            }
        }
        add(errorWidget)

        init.invoke(this)
    }

    override fun getValue(): String = inputWidget.value ?: ""
}

fun <T> CsForm<T>.csPasswordField(
    key: KProperty1<T, *>,
    label: String,
    required: Boolean = false,
    init: CsPasswordField<T>.() -> Unit,
) = CsPasswordField(key, label, required, init).also {
    bind(key, it)
    add(it)
}
