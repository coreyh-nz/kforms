package nz.coreyh.kforms.component.form.input

import io.kvision.core.AttributeSetBuilder
import io.kvision.core.Container
import io.kvision.core.onEvent
import io.kvision.panel.SimplePanel
import io.kvision.snabbdom.VNode
import nz.coreyh.kforms.common.extension.addCssClasses

class CsInputSelect(
    options: List<Pair<String, String>>,
    init: CsInputSelect.() -> Unit,
) : SimplePanel() {
    var value: String? = null
        private set

    init {
        onEvent {
            change = {
                value = this@CsInputSelect.getElement()?.asDynamic().value as? String
            }
        }

        addCssClasses(
            "w-full rounded-lg border border-neutral-300 bg-neutral-50 px-3 py-2 text-sm",
            "text-neutral-900 shadow-sm outline-none transition appearance-none",
            "hover:border-neutral-400",
            "focus:border-neutral-600 focus:bg-white focus:ring-2 focus:ring-neutral-300",
            "data-invalid:border-red-600",
        )

        // add empty unselectable placeholder choice
        add(
            csInputSelectOption("", "Select...") {
                setAttribute("selected", "true")
                setAttribute("disabled", "true")
                setAttribute("hidden", "true")
            },
        )

        options.forEach { (value, label) ->
            add(csInputSelectOption(value, label) { })
        }

        init.invoke(this)
    }

    override fun render(): VNode = render("select", childrenVNodes())
}

class CsInputSelectOption(
    val value: String,
    label: String,
    init: CsInputSelectOption.() -> Unit,
) : SimplePanel() {
    init {
        init.invoke(this)

        +label
    }

    override fun render(): VNode = render("option", childrenVNodes())

    override fun buildAttributeSet(attributeSetBuilder: AttributeSetBuilder) {
        super.buildAttributeSet(attributeSetBuilder)
        attributeSetBuilder.add("value", value)
    }
}

fun Container.csInputSelectOption(
    value: String,
    label: String,
    init: CsInputSelectOption.() -> Unit,
) = CsInputSelectOption(value, label, init).also { add(it) }
