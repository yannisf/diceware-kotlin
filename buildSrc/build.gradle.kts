plugins {
    // Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main' that automatically become available as plugins in the main build.
    `kotlin-dsl`
}

repositories {
    // Use the plugin portal to apply community plugins in convention plugins.
    gradlePluginPortal()
}

//Dependencies here, are plugins for the underlying projects???
dependencies {
    implementation(libs.kotlinGradlePlugin) //org.jetbrains.kotlin.jvm
    implementation("io.spring.gradle:dependency-management-plugin:1.1.0") //io.spring.dependency-management
    implementation(libs.springBootGradlePlugin) //org.springframework.boot
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.8.21") //org.jetbrains.kotlin.plugin.spring
}
