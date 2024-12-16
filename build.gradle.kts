plugins {
	java
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("jvm") version  "2.0.21"
	//"1.8.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		var languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.boot:spring-boot-starter-security")
	implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:3.0.0")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation ("org.mockito:mockito-core")
	implementation ("com.h2database:h2:2.2.222")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("jakarta.persistence:jakarta.persistence-api:3.1.0")
    runtimeOnly ("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

}
tasks.withType<Test> {
	useJUnitPlatform()
}
