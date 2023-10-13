package org.starter

import io.ktor.server.application.*
import org.starter.apps.ComponentRegistry
import org.starter.plugins.configureContentNegotation
import org.starter.plugins.configureHTTP
import org.starter.plugins.configureRouting
import org.starter.utils.ConfigUtils.loadConfig

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.yaml references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val appEnvironment = environment.config.propertyOrNull("app.environment")?.getString()

    val appConfig = loadConfig(appEnvironment)
    val componentRegistry = ComponentRegistry(appConfig)

    configureHTTP()
    configureRouting(componentRegistry)
    configureContentNegotation()
}
