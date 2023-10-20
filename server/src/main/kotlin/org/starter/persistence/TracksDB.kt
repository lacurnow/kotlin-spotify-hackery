package org.starter.persistence

import kotliquery.HikariCP
import org.flywaydb.core.Flyway
import org.slf4j.LoggerFactory

class TracksDB {
    companion object {

        private val logger = LoggerFactory.getLogger(Companion::class.java)

        const val name = "tracksdb"

        // TODO: Pull these from the test config
        private const val jdbcUrl = "jdbc:postgresql://localhost:5432/tracksdb"
        private const val username = "dba"
        private const val password = "stub-password"

        fun createBlankDatabase() {
            val flyway =
                Flyway
                    .configure()
                    .locations("db")
                    .createSchemas(true)
                    .cleanDisabled(false)
                    .dataSource(jdbcUrl, username, password)
                    .load()

            flyway.clean()
            flyway.migrate()
            initialiseDatabaseConnection()
        }

        private fun initialiseDatabaseConnection() {
            HikariCP.init(name, jdbcUrl, username, password)
        }
    }
}