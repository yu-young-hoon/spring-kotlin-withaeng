package com.withaeng.api.applicationservice.accompany

import com.withaeng.domain.accompany.AccompanyIncrementViewCountEvent
import com.withaeng.domain.accompany.AccompanyService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AccompanyEventListener(
    private val accompanyService: AccompanyService
) {
    @EventListener
    @Async
    @Transactional
    fun incrementViewCountEventListener(event: AccompanyIncrementViewCountEvent) {
        event.accompanyId?.let { accompanyService.increaseViewCount(it) }
    }
}