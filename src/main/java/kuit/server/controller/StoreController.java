package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.store.GetCategoriesResponse;
import kuit.server.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
