package com.withaeng.api.applicationservice.destination

import com.withaeng.api.applicationservice.accompany.dto.DestinationResponse
import com.withaeng.domain.destination.City
import com.withaeng.domain.destination.Continent
import com.withaeng.domain.destination.Country
import org.springframework.stereotype.Service
import java.util.*

@Service
class DestinationApplicationService {

    fun getDestinations(): DestinationResponse {
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
        return DestinationResponse(structure)
    }
}