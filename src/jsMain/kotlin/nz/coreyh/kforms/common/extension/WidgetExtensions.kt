package nz.coreyh.kforms.common.extension

import io.kvision.core.Widget

fun Widget.addCssClasses(vararg classes: String) {
    singleRender {
        classes
            .flatMap { it.split(" ") }
            .forEach { addCssClass(it) }
    }
}
