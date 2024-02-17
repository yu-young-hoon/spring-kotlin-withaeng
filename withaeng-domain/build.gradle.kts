allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

val jasyptVersion: String by project.extra
val mysqlVersion: String by project.extra
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j:$mysqlVersion")
    // jasypt
    api("com.github.ulisesbocchio:jasypt-spring-boot-starter:$jasyptVersion")
}