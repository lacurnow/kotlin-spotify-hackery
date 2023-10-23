package org.starter.persistence

import kotliquery.HikariCP
import kotliquery.LoanPattern.using
import kotliquery.Row
import kotliquery.queryOf
import kotliquery.sessionOf
import org.slf4j.LoggerFactory
import org.starter.model.Track
import java.util.*

class TrackDAO(private val name: String) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    private var tracks: List<Track> = listOf()

    fun createTrack(track: Track): Track {
        if (track.id != null) {
            throw IllegalArgumentException("Attempting to create a track which already has an id $track")
        }

        val id = UUID.randomUUID().toString()

        return using(sessionOf(HikariCP.dataSource(name))) { session ->
            val sql = """
                INSERT INTO $name
                  (id, track_name, artist_name, streams)
                VALUES
                  (?, ?, ?, ?)
            """.trimIndent()

            val (_, track_name, artist_name, streams) = track

            session.run(queryOf(sql, id, track_name, artist_name, streams).asUpdate)

            logger.info("Created track $track with ID $id")

            track.copy(id = id)
        }
    }

    fun getTracks(): List<Track> {
        return using(sessionOf(HikariCP.dataSource(name))) { session ->
            val sql = """
                SELECT
                    *
                FROM $name
            """.trimIndent()

            session.run(queryOf(sql).map { extractTrack(it) }.asList)
        }
    }

    private fun extractTrack(row: Row): Track {
        return Track(
            row.string("id"),
            row.string("track_name"),
            row.string("artist_name"),
            row.int("streams")
        )
    }
}