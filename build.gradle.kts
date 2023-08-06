import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.gradle.java")
    kotlin("jvm") version "1.7.22"
    id("org.jetbrains.kotlinx.kover") version "0.7.3" apply false
    id("org.springframework.boot") version "3.0.7" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false
    kotlin("plugin.spring") version "1.7.22" apply false
}

subprojects {
    group = "eu.frlab"
    version = "0.0.1-SNAPSHOT"

    apply {
        plugin("java")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlinx.kover")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events ("passed", "skipped", "failed")
        }
    }

}
