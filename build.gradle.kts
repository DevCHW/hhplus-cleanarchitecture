plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.jpa") version "1.9.25"
}

val springMockkVersion = "4.0.2"

group = "io.hhplus.cleanarchitecture"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // ## Production Dependencies
    // spring web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // spring data jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // spring validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // reflection
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // mysql
    runtimeOnly("com.mysql:mysql-connector-j")

    // ## Test Dependencies
    // spring boot test
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // junit
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // mockk
    testImplementation("com.ninja-squad:springmockk:${springMockkVersion}")

    // test containers
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mysql")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
