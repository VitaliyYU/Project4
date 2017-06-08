package ua.training.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.constant.Query;
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
    private static final Logger log=Logger.getLogger(MySQLUserRoleDAO.class);

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
        log.info("begin creating new  User role"+newUserRole);
        try(Connection connection=connectionFactory.getConnection()){

            PreparedStatement statement=connection.prepareStatement(Query.INSERT_USER_ROLE);

            setUserRoleInStatement(statement,newUserRole);

            statement.executeUpdate();
            log.info("success create new user role"+newUserRole);
        }catch (SQLException e){
            log.error("error create new user role"+newUserRole,e);
        }
    }


    @Override
    public List<UserRole> findAll() {
        List<UserRole> userRoles=null;
        log.info("begin finding all active user role");
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(Query.SELECT_USER_ROLE);
            ResultSet resultSet=statement.executeQuery();

            userRoles=parse(resultSet);
            log.info("success finding all user role");
        }catch (SQLException e){
            log.error("error find  all user role",e);
        }

        return userRoles;
    }

    @Override
    public void update(UserRole updateUserRole) {
        log.info("begin update user role");
        try(Connection connection=connectionFactory.getConnection()) {
            PreparedStatement statement=connection.prepareStatement(Query.UPDATE_USER_ROLE);

            setUserRoleInStatement(statement,updateUserRole);

            statement.setInt(2,updateUserRole.getId());

            statement.executeUpdate();
            log.info("success update user role");
        }catch (SQLException e){
            log.error("error update  user role"+updateUserRole,e);
        }
    }

    @Override
    public void delete(Integer userRoleId) {
        log.info("begin delete user role by id"+userRoleId);
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(Query.DELETE_USER_ROLE);
            statement.setInt(1,userRoleId);

            statement.executeUpdate();
            log.info("success delete user role by id "+userRoleId);
        }catch (SQLException e){
            log.error("error delete user role by  id="+userRoleId,e);
        }
    }

    private void  setUserRoleInStatement(PreparedStatement statement, UserRole newUserRole) throws SQLException {
        statement.setString(1,newUserRole.getRoleName());
    }

    private List<UserRole> parse(ResultSet resultSet) throws SQLException {
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
