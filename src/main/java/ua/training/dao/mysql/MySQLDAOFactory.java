package ua.training.dao.mysql;

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
