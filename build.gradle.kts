plugins {
    id("io.freefair.lombok") version "8.11"
    `maven-publish`
    `java-library`
    java
}

group = "com.glance.codex"
version = "1.1.1"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:26.0.2-1")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

// ---- GitHub Packages publishing ----
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = "com.glance.codex"
            artifactId = "codex-api"
            version = project.version.toString()

            pom {
                name.set("Codex API")
                description.set("Collectables/Collections API for Paper servers")
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Glance-Studios/CollectableCodexAPI")
            credentials {
                username = project.findProperty("gpr.user") as String?
                    ?: System.getenv("GPR_USER")
                password = project.findProperty("gpr.key") as String?
                    ?: System.getenv("GPR_TOKEN")
            }
        }
    }
}
