package ua.maksym.bataiev.demotaskjdbc.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ua.maksym.bataiev.demotaskjdbc.mapper.UserMapper;
import ua.maksym.bataiev.demotaskjdbc.model.User;

@Repository
public class UserDAO extends JdbcDaoSupport {

  @Autowired
  public UserDAO(DataSource dataSource) {
    setDataSource(dataSource);
  }

  public List<User> getAllUsers() {
    String sql = UserMapper.BASE_SQL;
    JdbcTemplate template = getJdbcTemplate();
    UserMapper mapper = new UserMapper();

    return template != null ? template.query(sql, mapper)
        : null;
  }

  public User getUserById(Long id) {
    String sql = UserMapper.BASE_SQL + " WHERE id=(?)";
    JdbcTemplate template = getJdbcTemplate();
    UserMapper mapper = new UserMapper();

    return template != null ? template.queryForObject(sql, mapper, id)
        : null;
  }

  public void createUser(User user) {
    String sql = "INSERT INTO users (first_name, last_name) VALUES (?,?)";
    JdbcTemplate template = getJdbcTemplate();

    if (template == null) {
      return;
    }
    template.update(sql, user.getFirstName(), user.getLastName());
  }

  public void deleteUser(Long id) {
    String sql = "DELETE FROM users WHERE id=(?)";
    JdbcTemplate template = getJdbcTemplate();

    if (template != null) {
      template.update(sql, id);
    }
  }

}