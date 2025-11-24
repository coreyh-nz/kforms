package nz.coreyh.kforms

import io.kvision.Application
import io.kvision.CoreModule
import io.kvision.FontAwesomeModule
import io.kvision.Hot
import io.kvision.TailwindcssModule
import io.kvision.html.div
import io.kvision.panel.root
import io.kvision.remote.registerRemoteTypes
import io.kvision.startApplication
import nz.coreyh.kforms.views.loginForm

class App : Application() {
    override fun start(state: Map<String, Any>) {
        root("kvapp") {
            div(className = "flex items-center justify-center min-h-screen ") {
                loginForm()
            }
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
