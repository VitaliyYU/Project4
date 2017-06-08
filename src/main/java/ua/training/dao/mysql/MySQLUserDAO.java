package ua.training.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.constant.Query;
import ua.training.dao.UserDAO;
import ua.training.database.ConnectionFactory;
import ua.training.entity.User;
import ua.training.entity.UserRole;
import ua.training.entity.Wallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySQLUserDAO implements UserDAO {
    private static final Logger log=Logger.getLogger(MySQLUserDAO.class);

    private ConnectionFactory connectionFactory;
    private static MySQLUserDAO userDAO=new MySQLUserDAO();

    private MySQLUserDAO(){
            this.connectionFactory=ConnectionFactory.getInstance();
    }

    public static MySQLUserDAO getInstance(){
        return userDAO;
    }
    @Override
    public void create(User newUser) {
        log.info("begin creating user "+newUser);

        try(Connection connection=connectionFactory.getConnection()){

            PreparedStatement statement=connection.prepareStatement(Query.INSERT_USER);
            setUserInStatement(statement,newUser);
            statement.executeUpdate();
            log.info("success create user"+newUser);
        }catch (SQLException e){
            log.error("error create user "+newUser,e);
        }
    }

    public User findByLoginAndPassword(String login,String password){
        User newUser=null;

        log.info("begin find user by login="+login+" and  password ="+password);

        try (Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(Query.SELECT_USER_BY_LOGIN_AND_PASSWORD);

            statement.setString(1,login);
            statement.setString(2,password);

            ResultSet resultSet=statement.executeQuery();
            newUser=parse(resultSet).get(0);
            log.info("succes find User by login="+login+"and password="+password);
        }catch (SQLException e){
            log.error("error by find login="+login+" and password="+password,e);
        }catch (IndexOutOfBoundsException e){
            log.error("don`t find user with login="+login+"and password="+password);
        }
        return newUser;

    }

    @Override
    public List<User> findAll() {
        List<User> allUser=null;

        log.info("begin finding all users");
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(Query.SELECT_ALL_USER);

            allUser=new ArrayList<>();

            ResultSet resultSet=statement.executeQuery();

            allUser=parse(resultSet);
            log.info("success find all users");

        }catch (SQLException e){
            log.error("error in finding all active users",e);
        }

        return allUser;
    }

    @Override
    public void update(User updateUser) {
        log.info("begin update User "+updateUser);
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(Query.UPDATE_USER);

            statement.setString(1,updateUser.getLogin());
            statement.setString(2,updateUser.getName());
            statement.setString(3,updateUser.getSurname());
            statement.setInt(4,updateUser.getWallet().getId());
            statement.setInt(5,updateUser.getId());

            statement.executeUpdate();
            log.info("success update user"+updateUser);
        }catch (SQLException e) {
            log.error("error update  user"+updateUser,e);
        }
    }

    @Override
    public void delete(Integer userId) {
        log.info("begin delete user by id="+userId);
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(Query.DELETE_USER);
            statement.setInt(1,userId);

            statement.executeUpdate();
            log.info("success delete user by id= "+userId);
        }catch (SQLException e){
            log.error("error delete user by id"+userId,e);
        }
    }

    private List<User> parse(ResultSet resultSet) throws SQLException {
        List<User> result = new ArrayList<>();

        while (resultSet.next()) {
            User newUser = new User();

            UserRole newUserRole=new UserRole();
            Wallet newWallet=new Wallet();
            newUser.setId(resultSet.getInt("user_id"));
            newUser.setLogin(resultSet.getString("login"));
            newUser.setName(resultSet.getString("name"));
            newUser.setSurname(resultSet.getString("surname"));

            newUserRole.setId(resultSet.getInt("role_id"));
            newUserRole.setRoleName(resultSet.getString("role_name"));

            newWallet.setId(resultSet.getInt("wallet_id"));
            newWallet.setCode(resultSet.getString("code"));
            newWallet.setCount(resultSet.getDouble("count"));

            newUser.setUserRole(newUserRole);
            newUser.setWallet(newWallet);

            result.add(newUser);
        }

        return result;
    }

    private void setUserInStatement(PreparedStatement statement,User updateUser) throws SQLException {
        statement.setString(1,updateUser.getLogin());
        statement.setString(2,updateUser.getPassword());
        statement.setString(3,updateUser.getName());
        statement.setString(4,updateUser.getSurname());
    }
}
