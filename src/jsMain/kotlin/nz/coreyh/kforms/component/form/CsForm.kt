package nz.coreyh.kforms.component.form

import io.kvision.core.Container
import io.kvision.core.onEvent
import io.kvision.html.ButtonType
import io.kvision.panel.SimplePanel
import io.kvision.snabbdom.VNode
import nz.coreyh.kforms.common.extension.addCssClasses
import nz.coreyh.kforms.component.form.field.CsField
import nz.coreyh.kforms.component.html.CsButton
import nz.coreyh.kforms.component.html.CsButtonVariant
import kotlin.reflect.KProperty1

class CsForm<T>(
    init: CsForm<T>.() -> Unit,
) : SimplePanel() {
    private val bindings = mutableMapOf<String, CsField<out Any?>>()
    private var onSubmit: (CsForm<T>.() -> Unit)? = null

    init {
        addCssClasses("flex flex-col gap-4")

        onEvent {
            submit = {
                it.preventDefault()

                val results = bindings.values.map { field -> field to field.validate() }
                val allValid = results.all { (_, valid) -> valid }

                if (allValid) {
                    onSubmit?.invoke(this@CsForm)
                } else {
                    results
                        .firstOrNull { (_, valid) -> !valid }
                        ?.first
                        ?.focus()
                }
            }
        }

        init.invoke(this)
    }

    override fun render(): VNode = render("form", childrenVNodes())

    @Suppress("UNCHECKED_CAST")
    fun <V> getValue(property: KProperty1<T, V>): V {
        val field =
            bindings[property.name] as? CsField<V>
                ?: throw IllegalArgumentException("No field bound for ${property.name}")
        return field.getValue()
    }

    fun csSubmitButton(init: CsButton.() -> Unit) =
        CsButton(
            text = "Submit",
            variant = CsButtonVariant.PRIMARY,
            fullWidth = false,
            type = ButtonType.SUBMIT,
            init,
        ).also { add(it) }

    fun onSubmit(handler: CsForm<T>.() -> Unit) {
        onSubmit = handler
    }

    internal fun bind(
        property: KProperty1<T, *>,
        input: CsField<*>,
    ) {
        bindings[property.name] = input
    }
}

fun <T> Container.csForm(init: CsForm<T>.() -> Unit) = CsForm(init).also { add(it) }
