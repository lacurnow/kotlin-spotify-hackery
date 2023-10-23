package org.starter.services

import org.slf4j.LoggerFactory
import org.starter.model.Track
import org.starter.persistence.TrackDAO

class TrackService(private val trackDAO: TrackDAO) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun createTrack(track: Track): Track {
        val createdTrack = trackDAO.createTrack(track)
        logger.info("Created Track ${createdTrack}")
        return createdTrack
    }

    fun getTracks(): List<Track> {
        val tracks = trackDAO.getTracks()
        return tracks
    }
}