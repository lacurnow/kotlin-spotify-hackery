package org.starter.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.ExperimentalSerializationApi
import org.starter.apps.ComponentRegistry
import org.starter.model.Ping
import org.starter.model.Pong

@OptIn(ExperimentalSerializationApi::class)
fun Application.configureRouting(registry: ComponentRegistry) {

    routing {
        staticResources("/", "web")

        post("/ping") {
            val ping = call.receive<Ping>()
            val pong = Pong(ping.message, ping.number)
            call.respond(pong)
        }
    }
}
