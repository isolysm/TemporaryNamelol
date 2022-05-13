import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import dev.architectury.pack200.java.Pack200Adapter
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("gg.essential.loom") version "0.10.0.2"
    id("dev.architectury.architectury-pack200") version "0.1.3"
    java
    idea
}

group = "your.mom"
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
    launchConfigs.named("client") {
        property("mixin.debug.export", "true")
        property("mixin.dumpTargetOnFailure", "true")
        arg("--tweakClass", "gg.essential.loader.stage0.EssentialSetupTweaker")
        arg("--mixin", "mixins.apple.json")
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

val shadowMe: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

val shadowMeMod: Configuration by configurations.creating {
    configurations.modImplementation.get().extendsFrom(this)
}


dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("de.oceanlabs.mcp:mcp_stable:22-1.8.9")
    forge("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9")

    shadowMe("gg.essential:loader-launchwrapper:1.1.3")
    shadowMe("gg.essential:essential-1.8.9-forge:2666") {
        exclude(module = "asm")
    }
    "com.github.LlamaLad7:MixinExtras:0.0.9".let {
        implementation(it)
        annotationProcessor(it)
    }
    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
    compileOnly("org.spongepowered:mixin:0.8.5")
}

tasks {
    "shadowJar"(ShadowJar::class) {
        configurations = listOf(shadowMe, shadowMeMod)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        relocate("com.llamalad7.mixinextras", "dev.myosyn.euphoria.mixinextras")

        relocate("gg.essential.vigilance", "dev.shuuyu.euphoria.vigilance")
        relocate("gg.essential.elementa", "dev.shuuyu.euphoria.elementa")
        relocate("gg.essential.universalcraft", "dev.shuuyu.euphoria.universalcraft")

        exclude(
            "**/LICENSE.md",
            "**/LICENSE.txt",
            "**/LICENSE",
            "**/NOTICE",
            "**/NOTICE.txt",
        )
    }

    "KotlinCompile"(KotlinCompile::class) {

    }
}
