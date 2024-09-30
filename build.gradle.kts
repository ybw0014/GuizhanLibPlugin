group = "net.guizhanss"
val mainPackage = "net.guizhanss.minecraft.guizhanlib"

plugins {
    java
    `java-library`
    `maven-publish`
    signing
    id("io.freefair.lombok") version "8.7.1"
    id("com.gradleup.shadow") version "8.3.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("co.uzzu.dotenv.gradle") version "4.0.0"
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io/")
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven(url = "https://papermc.io/repo/repository/maven-public")
    maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    fun compileOnlyAndTestImplementation(dependencyNotation: Any) {
        compileOnly(dependencyNotation)
        testImplementation(dependencyNotation)
    }

    implementation("net.guizhanss:guizhanlib-all:2.0.0-SNAPSHOT")
    implementation("org.bstats:bstats-bukkit:3.1.0")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("com.github.houbb:pinyin:0.4.0")

    compileOnlyAndTestImplementation("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    compileOnlyAndTestImplementation("com.github.Slimefun:Slimefun4:RC-37")

    // mockbukkit
    testImplementation("com.github.MockBukkit:MockBukkit:c7cc678834")
    // junit
    testImplementation(platform("org.junit:junit-bom:5.11.1"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
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
    archiveAppendix = ""
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            project.shadow.component(this)

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
                    url.set("https://github.com/ybw0014/guizhanlibplugin/tree/master")
                }
            }
        }
    }

    repositories {
        maven {
            name = "CentralRelease"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = env.OSSRH_USERNAME.orElse("")
                password = env.OSSRH_PASSWORD.orElse("")
            }
        }
        maven {
            name = "CentralSnapshot"
            url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            credentials {
                username = env.OSSRH_USERNAME.orElse("")
                password = env.OSSRH_PASSWORD.orElse("")
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(configurations.runtimeElements.get())
}

bukkit {
    main = "$mainPackage.GuizhanLib"
    apiVersion = "1.18"
    authors = listOf("ybw0014")
    description = "A library plugin for Simplified Chinese Slimefun addons."
    website = "https://github.com/ybw0014/GuizhanLibPlugin"
    depend = listOf("Slimefun")
}
