package com.travel.withaeng.config

import com.travel.withaeng.scheduler.SchedulerBasePackage
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
@ComponentScan(basePackageClasses = [SchedulerBasePackage::class])
class SchedulerConfig