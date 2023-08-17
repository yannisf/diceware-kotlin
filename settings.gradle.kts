rootProject.name = "diceware"

plugins {
    id("com.gradle.enterprise") version("3.13.4")
}

include("core", "web", "protobuf")

gradleEnterprise {
    if (System.getenv("CI") != null) {
        buildScan {
            publishAlways()
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
        }
    }
}

