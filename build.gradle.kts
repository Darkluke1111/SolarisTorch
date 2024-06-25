import dev.s7a.gradle.minecraft.server.tasks.LaunchMinecraftServerTask
import dev.s7a.gradle.minecraft.server.tasks.LaunchMinecraftServerTask.JarUrl

plugins {
    java
    id("dev.s7a.gradle.minecraft.server") version "3.1.0"
}

repositories {
    mavenCentral()
    //maven {
    //    name = "papermc-repo"
    //    url = "https://repo.papermc.io/repository/maven-public/"
    //}
    maven (url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven (url = "https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    //compileOnly "io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT"
    compileOnly("org.spigotmc:spigot-api:1.21-R0.1-SNAPSHOT")
}

val archivesBaseName =  "solaris_torch"

val javaVersion = JavaVersion.VERSION_17

        java {
            sourceCompatibility = javaVersion
            targetCompatibility = javaVersion
            toolchain.languageVersion = JavaLanguageVersion.of(javaVersion.toString())
        }

task<LaunchMinecraftServerTask>("testPlugin") {
    dependsOn("build")

    doFirst {
        copy {
            from(buildDir.resolve("libs/${project.name}.jar"))
            //into(buildDir.resolve("MinecraftServer/plugins"))
            into(projectDir.resolve("MinecraftServer/plugins"))
        }
    }

    jarUrl.set(JarUrl.Paper("1.21"))
    //jarUrl.set(JarUrl.LocalFile(projectDir.resolve("MinecraftServer/spigot-1.21.jar")))
    serverDirectory.set(projectDir.resolve("MinecraftServer").toString())
    agreeEula.set(true)
}