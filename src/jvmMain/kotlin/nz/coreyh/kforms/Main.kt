package nz.coreyh.kforms

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.compression.Compression
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.kvision.remote.registerRemoteTypes

fun Application.main() {
    registerRemoteTypes()
    install(Compression)
    install(WebSockets)

    routing {
//        getAllServiceManagers().forEach { applyRoutes(it) }
    }
}
