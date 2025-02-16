package com.withaeng.api.controller.accompany

import com.withaeng.api.applicationservice.accompany.AccompanyApplicationService
import com.withaeng.api.applicationservice.accompany.dto.AccompanyResponse
import com.withaeng.api.applicationservice.accompany.dto.AccompanySummaryResponse
import com.withaeng.api.applicationservice.accompany.dto.CreateAccompanyResponse
import com.withaeng.api.applicationservice.accompany.dto.FindAccompanyResponse
import com.withaeng.api.common.ApiResponse
import com.withaeng.api.controller.accompany.dto.*
import com.withaeng.api.security.authentication.UserInfo
import com.withaeng.api.security.resolver.GetAuth
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "Accompany", description = "동행 API")
@RestController
@RequestMapping("/api/v1/accompany")
class AccompanyController(
    private val accompanyApplicationService: AccompanyApplicationService,
) {

    @Operation(
        summary = "Create Accompany API",
        description = "동행 게시글 생성 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    fun create(
        @GetAuth userInfo: UserInfo,
        @RequestBody @Valid request: CreateAccompanyRequest,
    ): ApiResponse<CreateAccompanyResponse> {
        return ApiResponse.success(
            accompanyApplicationService.create(request.toServiceRequest(userInfo.id))
        )
    }

    @Operation(summary = "Retrieve Accompany API", description = "동행 게시글 단건 조회 API")
    @GetMapping("/{accompanyId}")
    fun retrieve(
        @GetAuth userInfo: UserInfo?,
        @Parameter(description = "동행 id") @PathVariable("accompanyId") accompanyId: Long,
    ): ApiResponse<FindAccompanyResponse> {
        return ApiResponse.success(
            accompanyApplicationService.detail(accompanyId, userInfo?.id)
        )
    }

    @Operation(summary = "Search Accompany", description = "동행 검색 API (필터링만 지원)")
    @GetMapping("/search")
    fun search(
        @ParameterObject request: SearchAccompanyRequest,
    ): ApiResponse<List<AccompanySummaryResponse>> {
        return ApiResponse.success(
            accompanyApplicationService.search(request.toPageInfoRequest(), request.toServiceRequest())
        )
    }

    @Operation(summary = "Retrieve All Accompany API", description = "모든 동행 게시글 조회 API")
    @GetMapping("/all")
    fun retrieveAll(): ApiResponse<List<AccompanyResponse>> {
        return ApiResponse.success(
            accompanyApplicationService.retrieveAll()
        )
    }

    @Operation(
        summary = "Update Accompany API",
        description = "동행 게시글 수정 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PutMapping("/{accompanyId}")
    fun update(
        @GetAuth userInfo: UserInfo,
        @Parameter(description = "동행 id") @PathVariable accompanyId: Long,
        @RequestBody @Valid param: UpdateAccompanyRequest,
    ): ApiResponse<AccompanyResponse> {
        return ApiResponse.success(
            accompanyApplicationService.update(
                param.toServiceRequest(
                    accompanyId = accompanyId,
                    userId = userInfo.id
                )
            )
        )
    }

    @Operation(
        summary = "Delete Accompany API",
        description = "동행 게시글 삭제 API (For Admin)",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @DeleteMapping("/{accompanyId}")
    fun delete(
        @Parameter(description = "동행 id") @PathVariable accompanyId: Long,
    ): ApiResponse<Unit> {
        accompanyApplicationService.delete(accompanyId)
        return ApiResponse.success()
    }
}