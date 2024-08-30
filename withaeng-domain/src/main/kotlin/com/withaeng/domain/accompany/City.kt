package com.withaeng.domain.accompany

enum class City(
    val countryCode: String,
    val cityCode: String,
    val cityName: String
) {

    SEOUL(Country.KOREA.countryCode, "SEOUL", "서울"),
    BUSAN(Country.KOREA.countryCode, "BUSAN", "부산"),
    INCHEON(Country.KOREA.countryCode, "INCHEON", "인천"),
    DAEGU(Country.KOREA.countryCode, "DAEGU", "대구"),
    GWANGJU(Country.KOREA.countryCode, "GWANGJU", "광주"),
    DAEJEON(Country.KOREA.countryCode, "DAEJEON", "대전"),
    ULSAN(Country.KOREA.countryCode, "ULSAN", "울산"),
    SUWON(Country.KOREA.countryCode, "SUWON", "수원"),
    CHANGWON(Country.KOREA.countryCode, "CHANGWON", "창원"),
    JEJU(Country.KOREA.countryCode, "JEJU", "제주"),

    // Japan
    TOKYO(Country.JAPAN.countryCode, "TOKYO", "도쿄"),
    OSAKA(Country.JAPAN.countryCode, "OSAKA", "오사카"),
    KYOTO(Country.JAPAN.countryCode, "KYOTO", "교토"),
    SAPPORO(Country.JAPAN.countryCode, "SAPPORO", "삿포로"),
    FUKUOKA(Country.JAPAN.countryCode, "FUKUOKA", "후쿠오카"),
    HIROSHIMA(Country.JAPAN.countryCode, "HIROSHIMA", "히로시마"),
    NAGOYA(Country.JAPAN.countryCode, "NAGOYA", "나고야"),
    OKINAWA(Country.JAPAN.countryCode, "OKINAWA", "오키나와"),
    KANAZAWA(Country.JAPAN.countryCode, "KANAZAWA", "가나자와"),

    // China
    BEIJING(Country.CHINA.countryCode, "BEIJING", "베이징"),
    SHANGHAI(Country.CHINA.countryCode, "SHANGHAI", "상하이"),
    HONG_KONG(Country.CHINA.countryCode, "HONG_KONG", "홍콩"),
    MACAO(Country.CHINA.countryCode, "MACAO", "마카오"),
    GUANGZHOU(Country.CHINA.countryCode, "GUANGZHOU", "광저우"),
    SHENZHEN(Country.CHINA.countryCode, "SHENZHEN", "선전"),
    CHENGDU(Country.CHINA.countryCode, "CHENGDU", "청두"),
    XIAMEN(Country.CHINA.countryCode, "XIAMEN", "샤먼"),
    TIANJIN(Country.CHINA.countryCode, "TIANJIN", "천진"),

    // United States
    NEW_YORK(Country.UNITED_STATES.countryCode, "NEW_YORK", "뉴욕"),
    LOS_ANGELES(Country.UNITED_STATES.countryCode, "LOS_ANGELES", "로스앤젤레스"),
    CHICAGO(Country.UNITED_STATES.countryCode, "CHICAGO", "시카고"),
    LAS_VEGAS(Country.UNITED_STATES.countryCode, "LAS_VEGAS", "라스베이거스"),
    SAN_FRANCISCO(Country.UNITED_STATES.countryCode, "SAN_FRANCISCO", "샌프란시스코"),
    WASHINGTON_DC(Country.UNITED_STATES.countryCode, "WASHINGTON_DC", "워싱턴 D.C."),
    MIAMI(Country.UNITED_STATES.countryCode, "MIAMI", "마이애미"),
    ORLANDO(Country.UNITED_STATES.countryCode, "ORLANDO", "올랜도"),
    SEATTLE(Country.UNITED_STATES.countryCode, "SEATTLE", "시애틀"),

    // United Kingdom
    LONDON(Country.ENGLAND.countryCode, "LONDON", "런던"),
    EDINBURGH(Country.ENGLAND.countryCode, "EDINBURGH", "에든버러"),
    MANCHESTER(Country.ENGLAND.countryCode, "MANCHESTER", "맨체스터"),
    BIRMINGHAM(Country.ENGLAND.countryCode, "BIRMINGHAM", "버밍엄"),
    GLASGOW(Country.ENGLAND.countryCode, "GLASGOW", "글래스고"),
    LIVERPOOL(Country.ENGLAND.countryCode, "LIVERPOOL", "리버풀"),
    CAMBRIDGE(Country.ENGLAND.countryCode, "CAMBRIDGE", "케임브리지"),

    // France
    PARIS(Country.FRANCE.countryCode, "PARIS", "파리"),
    MARSEILLE(Country.FRANCE.countryCode, "MARSEILLE", "마르세유"),
    LYON(Country.FRANCE.countryCode, "LYON", "리옹"),
    NICE(Country.FRANCE.countryCode, "NICE", "니스"),
    BORDEAUX(Country.FRANCE.countryCode, "BORDEAUX", "보르도"),
    STRASBOURG(Country.FRANCE.countryCode, "STRASBOURG", "스트라스부르"),

    // Germany
    BERLIN(Country.GERMANY.countryCode, "BERLIN", "베를린"),
    MUNICH(Country.GERMANY.countryCode, "MUNICH", "뮌헨"),
    FRANKFURT(Country.GERMANY.countryCode, "FRANKFURT", "프랑크푸르트"),
    HAMBURG(Country.GERMANY.countryCode, "HAMBURG", "함부르크"),
    COLOGNE(Country.GERMANY.countryCode, "COLOGNE", "쾰른"),
    DUSSELDORF(Country.GERMANY.countryCode, "DUSSELDORF", "뒤셀도르프"),

    // Italy
    ROME(Country.ITALY.countryCode, "ROME", "로마"),
    MILAN(Country.ITALY.countryCode, "MILAN", "밀라노"),
    VENICE(Country.ITALY.countryCode, "VENICE", "베네치아"),
    FLORENCE(Country.ITALY.countryCode, "FLORENCE", "피렌체"),
    NAPLES(Country.ITALY.countryCode, "NAPLES", "나폴리"),
    TURIN(Country.ITALY.countryCode, "TURIN", "투린"),

    // Spain
    MADRID(Country.SPAIN.countryCode, "MADRID", "마드리드"),
    BARCELONA(Country.SPAIN.countryCode, "BARCELONA", "바르셀로나"),
    VALENCIA(Country.SPAIN.countryCode, "VALENCIA", "발렌시아"),
    SEVILLE(Country.SPAIN.countryCode, "SEVILLE", "세비야"),
    MALAGA(Country.SPAIN.countryCode, "MALAGA", "말라가"),
    BILBAO(Country.SPAIN.countryCode, "BILBAO", "빌바오"),

    // Canada
    TORONTO(Country.CANADA.countryCode, "TORONTO", "토론토"),
    VANCOUVER(Country.CANADA.countryCode, "VANCOUVER", "밴쿠버"),
    MONTREAL(Country.CANADA.countryCode, "MONTREAL", "몬트리올"),
    CALGARY(Country.CANADA.countryCode, "CALGARY", "캘거리"),
    OTTAWA(Country.CANADA.countryCode, "OTTAWA", "오타와"),
    QUEBEC_CITY(Country.CANADA.countryCode, "QUEBEC_CITY", "퀘벡 시티"),

    // Australia
    SYDNEY(Country.AUSTRALIA.countryCode, "SYDNEY", "시드니"),
    MELBOURNE(Country.AUSTRALIA.countryCode, "MELBOURNE", "멜버른"),
    BRISBANE(Country.AUSTRALIA.countryCode, "BRISBANE", "브리즈번"),
    PERTH(Country.AUSTRALIA.countryCode, "PERTH", "퍼스"),
    ADELAIDE(Country.AUSTRALIA.countryCode, "ADELAIDE", "애들레이드"),
    CANBERRA(Country.AUSTRALIA.countryCode, "CANBERRA", "캔버라"),

    // Brazil
    SAO_PAULO(Country.BRAZIL.countryCode, "SAO_PAULO", "상파울로"),
    RIO_DE_JANEIRO(Country.BRAZIL.countryCode, "RIO_DE_JANEIRO", "리오 데 자네이로"),
    BRASILIA(Country.BRAZIL.countryCode, "BRASILIA", "브라질리아"),
    SALVADOR(Country.BRAZIL.countryCode, "SALVADOR", "살바도르"),
    BELO_HORIZONTE(Country.BRAZIL.countryCode, "BELO_HORIZONTE", "벨루 오리손티"),

    // India
    MUMBAI(Country.INDIA.countryCode, "MUMBAI", "뭄바이"),
    NEW_DELHI(Country.INDIA.countryCode, "NEW_DELHI", "뉴델리"),
    BANGALORE(Country.INDIA.countryCode, "BANGALORE", "방갈로르"),
    CHENNAI(Country.INDIA.countryCode, "CHENNAI", "첸나이"),
    KOLKATA(Country.INDIA.countryCode, "KOLKATA", "콜카타"),
    HYDERABAD(Country.INDIA.countryCode, "HYDERABAD", "하이데라바드"),

    MOSCOW(Country.RUSSIA.countryCode, "MOSCOW", "모스크바"),
    SAINT_PETERSBURG(Country.RUSSIA.countryCode, "SAINT_PETERSBURG", "상트페테르부르크"),
    NOVOSIBIRSK(Country.RUSSIA.countryCode, "NOVOSIBIRSK", "노보시비르스크"),
    EKATERINBURG(Country.RUSSIA.countryCode, "EKATERINBURG", "예카테린부르크"),
    KAZAN(Country.RUSSIA.countryCode, "KAZAN", "카잔"),
    NIZHNY_NOVGOROD(Country.RUSSIA.countryCode, "NIZHNY_NOVGOROD", "니즈니 노브고로드"),

    // Turkey
    ISTANBUL(Country.TURKIYE.countryCode, "ISTANBUL", "이스탄불"),
    ANKARA(Country.TURKIYE.countryCode, "ANKARA", "앙카라"),
    IZMIR(Country.TURKIYE.countryCode, "IZMIR", "이즈미르"),
    BURSA(Country.TURKIYE.countryCode, "BURSA", "부르사"),
    ANTALYA(Country.TURKIYE.countryCode, "ANTALYA", "안탈리아"),
    ADANA(Country.TURKIYE.countryCode, "ADANA", "아다나"),

    // Argentina
    BUENOS_AIRES(Country.ARGENTINA.countryCode, "BUENOS_AIRES", "부에노스 아이레스"),
    CORDOBA(Country.ARGENTINA.countryCode, "CORDOBA", "코르도바"),
    ROSARIO(Country.ARGENTINA.countryCode, "ROSARIO", "로사리오"),
    MENDOZA(Country.ARGENTINA.countryCode, "MENDOZA", "멘도사"),
    SALTA(Country.ARGENTINA.countryCode, "SALTA", "살타"),

    // Egypt
    CAIRO(Country.EGYPT.countryCode, "CAIRO", "카이로"),
    ALEXANDRIA(Country.EGYPT.countryCode, "ALEXANDRIA", "알렉산드리아"),
    LUXOR(Country.EGYPT.countryCode, "LUXOR", "룩소르"),
    SHARM_EL_SHEIKH(Country.EGYPT.countryCode, "SHARM_EL_SHEIKH", "샤름 엘 쉬크"),
    ASWAN(Country.EGYPT.countryCode, "ASWAN", "아스완"),
    HURGHADA(Country.EGYPT.countryCode, "HURGHADA", "후르가다"),

    // Thailand
    BANGKOK(Country.THAILAND.countryCode, "BANGKOK", "방콕"),
    PHUKET(Country.THAILAND.countryCode, "PHUKET", "푸켓"),
    CHIANG_MAI(Country.THAILAND.countryCode, "CHIANG_MAI", "치앙마이"),
    PATTAYA(Country.THAILAND.countryCode, "PATTAYA", "파타야"),
    KRABI(Country.THAILAND.countryCode, "KRABI", "크라비"),
    HUA_HIN(Country.THAILAND.countryCode, "HUA_HIN", "후아힌"),

    // Mexico
    MEXICO_CITY(Country.MEXICO.countryCode, "MEXICO_CITY", "멕시코시티"),
    CANCUN(Country.MEXICO.countryCode, "CANCUN", "칸쿤"),
    GUADALAJARA(Country.MEXICO.countryCode, "GUADALAJARA", "과달라하라"),
    MONTERREY(Country.MEXICO.countryCode, "MONTERREY", "몬테레이"),
    TULUM(Country.MEXICO.countryCode, "TULUM", "툴룸"),
    OAXACA(Country.MEXICO.countryCode, "OAXACA", "오아하카"),

    // Indonesia
    JAKARTA(Country.INDONESIA.countryCode, "JAKARTA", "자카르타"),
    BALI(Country.INDONESIA.countryCode, "BALI", "발리"),
    BANDUNG(Country.INDONESIA.countryCode, "BANDUNG", "반둥"),
    YOGYAKARTA(Country.INDONESIA.countryCode, "YOGYAKARTA", "요가야카르타"),
    SURABAYA(Country.INDONESIA.countryCode, "SURABAYA", "수라바야"),
    MAKASSAR(Country.INDONESIA.countryCode, "MAKASSAR", "마카사르");

}