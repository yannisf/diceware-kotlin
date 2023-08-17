import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
}

dependencies {

//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
//    implementation("io.grpc:grpc-kotlin-stub:1.3.0")
//    implementation("io.grpc:grpc-services:1.57.2")
//    implementation("com.google.protobuf:protobuf-kotlin:3.24.0")
    runtimeOnly("io.grpc:grpc-netty:1.57.2")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation(project(":protobuf"))
    implementation(project(":core"))
    kover(project(":core"))

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
}

tasks.named<BootJar>("bootJar") {
    archiveFileName.set("app.jar")
}

tasks.register("kover") {
    dependsOn("koverHtmlReport", "koverXmlReport")
}
