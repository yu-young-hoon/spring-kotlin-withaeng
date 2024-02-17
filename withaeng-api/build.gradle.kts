tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":withaeng-domain"))
    implementation(project(":withaeng-common"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}