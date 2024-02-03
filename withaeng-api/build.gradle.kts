tasks.bootJar {
    enabled = true
}

tasks.jar {
    enabled = false
}

dependencies {
    implementation(project(":withaeng-domain"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}