package nz.coreyh.kforms.component.form.input

import io.kvision.html.Label
import io.kvision.html.span
import nz.coreyh.kforms.common.extension.addCssClasses

class CsInputLabel(
    forId: String,
    text: String,
    required: Boolean = false,
    init: CsInputLabel.() -> Unit,
) : Label(forId = forId) {
    init {
        addCssClasses(
            "text-sm font-medium text-neutral-800",
            "data-invalid:border-red-600",
        )

        +text
        if (required) {
            span(" *") {
                addCssClasses("text-red-600")
            }
        }

        init.invoke(this)
    }
}
