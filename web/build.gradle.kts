plugins {
    id("diceware.kotlin-conventions")
}

dependencies {
    implementation(libs.springBootStarterWeb)
    implementation(libs.springBootStarterActuator)
    implementation(project(":core"))
}

