package kuit.server.dao;

import kuit.server.dto.store.GetCategoriesResponse;
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

}
