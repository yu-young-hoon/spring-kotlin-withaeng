package com.travel.withaeng.domain.destination

import com.travel.withaeng.domain.accompany.City
import com.travel.withaeng.domain.accompany.Continent
import com.travel.withaeng.domain.accompany.Country
import org.springframework.stereotype.Service

@Service
class DestinationService {

    fun getDestinationList(): DestinationDto {

        val continentList = mutableListOf<Continent>()

        for (continentCd in Continent.entries) {

            // Create a continent object
            val continent = Continent(continentCd.continentCode, continentCd.continentName)
            val countryList = mutableListOf<Country>()

            for (countryCd in Country.entries) {
                if (continentCd.continentCode.equals(countryCd.continentCode)) {
                    val country = Country(countryCd.countryCode, countryCd.countryName)

                    val cityList = mutableListOf<City>()
                    for (cityCd in City.entries) {
                        if (countryCd.countryCode.equals(cityCd.countryCode)) {
                            val city = City(cityCd.cityCode, cityCd.cityName)
                            cityList.add(city)
                        }
                    }
                    country.cityList = cityList
                    countryList.add(country)
                }
            }

            continent.countryList = countryList
            // Add the continent to the list
            continentList.add(continent)
        }

        // Create and return DestinationDto
        return DestinationDto(continentList)
    }
}