package com.withaeng.domain.destination

enum class Continent(
    val continentCode: String,
    val continentName: String,
) {

    EAST_ASIA("EA", "동아시아"),
    SOUTHEAST_ASIA("SA", "동남아시아"),
    CENTRAL_ASIA("CA", "중앙아시아"),
    WESTERN_ASIA("WA", "서남아시아"),
    EUROPE("EU", "유럽"),
    OCEANIA("OC", "오세아니아"),
    AFRICA("AF", "아프리카"),
    NORTH_AMERICA("NA", "북아메리카"),
    SOUTH_AMERICA("SA", "남아메리카")

}