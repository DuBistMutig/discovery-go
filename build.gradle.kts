plugins {
    java
    alias(libs.plugins.lavalink)
    id("com.github.ben-manes.versions") version "0.53.0"
}

group = "com.dubistmutig.discovery"
version = "0.1.0"

lavalinkPlugin {
    name = "discovery-go"
    apiVersion = libs.versions.lavalink.api
    serverVersion = libs.versions.lavalink.server
}

sourceSets {
    main {
        java {
            srcDirs("src/main/java")
        }
        resources {
            srcDirs("src/main/resources")
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
    jar {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
    processResources {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}

dependencies {
    implementation(libs.lavaplayer)
    implementation(libs.httpclient5)
    implementation(libs.httpcore5)
}
