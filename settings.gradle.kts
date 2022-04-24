pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://files.minecraftforge.net/maven")
        maven("https://maven.architectury.dev/")
        maven("https://maven.fabricmc.net")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://repo.essential.gg/repository/maven-public")
        maven("https://jitpack.io")
    }
    resolutionStrategy {
        eachPlugin {
            when(requested.id.id) {
                "net.minecraftforge.gradle.forge" -> {
                    useModule("com.github.ReplayMod:ForgeGradle:${requested.version}")
                }
            }
        }
    }
}

rootProject.name = "Apple"
