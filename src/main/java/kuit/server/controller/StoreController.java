package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.store.GetCategoriesResponse;
import kuit.server.dto.store.GetStoreListResponse;
import kuit.server.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    // 의존관계 주입
    private final StoreService storeService;

    /**
     * 모든 카테고리 목록 조회
     */
    @GetMapping("/categories")
    public BaseResponse<GetCategoriesResponse> getCategories() {
        log.info("[StoreController.getCategories]");
        return new BaseResponse<>(storeService.getCategories());
    }

    /**
     * 특정 카테고리의 가게 목록 조회
     */
    @GetMapping("/{category}")
    public BaseResponse<GetStoreListResponse> getStoresByCategory(
            @PathVariable String category,
            @RequestParam(required = false, defaultValue = "") String sortBy
    ) {
        log.info("[StoreController.getStoresByCategory]");
        return new BaseResponse<>(storeService.getStoresByCategory(category, sortBy));
    }
}
