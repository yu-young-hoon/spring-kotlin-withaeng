allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

val jasyptVersion: String by project.extra
val mysqlVersion: String by project.extra
val queryDslVersion: String by project.extra
dependencies {
    implementation(project(":withaeng-common"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Query DSL
    implementation("com.querydsl:querydsl-jpa:$queryDslVersion")
    kapt("com.querydsl:querydsl-apt:$queryDslVersion")

    kapt("org.springframework.boot:spring-boot-configuration-processor")
    runtimeOnly("com.mysql:mysql-connector-j:$mysqlVersion")

    // jasypt
    api("com.github.ulisesbocchio:jasypt-spring-boot-starter:$jasyptVersion")
}