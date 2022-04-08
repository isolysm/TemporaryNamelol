pluginManagement {
    repositories {
        maven("http://files.minecraftforge.net/maven")
        maven("https://maven.architectury.dev/")
        maven("https://maven.fabricmc.net")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://jitpack.io")
    }
    resolutionStrategy {
        eachPlugin {
            when(requested.id.id == "net.minecraftforge.gradle.forge") {
                useModule("com.github.ReplayMod:ForgeGradle:${requested.version}")
            }
        }
    }
}

rootProject.name = "Apple"

gradle.settingsEvaluated{
    // We have to use Java 11 for this because apparently I can't make it default to Java 18
    if(!JavaVersion.current().isJava11Compatible) {
        throw GradleException("Fuck you. Absoute troglodyte. Just go to the releases you idiot. Stop building this file if you aren't insane. Are you seriously still here? Honestly, I wasn't going to make this mod, but I realized that people still played 1.8.9, and they still used BetterFPS, and that really affected their FPS, so this is going to really affect your fps, by doing absolutely nothing. Yeah, that's right, it'll do nothing, and it bundles Essential, which isn't spyware. If you think that Essential is spyware, you're probably not that bright, and think that you're smart, but in reality, you're probably getting C's at school. If you feel offended by what I just said, cry about it. We also bundle something called get smarter, and when I say that, I mean grow some braincells, and grow up, go outside, and do some excercises. This is such a long error message that I want to sleep and get a hobby, but I can't because I have to force you to do something good, but no, you have to stay inside and play MInecraft all day long, and this forces me to make this mod. I hate you. Okay fine, I'll tell you how to compile this mod. Gradle is Psuedo to make you think that this mod is compiled this way. So what you do is go to the command prompt, and type in iamstoopid, which is the name of the compiler. once you type in iamstoopid, give yourself the middle finger, because this was a joke. Have fun reading the rest of this because I'm out. ")
    }
}

fun getBuildJavaHome(){
    System.getProperty("java.home")
}