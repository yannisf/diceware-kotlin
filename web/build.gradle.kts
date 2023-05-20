import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("diceware.kotlin-conventions")
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.8.21"
	kotlin("plugin.spring") version "1.8.21"
}

group = "eu.frlab"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
	implementation ("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.boot:spring-boot-starter-actuator")
	implementation ("org.springframework.boot:spring-boot-starter-validation")
	implementation ("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation ("org.jetbrains.kotlin:kotlin-reflect")
	implementation (project(":core"))

	developmentOnly ("org.springframework.boot:spring-boot-devtools")

	annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor")

	testImplementation ("org.mockito.kotlin:mockito-kotlin:4.1.0")
	testImplementation ("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
