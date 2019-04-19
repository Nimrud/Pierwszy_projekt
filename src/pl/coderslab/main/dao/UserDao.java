package pl.coderslab.main.dao;

import pl.coderslab.main.domain.User;
import pl.coderslab.main.utils.DbConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final String SELECT_ALL_QUERY = "SELECT * FROM users";
    private final String SELECT_BY_ID_QUERY = "SELECT * FROM users WHERE id=?";
    private final String UPDATE_QUERY = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private final String CREATE_QUERY = "INSERT INTO users(username, email, password) VALUES (?,?,?)";
    private final String DELETE_QUERY = "DELETE FROM users";
    private final String DELETE_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";
    public User create(User user){
        try(Connection connection = DbConnector.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,user.getUserName());
            stmt.setString(2,user.getEmail());
            stmt.setString(3,user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                user.setId(rs.getInt(1));
            }
            return user;
        }catch (SQLException e){e.printStackTrace();}
        return null;
    }
    public void update(User user){
        try(Connection connection = DbConnector.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY);
            stmt.setString(1,user.getUserName());
            stmt.setString(2,user.getEmail());
            stmt.setString(3,user.getPassword());
            stmt.setInt(4,user.getId());
            stmt.executeUpdate();
        }catch (SQLException e){e.printStackTrace();}
    }
    public void delete(User user){
        try(Connection connection = DbConnector.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(DELETE_BY_ID_QUERY);
            stmt.setInt(1,user.getId());
            stmt.executeUpdate();
        }catch (SQLException e){e.printStackTrace();}
    }
    public User findById(int id){
        try(Connection connection = DbConnector.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID_QUERY);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                User user =new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        }catch (SQLException e){e.printStackTrace();}
        return null;
    }
    public List<User> findAll(){
        try(Connection connection = DbConnector.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_QUERY);

            List<User> users = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                User user =new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add( user );
            }
            return users;
        }catch (SQLException e){e.printStackTrace();}
        return null;
    }
}