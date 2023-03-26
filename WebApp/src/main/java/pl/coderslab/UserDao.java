package pl.coderslab;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final String FIND_ALL_BOOKS_QUERY = "SELECT * FROM users;";

    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(FIND_ALL_BOOKS_QUERY);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                userList.add(new User(id, name, email, password));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return userList;
    }


    public User findById(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM USERS WHERE ID = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String id1 = rs.getString(1);
                String name = rs.getString(2);
                String mail = rs.getString(3);
                String password = rs.getString(4);
                System.out.println(String.format("%s %s %s %s", id1, name, mail, password));

            } else {
                System.out.println("User with id " + id + " not found");
            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to database or executing query: " + e.getMessage());
        }
        return null;
    }

    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement pS = conn.prepareStatement("INSERT INTO users(username,email,password) VALUES (?, ?,?)");
            pS.setString(1, user.getUserName());
            pS.setString(2, user.getEmail());
            pS.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            pS.executeUpdate();
            System.out.println("User added successfully");


        } catch (SQLException e) {
            System.err.println("User error added" + e.getMessage());

        }

        return user;

    }

    public void update(int id, User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement pS = conn.prepareStatement("Update users set username=?,email=?,password=? where id = ?");
            pS.setString(1, user.getUserName());
            pS.setString(2, user.getEmail());
            pS.setString(3, user.getPassword());
            pS.setInt(4, id);
            pS.executeUpdate();
            System.out.println("User updated successfully");


        } catch (SQLException e) {
            System.err.println("User error added" + e.getMessage());
            ;
        }


    }

    public boolean delete(User user) {
        try {
            DbUtil d = new DbUtil();
            DbUtil.remove(DbUtil.getConnection(), "users", user.getId());
            System.out.println("User delete successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
