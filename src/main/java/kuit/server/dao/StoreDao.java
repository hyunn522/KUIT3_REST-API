package kuit.server.dao;

import kuit.server.common.domain.Store;
import kuit.server.dto.store.GetCategoriesResponse;
import kuit.server.dto.store.GetStoreListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class StoreDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StoreDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public GetCategoriesResponse getCategories() {
        String sql = "select distinct category from store";
        Map<String, Object> params = new HashMap<>(); // Empty map (no parameters)
        List<String> categories = jdbcTemplate.queryForList(sql, params, String.class);
        return new GetCategoriesResponse(categories);
    }

    public GetStoreListResponse getStoresByCategory(String category, String sortBy) {
        String sql = "";

        // TODO: enum으로 빼기 (별점높은순: 0, 별점낮은순: 1, ..)
        switch (sortBy) {
            case "별점높은순":
                sql = "select name, category, phone_number, min_order_price, rate, save_count, review_count " +
                "from Store where category = :category order by rate desc";
                break;
            case "별점낮은순":
                sql = "select name, category, phone_number, min_order_price, rate, save_count, review_count " +
                        "from Store where category = :category order by rate asc";
                break;
            case "리뷰많은순":
                sql = "select name, category, phone_number, min_order_price, rate, save_count, review_count " +
                        "from Store where category = :category order by review_count desc";
                break;
        }

        Map<String, Object> params = Map.of("category", category);
        List<Store> stores = jdbcTemplate.query(sql, params, (rs, rn) -> new Store(
                rs.getString("name"),
                rs.getString("category"),
                rs.getString("phone_number"),
                rs.getLong("min_order_price"),
                rs.getDouble("rate"),
                rs.getInt("save_count"),
                rs.getInt("review_count")
        ));

        return new GetStoreListResponse(stores);
    }
}