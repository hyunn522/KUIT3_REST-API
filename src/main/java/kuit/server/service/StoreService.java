package kuit.server.service;

import kuit.server.dao.StoreDao;
import kuit.server.dto.store.GetCategoriesResponse;
import kuit.server.dto.store.GetStoreListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreDao storeDao;

    public GetCategoriesResponse getCategories() {
        log.info("[UserService.getUsers]");
        return storeDao.getCategories();
    }

    public GetStoreListResponse getStoresByCategory(String category, String sortBy) {
        log.info("[UserService.getStoresByCategory]");
        return storeDao.getStoresByCategory(category, sortBy);
    }
}
