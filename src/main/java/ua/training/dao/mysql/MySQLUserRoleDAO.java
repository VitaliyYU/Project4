package ua.training.dao.mysql;

import ua.training.dao.UserRoleDAO;
import ua.training.database.ConnectionFactory;
import ua.training.entity.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserRoleDAO implements UserRoleDAO {

    private ConnectionFactory connectionFactory;
    private static MySQLUserRoleDAO userRoleDAO=new MySQLUserRoleDAO();

    private  MySQLUserRoleDAO(){
        this.connectionFactory=ConnectionFactory.getInstance();
    }

    public static MySQLUserRoleDAO getInstance(){
        return userRoleDAO;
    }

    @Override
    public void create(UserRole newUserRole) {
        String insertUserRole="insert into user_role(role_name) values(?)";

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(insertUserRole);
            setUserRoleInStatement(statement,newUserRole);
            statement.executeUpdate();
        }catch (SQLException e){
            //logging
        }
    }

    @Override
    public UserRole findById(Integer userRoleId) {
        String selectUserRoleByIdStatment="select * from role where role_id=? and status=0";
        UserRole userRole=null;
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(selectUserRoleByIdStatment);
            statement.setInt(1,userRoleId);
            ResultSet resultSet=statement.executeQuery();
            userRole=writeUserRoleToListFromResultSet(resultSet).get(0);
        }catch (SQLException e){
            //logging
        }
        return userRole;
    }

    @Override
    public List<UserRole> findAll() {
        String  selectAllUserRole="select * from role where status=0";
        List<UserRole> userRoles=null;

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(selectAllUserRole);
            ResultSet resultSet=statement.executeQuery();
            userRoles=writeUserRoleToListFromResultSet(resultSet);
        }catch (SQLException e){
            //logging
        }

        return userRoles;
    }

    @Override
    public void update(UserRole updateUserRole) {
        String updateStatement="Update role set role_name=? where role_id=?";

        try(Connection connection=connectionFactory.getConnection()) {
            PreparedStatement statement=connection.prepareStatement(updateStatement);
            setUserRoleInStatement(statement,updateUserRole);
            statement.setInt(2,updateUserRole.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            //logging
        }
    }

    @Override
    public void delete(Integer userRoleId) {
        String deleteStatement="Update role set status=1 where role_id=?";

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(deleteStatement);
            statement.setInt(1,userRoleId);

            statement.executeUpdate();
        }catch (SQLException e){
            //logging
        }
    }

    private void  setUserRoleInStatement(PreparedStatement statement, UserRole newUserRole) throws SQLException {
        statement.setString(1,newUserRole.getRoleName());
    }

    private List<UserRole> writeUserRoleToListFromResultSet(ResultSet resultSet) throws SQLException {
        List<UserRole> result = new ArrayList<>();
        while (resultSet.next()) {
            UserRole newUserRole = new UserRole();
            newUserRole.setId(resultSet.getInt("role_id"));
            newUserRole.setRoleName(resultSet.getString("role_name"));

            result.add(newUserRole);
        }
        return result;
    }

}
