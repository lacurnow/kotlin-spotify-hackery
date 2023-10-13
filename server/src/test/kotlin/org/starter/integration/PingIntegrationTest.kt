package org.starter.integration

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.starter.model.Ping
import org.starter.model.Pong
import org.starter.testcommon.IntegrationTestBase
import kotlin.test.Test
import kotlin.test.assertEquals

class PingIntegrationTest : IntegrationTestBase() {

    @Test
    fun pingThenReturnsPongResponse() {
        testApplication {

            val client = httpClient()

            val ping = Ping("message", 23)

            val pingResponse = client.post("/ping") {
                contentType(ContentType.Application.Json)
                setBody(ping)
            }
            assertEquals(HttpStatusCode.OK, pingResponse.status)

            val actual: Pong = pingResponse.body()
            val expected = Pong(ping.message, ping.number)
            assertEquals(expected, actual)
        }
    }
}