
plugins {
    java
    id("com.gradleup.shadow") version "8.3.0"
}

group = "net.survivalboom.abstractbukkiteventannotations"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation("io.github.classgraph:classgraph:4.8.179")
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
}

tasks {

    compileJava {

        options.compilerArgs.addAll(arrayOf(
            "--add-exports", "jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED",
            "--add-exports", "jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED",
            "--add-exports", "jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED",
            "--add-exports", "jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED",
        ))

    }

    shadowJar {

        destinationDirectory.set(rootDir)

        relocate("io", "net.survivalboom.abstractbukkiteventannotations.libs")
        relocate("nonapi", "net.survivalboom.abstractbukkiteventannotations.libs")

    }

    build {
        dependsOn(shadowJar)
    }

}