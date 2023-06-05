plugins {
    java
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok-mapstruct-binding:0.1.0")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.assertj:assertj-core:3.21.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
