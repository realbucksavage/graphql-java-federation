import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

group = "io.gqljf"
// todo set up plugin
version = "0.1.16"

plugins {
    `java-library`
    idea
    id("com.google.protobuf")
}

val graphqlJavaVersion: String by project
val protobufVersion: String by project
val junitVersion: String by project
val hamcrestVersion: String by project

repositories {
    jcenter()
}

dependencies {
    implementation("com.graphql-java:graphql-java:$graphqlJavaVersion")
    implementation("com.google.protobuf:protobuf-java:$protobufVersion")

    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.hamcrest:hamcrest:$hamcrestVersion")
}


protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }
}

tasks {
    test {
        useJUnitPlatform()
    }

    jar {
        manifest {
            attributes(
                mapOf(
                    "Implementation-Title" to project.name,
                    "Implementation-Version" to project.version
                )
            )
        }
    }
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val projectUrl = "https://github.com/rkudryashov/graphql-java-federation.git"

idea {
    module {
        sourceDirs.add(file("${projectDir}/src/generated/main/java"))
    }
}
