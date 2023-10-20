package org.starter.persistence

import org.starter.model.Track
import org.testcontainers.containers.DockerComposeContainer
import java.io.File
import kotlin.test.*

class TracksDAOTest {
    private val container = DockerComposeContainer(File("docker-compose.yml"))
        .withExposedService("postgres", 5432)

    private var trackDAO: TrackDAO? = null

    @BeforeTest
    fun beforeAll() {
        container.start()
        TracksDB.createBlankDatabase()
        trackDAO = TrackDAO("tracksdb")
    }

    @AfterTest
    fun afterAll() {
        container.stop()
    }

    @Test
    fun createTrackThenCreatesNewTrack() {
        // Given
        val track = Track(null, "track-name", "artist_name", 100)

        // When
        getTrackDAO().createTrack(track)

        // Then
        val Tracks = getTrackDAO().getTracks()
        assertEquals(1, Tracks.size)
        assertNotNull(Tracks[0].id)
        assertEquals(track.track_name, Tracks[0].track_name)
        assertEquals(track.artist_name, Tracks[0].artist_name)
        assertEquals(track.streams, Tracks[0].streams)
    }

    @Test
    fun getTracksThenReturnsTracks() {
        // Given
        getTrackDAO().createTrack(Track(null, "Track-1", "Artist-1", 2))
        getTrackDAO().createTrack(Track(null, "Track-2", "Artist-2", 10))
        getTrackDAO().createTrack(Track(null, "Track-3", "Artist-3", 100))

        // When
        val Tracks = getTrackDAO().getTracks()

        // Then
        assertEquals(3, Tracks.size)
        val actualTrackNames = Tracks.map { it.track_name }
        assertContentEquals(listOf("Track-1", "Track-2", "Track-3"), actualTrackNames)
    }

    private fun getTrackDAO(): TrackDAO {
        return trackDAO!!
    }
}