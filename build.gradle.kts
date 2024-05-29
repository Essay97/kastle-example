plugins {
    kotlin("jvm") version "1.9.23"
}

group = "io.github.essay97.kastle.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.github.essay97:kastle-lib:0.0.2")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}