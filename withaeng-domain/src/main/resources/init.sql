CREATE TABLE `accompany`
(
    `id`                  bigint(20) NOT NULL AUTO_INCREMENT,
    `created_at`          datetime(6) NOT NULL,
    `deleted_at`          datetime(6) DEFAULT NULL,
    `updated_at`          datetime(6) NOT NULL,
    `city`                enum('SEOUL','BUSAN','INCHEON','DAEGU','GWANGJU','DAEJEON','ULSAN','SUWON','CHANGWON','JEJU','TOKYO','OSAKA','KYOTO','SAPPORO','FUKUOKA','HIROSHIMA','NAGOYA','OKINAWA','KANAZAWA','BEIJING','SHANGHAI','HONG_KONG','MACAO','GUANGZHOU','SHENZHEN','CHENGDU','XIAMEN','TIANJIN','NEW_YORK','LOS_ANGELES','CHICAGO','LAS_VEGAS','SAN_FRANCISCO','WASHINGTON_DC','MIAMI','ORLANDO','SEATTLE','LONDON','EDINBURGH','MANCHESTER','BIRMINGHAM','GLASGOW','LIVERPOOL','CAMBRIDGE','PARIS','MARSEILLE','LYON','NICE','BORDEAUX','STRASBOURG','BERLIN','MUNICH','FRANKFURT','HAMBURG','COLOGNE','DUSSELDORF','ROME','MILAN','VENICE','FLORENCE','NAPLES','TURIN','MADRID','BARCELONA','VALENCIA','SEVILLE','MALAGA','BILBAO','TORONTO','VANCOUVER','MONTREAL','CALGARY','OTTAWA','QUEBEC_CITY','SYDNEY','MELBOURNE','BRISBANE','PERTH','ADELAIDE','CANBERRA','SAO_PAULO','RIO_DE_JANEIRO','BRASILIA','SALVADOR','BELO_HORIZONTE','MUMBAI','NEW_DELHI','BANGALORE','CHENNAI','KOLKATA','HYDERABAD','MOSCOW','SAINT_PETERSBURG','NOVOSIBIRSK','EKATERINBURG','KAZAN','NIZHNY_NOVGOROD','ISTANBUL','ANKARA','IZMIR','BURSA','ANTALYA','ADANA','BUENOS_AIRES','CORDOBA','ROSARIO','MENDOZA','SALTA','CAIRO','ALEXANDRIA','LUXOR','SHARM_EL_SHEIKH','ASWAN','HURGHADA','BANGKOK','PHUKET','CHIANG_MAI','PATTAYA','KRABI','HUA_HIN','MEXICO_CITY','CANCUN','GUADALAJARA','MONTERREY','TULUM','OAXACA','JAKARTA','BALI','BANDUNG','YOGYAKARTA','SURABAYA','MAKASSAR') DEFAULT NULL COMMENT '동행 장소 정보',
    `continent`           enum('EAST_ASIA','SOUTHEAST_ASIA','CENTRAL_ASIA','WESTERN_ASIA','EUROPE','OCEANIA','AFRICA','NORTH_AMERICA','SOUTH_AMERICA') DEFAULT NULL COMMENT '동행 장소 정보',
    `country`             enum('KOREA','JAPAN','HONG_KONG','MACAO','TAIWAN','CHINA','MONGOLIA','SINGAPORE','EAST_TIMOR','MYANMAR','CAMBODIA','LAOS','PHILIPPINES','MALAYSIA','INDONESIA','THAILAND','VIETNAM','BRUNEI','UZBEKISTAN','BANGLADESH','AZERBAIJAN','BUTANE','AFGHANISTAN','TAJIKISTAN','KYRGYZSTAN','KAZAKHSTAN','TURKMENISTAN','TIBET','ARAB_EMIRATES','JORDAN','YEMEN','SYRIA','IRAN','PAKISTAN','CYPRUS','SRI_LANKA','MALDIVE','BAHRAIN','LRAQ','PALESTINE','OMAN','INDIA','NEPAL','ISRAEL','CATARRH','LEBANON','SAUDI_ARABIA','KUWAIT','ARMENIA','GEORGIA','MALTA','MOLDOVA','MONTENEGRO','MONACO','MACEDONIA','LIECHTENSTEIN','LITHUANIA','LUXEMBOURG','ROMANIA','VATICAN','BELARUS','BOSNIA_HERCEGOVINA','CROATIA','UKRAINE','ESTONIA','ALBANIA','ANDORRA','SLOVAKIA','SERBIA','SAN_MARINO','BULGARIA','LATVIA','SLOVENIA','PORTUGAL','SWISS','GERMANY','NETHERLAND','AUSTRIA','ENGLAND','SPAIN','TURKIYE','ITALY','POLAND','ICELAND','FINLAND','CZECHIA','BELGIUM','HUNGARY','IRELAND','RUSSIA','GREECE','DENMARK','NORWAY','SWEDEN','FRANCE','AUSTRALIA','PALAU','PAPUA_NEW_GUINEA','TONGA','KIRIBATI','SOLOMON_ISLAND','SAMOA','TUNISIA','ANGOLA','ALGERIA','SIERRA_LEONE','SUDAN','SOMALIA','SEYCHELLES','SENEGAL','UGANDA','ZAMBIA','EQUATORIAL_GUINEA','TOGO','CONGO','COTE_D_IVOIRE','COMOROS','ZIMBABWE','DJIBOUTI','CENTRAL_AFRICAN_REPUBLIC','BURUNDI','BURKINA_FASO','GUINEA_BISSAU','BOTSWANA','GUINEA','GAMBIA','GABON','GHANA','MOROCCO','NAMIBIA','KENYA','ETHIOPIA','TANZANIA','NIGERIA','BENIN','NIGER','MOZAMBIQUE','MAURITANIA','MAURITIUS','MALI','MALAWI','MADAGASCAR','LIBYA','RWANDA','LESOTHO','LIBERIA','EGYPT','UNITED_STATES','CANADA','MEXICO','ANTIGUA_AND_BARBUDA','ARUBA','ANGUILLA','ANTARCTICA','BAHAMAS','BELIZE','BARBADOS','BERMUDA','BOLIVIA','BRAZIL','IRAQ','QATAR','ARGENTINA','CHILE','COLOMBIA','ECUADOR','PARAGUAY','PERU','SURINAME','URUGUAY','VENEZUELA') DEFAULT NULL COMMENT '동행 장소 정보',
    `accompany_status`    enum('ING','COMPLETE') NOT NULL COMMENT '동행 모집 상태',
    `banner_image_url`    varchar(255) DEFAULT NULL COMMENT '베너 이미지 URI',
    `content`             tinytext     NOT NULL COMMENT '동행 내용',
    `end_accompany_age`   int(11) NOT NULL COMMENT '동행 종료 연령',
    `end_trip_date`       date         NOT NULL COMMENT '여행 종료 일자',
    `member_count`        bigint(20) NOT NULL COMMENT '모집 인원',
    `open_kakao_url`      varchar(255) NOT NULL COMMENT '오픈 카카오 채팅 URI',
    `prefer_gender`       enum('MALE','FEMALE','NO_PREFERENCE') NOT NULL COMMENT '동행 선호 성별',
    `start_accompany_age` int(11) NOT NULL COMMENT '동행 시작 연령',
    `start_trip_date`     date         NOT NULL COMMENT '여행 시작 일자',
    `tags`                varchar(255) NOT NULL COMMENT '태그 목록',
    `title`               varchar(255) NOT NULL COMMENT '동행 제목',
    `user_id`             bigint(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `accompany_join_request`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `created_at`   datetime(6) NOT NULL,
    `deleted_at`   datetime(6) DEFAULT NULL,
    `updated_at`   datetime(6) NOT NULL,
    `status`       enum('WAIT','ACCEPT','REJECT','CANCEL') DEFAULT NULL,
    `user_id`      bigint(20) NOT NULL,
    `accompany_id` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY            `FKcy4r0qbc8wqbkm5h9g39p6b55` (`accompany_id`),
    CONSTRAINT `FKcy4r0qbc8wqbkm5h9g39p6b55` FOREIGN KEY (`accompany_id`) REFERENCES `accompany` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `accompany_like`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `created_at`   datetime(6) NOT NULL,
    `deleted_at`   datetime(6) DEFAULT NULL,
    `updated_at`   datetime(6) NOT NULL,
    `accompany_id` bigint(20) NOT NULL,
    `user_id`      bigint(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `accompany_reply`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `created_at`   datetime(6) NOT NULL,
    `deleted_at`   datetime(6) DEFAULT NULL,
    `updated_at`   datetime(6) NOT NULL,
    `accompany_id` bigint(20) NOT NULL,
    `content`      varchar(255) NOT NULL,
    `parent_id`    bigint(20) DEFAULT NULL,
    `status`       enum('ACTIVE','DELETED') NOT NULL,
    `user_id`      bigint(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `accompany_reply_like`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT,
    `created_at` datetime(6) NOT NULL,
    `deleted_at` datetime(6) DEFAULT NULL,
    `updated_at` datetime(6) NOT NULL,
    `reply_id`   bigint(20) NOT NULL,
    `user_id`    bigint(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `accompany_statistics`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `view_count`   bigint(20) NOT NULL COMMENT '조회수',
    `accompany_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_3lpyg2ol9tw1big2k0kcrgj0h` (`accompany_id`),
    CONSTRAINT `FKnksv9hrwriuldmo3d7ech9jm2` FOREIGN KEY (`accompany_id`) REFERENCES `accompany` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `user_travel_liking`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `created_at`    datetime(6) NOT NULL,
    `deleted_at`    datetime(6) DEFAULT NULL,
    `updated_at`    datetime(6) NOT NULL,
    `liking_id`     int(11) NOT NULL,
    `drinking_type` int(11) NOT NULL,
    `user_id`       bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY             `FKi7ig0qj5cup4de2b6vlrxlw8w` (`user_id`),
    CONSTRAINT `FKi7ig0qj5cup4de2b6vlrxlw8w` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `users`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT,
    `created_at`        datetime(6) NOT NULL,
    `deleted_at`        datetime(6) DEFAULT NULL,
    `updated_at`        datetime(6) NOT NULL,
    `birth`             date         DEFAULT NULL,
    `email`             varchar(255) DEFAULT NULL,
    `gender`            enum('MALE','FEMALE') DEFAULT NULL,
    `google_id`         varchar(255) DEFAULT NULL,
    `manner_score` double NOT NULL,
    `password`          varchar(255) DEFAULT NULL,
    `instagram`         varchar(255) DEFAULT NULL,
    `introduction`      varchar(255) DEFAULT NULL,
    `nickname`          varchar(255) NOT NULL,
    `name`              varchar(255) NOT NULL,
    `profile_image_url` varchar(255) DEFAULT NULL,
    `roles`             varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `verification_email`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT,
    `created_at` datetime(6) NOT NULL,
    `deleted_at` datetime(6) DEFAULT NULL,
    `updated_at` datetime(6) NOT NULL,
    `code`       varchar(255) NOT NULL,
    `email`      varchar(255) NOT NULL,
    `host`       varchar(255) DEFAULT NULL,
    `status`     enum('YET','DONE','FAILED') NOT NULL,
    `type`       enum('VERIFY_EMAIL','CHANGE_PASSWORD') NOT NULL,
    `user_id`    bigint(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
