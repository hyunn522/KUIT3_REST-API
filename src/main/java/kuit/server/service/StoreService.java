package kuit.server.service;

import kuit.server.dao.StoreDao;
import kuit.server.dto.store.GetCategoriesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreDao storeDao;

    public GetCategoriesResponse getCategories() {
        log.info("[UserService.getUsers]");
        return storeDao.getCategories();
    }
}
