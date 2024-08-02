package com.travel.withaeng.applicationservice.user

import com.travel.withaeng.applicationservice.auth.UserNicknameUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserNicknameUtilsTest {

    @Test
    fun createNickname() {
        // given
        val hyphen = '-'

        // when
        val nickname = UserNicknameUtils.createTemporaryNickname()

        // then
        assertThat(nickname.contains(hyphen)).isTrue()
        assertThat(nickname.count { char -> char == hyphen }).isEqualTo(1)
    }

}
