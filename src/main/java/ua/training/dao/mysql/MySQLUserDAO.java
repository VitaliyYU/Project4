package ua.training.dao.mysql;

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

/**
 * Created by vitaliy on 21.05.17.
 */
public class MySQLUserDAO implements UserDAO {
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
        String insertUser="Insert into user " +
                "(login ,password ,name, surname)" +
                " values(?,?,?,?)";
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(insertUser);
            setUserInStatement(statement,newUser);
            System.out.println(statement.executeUpdate());
        }catch (SQLException e){
            ///logging
        }

    }
    @Override
    public User findById(Integer userId) {
        String selectUserById="Select * from ((user inner join wallet on user.wallet_id=wallet.wallet_id)inner join user_role on user.role_id=user_role.role_id ) where user.user_id=? and user.status=0";
        User newUser=null;
        try (Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(selectUserById);
            statement.setInt(1,userId);
            ResultSet resultSet=statement.executeQuery();

            newUser=writeUserToListFromResultSet(resultSet).get(0);
        }catch (SQLException e){
            //logging
        }

        return newUser;
    }

    @Override
    public User findByLogin(String login) {
        String selectUserByLogin="Select * from((user inner join wallet on user.wallet_id=wallet.wallet_id)inner join user_role on user.role_id=user_role.role_id ) where login=? and user.status=0";
        User newUser=null;
        try (Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(selectUserByLogin);
            statement.setString(1,login);
            ResultSet resultSet=statement.executeQuery();
            newUser=writeUserToListFromResultSet(resultSet).get(0);
        }catch (SQLException e){
            //logging
        }

        return newUser;

    }
    public User findByLoginAndPassword(String login,String password){
        String selectUserByLogin="Select * from((user inner join wallet on user.wallet_id=wallet.wallet_id)inner join user_role on user.role_id=user_role.role_id ) where login=? and password=?  and user.status=0";
        User newUser=null;
        try (Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(selectUserByLogin);
            statement.setString(1,login);
            statement.setString(2,password);
            ResultSet resultSet=statement.executeQuery();
            newUser=writeUserToListFromResultSet(resultSet).get(0);
        }catch (SQLException e){
            //logging
        }catch (IndexOutOfBoundsException e){

        }
        return newUser;

    }

    @Override
    public List<User> findAll() {
        String selectAllUser="select * from ((user left join wallet on user.wallet_id=wallet.wallet_id)left join user_role on user.role_id=user_role.role_id ) where user.status=0";
        List<User> allUser=null;

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(selectAllUser);
            allUser=new ArrayList<User>();
            ResultSet resultSet=statement.executeQuery();

            allUser=writeUserToListFromResultSet(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
            //logging
        }

        return allUser;
    }

    @Override
    public void update(User updateUser) {
        String updateUserStatement="Update user SET login=? , name=?,surname=?,wallet_id=? where user_id=?";

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(updateUserStatement);
            statement.setString(1,updateUser.getLogin());
            statement.setString(2,updateUser.getName());
            statement.setString(3,updateUser.getSurname());
            statement.setInt(4,updateUser.getWallet().getId());
            statement.setInt(5,updateUser.getId());
            statement.executeUpdate();
        }catch (SQLException e) {
            //logging
        }
    }

    @Override
    public void delete(Integer userId) {
        String deleteStatement="Update user set status=1 where user_id=?";

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(deleteStatement);
            statement.setInt(1,userId);

            statement.executeUpdate();
        }catch (SQLException e){
            //logging
        }
    }

    private List<User> writeUserToListFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> result = new ArrayList<User>();

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
