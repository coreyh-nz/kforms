package nz.coreyh.kforms

import io.kvision.Application
import io.kvision.CoreModule
import io.kvision.FontAwesomeModule
import io.kvision.Hot
import io.kvision.TailwindcssModule
import io.kvision.panel.root
import io.kvision.remote.registerRemoteTypes
import io.kvision.startApplication

class App : Application() {
    override fun start(state: Map<String, Any>) {
        root("kvapp") {
        }
    }
}

fun main() {
    registerRemoteTypes()
    startApplication(
        ::App,
        js("import.meta.webpackHot").unsafeCast<Hot?>(),
        FontAwesomeModule,
        TailwindcssModule,
        CoreModule,
    )
}
