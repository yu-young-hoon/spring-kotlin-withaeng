package com.withaeng.api.config

import com.withaeng.api.scheduler.SchedulerBasePackage
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
@ComponentScan(basePackageClasses = [SchedulerBasePackage::class])
class SchedulerConfig