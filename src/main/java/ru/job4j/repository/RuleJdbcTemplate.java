package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Rule;

import java.util.List;

@Repository
@AllArgsConstructor
public class RuleJdbcTemplate {
    private final static String CREATE = "insert into rule (name) values (?)";

    private final static String FIND_ALL = "select * from rule order by id";

    private final static String FIND_BY_ID = "select name from rule where id = ?";

    private final JdbcTemplate jdbc;

    public Rule create(Rule rule) {
        jdbc.update(CREATE,
                rule.getName()
        );
        return rule;
    }

    public List<Rule> findAll() {
        return jdbc.query(FIND_ALL,
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public Rule findById(int id) {
        return jdbc.queryForObject(FIND_BY_ID,
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(id);
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id);
    }
}
