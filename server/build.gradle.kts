val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.serialization") version "1.8.20"
    id("io.ktor.plugin") version "2.3.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.jetbrains.kotlinx.kover") version "0.7.3"
}

group = "starter"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {

    // Ktor - Core
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml:$ktor_version")
    implementation("io.ktor:ktor-server-default-headers-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-cors-jvm:$ktor_version")

    // Ktor - Content
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("de.brudaswen.kotlinx.serialization:kotlinx-serialization-csv:2.0.0")

    // Persistence
    implementation("com.github.seratch:kotliquery:1.1.5")
    implementation("com.zaxxer:HikariCP:2.7.6")
    implementation("org.flywaydb:flyway-core:9.20.0")
    implementation("org.postgresql:postgresql:42.6.0")

    // Config
    implementation("io.github.config4k:config4k:0.6.0")

    // General utilities
    implementation("org.apache.commons:commons-text:1.3")

    // Testing
    testImplementation("org.flywaydb:flyway-core:9.20.0")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation("io.rest-assured:rest-assured:3.0.7")
    testImplementation("com.willowtreeapps.assertk:assertk:0.9")
    testImplementation("io.mockk:mockk:1.13.7")
    testImplementation("org.testcontainers:postgresql:1.19.1")
}
