package com.withaeng.api.controller.destination

import com.withaeng.api.applicationservice.accompany.dto.DestinationResponse
import com.withaeng.api.common.ApiResponse
import com.withaeng.domain.destination.City
import com.withaeng.domain.destination.Continent
import com.withaeng.domain.destination.Country
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Tag(name = "Destination", description = "여행지 API")
@RestController
@RequestMapping("/api/v1/destinations")
class DestinationController {

    @GetMapping
    fun getDestinations(): ApiResponse<DestinationResponse> {
        return ApiResponse.success(
            DestinationResponse(createDestinationsMap())
        )
    }

    private fun createDestinationsMap(): EnumMap<Continent, EnumMap<Country, List<City>>> {
        val structure = EnumMap<Continent, EnumMap<Country, List<City>>>(Continent::class.java)
        Continent.entries.forEach { continent ->
            val countriesInContinent = EnumMap<Country, List<City>>(Country::class.java)
            Country.entries
                .filter { it.continentCode == continent.continentCode }
                .forEach { country ->
                    val citiesInCountry = City.entries
                        .filter { it.countryCode == country.countryCode }
                        .toList()
                    countriesInContinent[country] = citiesInCountry
                }
            structure[continent] = countriesInContinent
        }
        return structure
    }
}