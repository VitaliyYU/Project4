package ua.training.dao.mysql;

/**
 * Created by vitaliy on 20.05.17.
 */
public class MySQLDAOFactory {
    private  MySQLDAOFactory(){

    }

    public static MySQLUserDAO getMySQLUserDAO(){
     return MySQLUserDAO.getInstance();
    }

    public static MySQLFlightDAO getMySQLFlightDAO(){
        return MySQLFlightDAO.getInstance();
    }

    public static MySQLUserRoleDAO getMySQLUserRoleDAO(){
        return MySQLUserRoleDAO.getInstance();
    }

    public static MySQLTicketDAO getMySQLTicketDAO(){
        return MySQLTicketDAO.getInstance();
    }

    public static MySQLWalletDAO getMySQLWalletDAO(){
        return MySQLWalletDAO.getInstance();
    }



}
