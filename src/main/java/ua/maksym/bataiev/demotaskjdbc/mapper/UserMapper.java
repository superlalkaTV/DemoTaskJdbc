package ua.maksym.bataiev.demotaskjdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import ua.maksym.bataiev.demotaskjdbc.model.User;

public class UserMapper implements RowMapper<User> {

  public static final String BASE_SQL = "SELECT id,first_name, last_name FROM users";

  @Override
  public User mapRow(ResultSet resultSet, int i) throws SQLException {
    Long id = resultSet.getLong("id");
    String firstName = resultSet.getString("first_name");
    String lastName = resultSet.getString("last_name");

    return new User(id, firstName, lastName);
  }
}
