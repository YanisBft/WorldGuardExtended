plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.21"
}

group = "com.yanisbft.commandhookpaper"
version = "1.0.0"

repositories {
    gradlePluginPortal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    paperweight.paperDevBundle("26.1.2.build.+")

    compileOnly("com.sk89q.worldguard:worldguard-core:7.0.17")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.17")
}

paperweight {
    javaLauncher = javaToolchains.launcherFor {
        languageVersion = JavaLanguageVersion.of(25)
    }
}
