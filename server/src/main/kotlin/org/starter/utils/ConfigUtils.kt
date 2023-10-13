package org.starter.utils

import com.typesafe.config.ConfigFactory
import io.github.config4k.extract
import org.starter.apps.AppConfig
import org.slf4j.LoggerFactory

object ConfigUtils {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun loadConfig(configName: String? = null): AppConfig {
        val config = ConfigFactory.load("starter-application.conf")

        return when (configName) {
            null -> {
                logger.info("Using default app config")
                config.extract("appConfig")
            }
            else -> {
                logger.info("Using ${configName} app config")
                config.getConfig(configName).extract("appConfig")
            }
        }
    }

}
