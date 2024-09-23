package com.withaeng.api.controller.destination

import com.withaeng.api.applicationservice.accompany.dto.DestinationResponse
import com.withaeng.api.applicationservice.destination.DestinationApplicationService
import com.withaeng.api.common.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Destination", description = "여행지 API")
@RestController
@RequestMapping("/api/v1/destinations")
class DestinationController(private val destinationApplicationService: DestinationApplicationService) {

    @GetMapping
    fun getDestinations(): ApiResponse<DestinationResponse> {
        return ApiResponse.success(
            destinationApplicationService.getDestinations()
        )
    }
}