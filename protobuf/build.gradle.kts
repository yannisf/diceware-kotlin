import com.google.protobuf.gradle.*

plugins {
    id("com.google.protobuf") version "0.9.4"
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")
    implementation("io.grpc:grpc-services:1.57.2")
    implementation("com.google.protobuf:protobuf-kotlin:3.24.0")
    runtimeOnly("io.grpc:grpc-netty:1.57.2")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.24.0"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.57.2"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}
