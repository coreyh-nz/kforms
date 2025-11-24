package nz.coreyh.kforms.component.form.field

import nz.coreyh.kforms.component.form.CsForm
import nz.coreyh.kforms.component.form.input.CsInput
import kotlin.reflect.KProperty1

class CsTextField<T>(
    key: KProperty1<*, *>,
    label: String,
    required: Boolean = false,
    init: CsTextField<T>.() -> Unit,
) : CsField<String>(key.name, label, required) {
    override val inputWidget = CsInput(key.name) {}

    init {
        add(labelWidget)
        add(inputWidget)
        add(errorWidget)

        init.invoke(this)
    }

    override fun getValue(): String = inputWidget.value ?: ""
}

fun <T> CsForm<T>.csTextField(
    key: KProperty1<T, *>,
    label: String,
    required: Boolean = false,
    init: CsTextField<T>.() -> Unit,
) = CsTextField(key, label, required, init).also {
    bind(key, it)
    add(it)
}
