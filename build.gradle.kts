plugins {
    `java-platform` //Plugin used to build Maven BOM
    `maven-publish` //Plugin used to publish Maven BOM
}

group = "com.paxleones"
version = "1.0-SNAPSHOT"

//Allows <dependencies> block in Maven BOM
javaPlatform {
    allowDependencies()
}

dependencies {
    //Properties from project gradle.properties to variable mapping
    val assertJVersion: String by project
    val cukedoctorVersion: String by project
    val cucumberVersion: String by project
    val jacksonVersion: String by project
    val jaxbApiVersion: String by project
    val junitJupiterVersion: String by project
    val h2Version: String by project
    val hibernateCoreVersion: String by project
    val liquibaseCoreVersion: String by project
    val mockitoVersion: String by project
    val postgresqlVersion: String by project
    val springVersion: String by project
    val springBootVersion: String by project
    val springfoxSwaggerVersion: String by project

    //Maven BOM <dependencyManagement> block
    constraints {
        api("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
        api("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")
        api("com.github.cukedoctor:cukedoctor-converter:$cukedoctorVersion")
        api("javax.xml.bind:jaxb-api:$jaxbApiVersion")
        api("io.springfox:springfox-boot-starter:$springfoxSwaggerVersion")
        api("org.hibernate:hibernate-core:$hibernateCoreVersion")
        api("org.liquibase:liquibase-core:$liquibaseCoreVersion")
        api("org.springframework.boot:spring-boot-starter-jdbc:$springBootVersion")
        api("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
        api("org.springframework.boot:spring-boot-starter-web:$springBootVersion")

        runtime("org.postgresql:postgresql:$postgresqlVersion")
        runtime("org.springframework.boot:spring-boot-devtools:$springBootVersion")

        api("com.h2database:h2:$h2Version")
        api("io.cucumber:cucumber-junit-platform-engine:$cucumberVersion")
        api("io.cucumber:cucumber-java:$cucumberVersion")
        api("org.assertj:assertj-core:$assertJVersion")
        api("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
        api("org.mockito:mockito-junit-jupiter:$mockitoVersion")
        api("org.mockito:mockito-core:$mockitoVersion")
        api("org.springframework:spring-test:$springVersion")
        api("org.springframework.boot:spring-boot-test:$springBootVersion")
        api("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    }

    //Maven BOM <dependencies> block
    //api(platform("com.fasterxml.jackson:jackson-bom:2.9.8"))
    //api("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonModuleVersion")

}

//Publishing to Maven repository
publishing {
    val mavenUser: String by project
    val mavenPassword: String by project
    val snapshotsRepoUrl: String by project
    val releasesRepoUrl: String by project

    repositories {
        maven {
            url = if (version.toString().endsWith("SNAPSHOT")) uri(snapshotsRepoUrl) else uri(releasesRepoUrl)
            credentials {
                username = mavenUser
                password = mavenPassword
            }
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            from(components["javaPlatform"])
        }
    }
}


