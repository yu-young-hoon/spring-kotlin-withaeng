package com.withaeng.api.controller.destination

import com.withaeng.api.common.ApiResponse
import com.withaeng.domain.destination.City
import com.withaeng.domain.destination.Continent
import com.withaeng.domain.destination.Country
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Destination", description = "여행지 API")
@RestController
@RequestMapping("/api/v1/destinations")
class DestinationController {

    @GetMapping
    fun getDestinations(): ApiResponse<GetDestinationsResponse> {
        return ApiResponse.success(
            getDestinationsTree()
        )
    }

    fun getDestinationsTree(): GetDestinationsResponse {
        val continents = Continent.entries.map { continent ->
            val countriesInContinent = Country.entries
                .filter { it.continentCode == continent.continentCode }
                .map { country ->
                    val citiesInCountry = City.entries
                        .filter { it.countryCode == country.countryCode }
                        .map { it.name }
                    CountryNode(name = country.name, cities = citiesInCountry)
                }
            ContinentNode(name = continent.name, countries = countriesInContinent)
        }
        return GetDestinationsResponse(continents = continents)
    }
}

data class GetDestinationsResponse(
    val continents: List<ContinentNode>,
)

data class ContinentNode(
    val name: String,
    val countries: List<CountryNode>,
)

data class CountryNode(
    val name: String,
    val cities: List<String>,
)