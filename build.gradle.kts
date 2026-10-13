plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks.register<Copy>("copyJarToPlugins"){
    from(tasks.named("jar"))
    into(layout.projectDirectory.dir("run/plugins"))
}

tasks.named("build"){
    finalizedBy("copyJarToPlugins")
}