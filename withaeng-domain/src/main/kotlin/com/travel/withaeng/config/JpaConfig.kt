package com.travel.withaeng.config

import com.travel.withaeng.domain.WithaengDomainModule
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaAuditing
@Configuration
@EntityScan(basePackageClasses = [WithaengDomainModule::class])
@EnableJpaRepositories(basePackageClasses = [WithaengDomainModule::class])
class JpaConfig