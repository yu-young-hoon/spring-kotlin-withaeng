package com.withaeng.domain.config

import com.withaeng.domain.WithaengDomainModule
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaAuditing
@Configuration
@EntityScan(basePackageClasses = [WithaengDomainModule::class])
@EnableJpaRepositories(basePackageClasses = [WithaengDomainModule::class])
class JpaConfig