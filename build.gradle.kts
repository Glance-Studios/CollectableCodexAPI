plugins {
    id("io.freefair.lombok") version "8.11"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.18"
    `maven-publish`
    `java-library`
    java
}

group = "com.glance.codex"
version = "1.0.1"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    paperweight.paperDevBundle("1.21.5-R0.1-SNAPSHOT")
    compileOnly("io.papermc.paper:paper-api:1.21.5-R0.1-SNAPSHOT")
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
        create<MavenPublication>("gpr") {
            from(components["java"])
            artifactId = "codex-api"
            pom {
                name.set(project.name)
                description.set("Collections/Collectables API for Paper 1.19.4+")
            }
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Glance-Studios/CollectableCodexAPI")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GPR_USER")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GPR_TOKEN")
            }
        }
    }
}
