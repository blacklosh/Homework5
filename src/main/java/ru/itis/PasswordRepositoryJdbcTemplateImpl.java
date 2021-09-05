package ru.itis;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

public class PasswordRepositoryJdbcTemplateImpl implements PasswordRepository {
    //language=SQL
    private static final String SQL_SELECT_BY_PASSWORD = "select * from password_blacklist where password = ?";

    //language=SQL
    private static final String SQL_INSERT_PASSWORD = "insert into password_blacklist(password) values (?)";

    //language=SQL
    private static final String SQL_DELETE_PASSWORD = "delete from password_blacklist where password = ?";

    private JdbcTemplate jdbcTemplate;

    public PasswordRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final ResultSetExtractor<List<String>> passwordsResultSetExtractor = resultSet -> {
        List<String> result = new LinkedList<>();
        while (resultSet.next()) result.add(resultSet.getString("password"));
        return result;
    };

    @Override
    public boolean checkPasswordInBlacklist(String password) {
        return !jdbcTemplate.query(SQL_SELECT_BY_PASSWORD, passwordsResultSetExtractor, password).isEmpty();
    }

    @Override
    public void savePasswordInBlacklist(String password) {
        /*jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PASSWORD);

            statement.setString(1, password);

            return statement;
        });*/
        jdbcTemplate.update(SQL_INSERT_PASSWORD, password);
    }

    @Override
    public void deletePasswordFromBlacklist(String password) {
        jdbcTemplate.update(SQL_DELETE_PASSWORD,password);
    }
}
