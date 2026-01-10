group = "net.guizhanss"
val mainPackage = "net.guizhanss.minecraft.guizhanlib"

plugins {
    java
    `java-library`
    `maven-publish`
    signing
    id("io.freefair.lombok") version "8.13.1"
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
    id("com.gradleup.shadow") version "9.3.0"
    id("de.eldoria.plugin-yml.bukkit") version "0.8.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://s01.oss.sonatype.org/content/groups/public/")
}

dependencies {
    fun compileOnlyAndTestImplementation(dependencyNotation: Any) {
        compileOnly(dependencyNotation)
        testImplementation(dependencyNotation)
    }

    implementation("net.guizhanss:guizhanlib-all:2.5.0")
    implementation("org.bstats:bstats-bukkit:3.1.0")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    api("com.github.houbb:pinyin:0.4.0")

    compileOnlyAndTestImplementation("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    compileOnlyAndTestImplementation("com.github.Slimefun:Slimefun4:RC-37")

    // mockbukkit
    testImplementation("com.github.MockBukkit:MockBukkit:v1.20-SNAPSHOT")
    // junit
    testImplementation(platform("org.junit:junit-bom:5.12.1"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    disableAutoTargetJvm()
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withJavadocJar()
    withSourcesJar()
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.javadoc {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    fun doRelocate(from: String, to: String? = null) {
        val last = to ?: from.split(".").last()
        relocate(from, "$mainPackage.libs.$last")
    }

    doRelocate("io.papermc.lib", "paperlib")
    doRelocate("com.github.houbb")
    doRelocate("org.bstats")
    archiveClassifier = ""
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["shadow"])

            artifact(tasks.named("javadocJar").get())
            artifact(tasks.named("sourcesJar").get())

            groupId = rootProject.group as String
            artifactId = project.name
            version = rootProject.version as String

            pom {
                name.set("GuizhanLibPlugin")
                description.set("A library plugin for Slimefun addon development.")
                url.set("https://github.com/ybw0014/GuizhanLibPlugin")

                licenses {
                    license {
                        name.set("GPL-3.0 license")
                        url.set("https://github.com/ybw0014/GuizhanLibPlugin/blob/master/LICENSE")
                        distribution.set("repo")
                    }
                }

                developers {
                    developer {
                        name.set("ybw0014")
                        url.set("https://ybw0014.dev/")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/ybw0014/GuizhanLibPlugin.git")
                    developerConnection.set("scm:git:ssh://github.com:ybw0014/GuizhanLibPlugin.git")
                    url.set("https://github.com/ybw0014/GuizhanLibPlugin/tree/master")
                }
            }
        }
    }
}

signing {
    // no need to sign when building
    if (!version.toString().startsWith("Build")) {
        sign(publishing.publications["maven"])
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

bukkit {
    main = "$mainPackage.GuizhanLib"
    apiVersion = "1.18"
    authors = listOf("ybw0014")
    description = "A library plugin for Simplified Chinese Slimefun addons."
    website = "https://github.com/ybw0014/GuizhanLibPlugin"
    depend = listOf("Slimefun")
}

tasks.runServer {
    downloadPlugins {
        // Slimefun
        url("https://builds.guizhanss.com/api/download/SlimefunGuguProject/Slimefun4/master/latest")
        // GuizhanCraft for testing convenient
        url("https://builds.guizhanss.com/api/download/ybw0014/GuizhanCraft/master/latest")
    }
    jvmArgs("-Dcom.mojang.eula.agree=true")
    minecraftVersion("1.21.10")
}
