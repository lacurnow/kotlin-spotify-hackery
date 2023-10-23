package org.starter.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.ExperimentalSerializationApi
import org.starter.model.Ping
import org.starter.model.Pong
import org.starter.model.Track
import org.starter.services.TrackService

@OptIn(ExperimentalSerializationApi::class)
fun Application.configureRouting(trackService: TrackService) {

    routing {
        staticResources("/", "web")

        post("/ping") {
            val ping = call.receive<Ping>()
            val pong = Pong(ping.message, ping.number)
            call.respond(pong)
        }

        post("/tracks") {
            val track = call.receive<Track>()
            val createdTrack = trackService.createTrack(track)
            call.response.status(HttpStatusCode.Created)
            call.respond(createdTrack)
        }

        get("/tasks") {
            val tracks = trackService.getTracks()
            call.respond(tracks)
        }
    }
}
