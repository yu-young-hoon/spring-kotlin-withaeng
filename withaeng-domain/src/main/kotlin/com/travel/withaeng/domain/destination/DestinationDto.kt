package com.travel.withaeng.domain.destination


class DestinationDto(

    var continentList: List<Continent>
)

class Continent(

    var continentCd: String,
    var continentNm: String,
    var countryList: List<Country>

) {
    constructor(continentCd: String, continentNm: String) :
            this(continentCd, continentNm, mutableListOf())

}

class Country(

    var countryCd: String,
    var countryNm: String,
    var cityList: List<City>
) {

    constructor(countryCd: String, countryNm: String) :
            this(countryCd, countryNm, mutableListOf())


}

class City(

    var cityCd: String,
    var cityNm: String,
)
