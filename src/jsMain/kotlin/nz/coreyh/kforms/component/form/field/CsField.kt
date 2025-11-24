package nz.coreyh.kforms.component.form.field

import io.kvision.core.Widget
import io.kvision.panel.SimplePanel
import nz.coreyh.kforms.common.extension.addCssClasses
import nz.coreyh.kforms.common.validation.ValidationRule
import nz.coreyh.kforms.component.form.input.CsInputErrorMessage
import nz.coreyh.kforms.component.form.input.CsInputLabel

abstract class CsField<T>(
    name: String,
    label: String,
    required: Boolean = false,
) : SimplePanel() {
    protected abstract val inputWidget: Widget
    protected val labelWidget: CsInputLabel = CsInputLabel(name, label, required) {}
    protected val errorWidget: CsInputErrorMessage = CsInputErrorMessage {}
    internal val validationRules = mutableListOf<ValidationRule<T>>()

    init {
        addCssClasses("flex flex-col gap-1.5")
    }

    fun validator(rule: ValidationRule<T>) {
        validationRules.add(rule)
    }

    fun validate(): Boolean {
        val value = getValue()
        for (rule in validationRules) {
            if (!rule.validate(value)) {
                inputWidget.setAttribute("data-invalid", "true")
                labelWidget.setAttribute("data-invalid", "true")
                errorWidget.setError(rule.errorMessage)
                return false
            }
        }
        inputWidget.removeAttribute("data-invalid")
        labelWidget.removeAttribute("data-invalid")
        errorWidget.clearError()
        return true
    }

    override fun focus() {
        inputWidget.focus()
    }

    abstract fun getValue(): T
}
