package com.withaeng.api.controller

import ch.qos.logback.core.model.Model
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping("/")
    fun health(model: Model): String {
        return "home"
    }

    @GetMapping("/home")
    fun home(model: Model): String {
        return "home"
    }

    @GetMapping("/instagram")
    fun instagram(model: Model): String {
        return "instagram"
    }

    @GetMapping("/auth")
    fun auth(model: Model): String {
        return "auth"
    }
}