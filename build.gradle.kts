import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import dev.architectury.pack200.java.Pack200Adapter
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    kotlin("plugin.serialization") version "1.6.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("gg.essential.loom") version "0.10.0.2"
    id("dev.architectury.architectury-pack200") version "0.1.3"
    java
}

group = "dev.myosyn"
version = "6.9.4.2.0"
description = "Think Different - Said someone idk"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.essential.gg/repository/maven-public")
    maven("https://repo.sk1er.club/repository/maven-public/")
    maven("https://repo.sk1er.club/repository/maven-releases/")
    maven("https://jitpack.io")
}

loom {
    launchConfigs {
        getByName("client") {
            arg("--tweakClass", "apple.myosyn.tweaker.AppleTweaker")
            arg("--mixin", "mixins.apple.json")
        }
    }
    runConfigs {
        getByName("client") {
            ideConfigGenerated(true)
        }
    }
    forge {
        pack200Provider.set(Pack200Adapter())
        mixinConfig("mixins.apple.json")
        mixin.defaultRefmapName.set("mixins.apple.refmap.json")
    }
}

dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("de.oceanlabs.mcp:mcp_stable:22-1.8.9")
    forge("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9")

    implementation("gg.essential:essential-1.8.9-forge:2666")
    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
    compileOnly("org.spongepowered:mixin:0.8.5")
}

tasks {
    withType<Jar> {
        manifest {
            attributes (
                mapOf(
                    "MixinConfigs" to "mixins.apple.json"
                )
                    )
        }
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs =
                listOf(
                    "-opt-in=kotlin.RequiresOptIn",
                    ""

                )
        }
    }
}
