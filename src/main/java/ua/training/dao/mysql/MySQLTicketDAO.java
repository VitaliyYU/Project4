package ua.training.dao.mysql;

import ua.training.dao.TicketDAO;
import ua.training.database.ConnectionFactory;
import ua.training.entity.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaliy on 21.05.17.
 */
public class MySQLTicketDAO implements TicketDAO {
    private ConnectionFactory connectionFactory;
    private static MySQLTicketDAO ticketDAO=new MySQLTicketDAO();

    private MySQLTicketDAO(){
        this.connectionFactory=ConnectionFactory.getInstance();
    }

    public static MySQLTicketDAO getInstance(){
        return ticketDAO;
    }

    @Override
    public void create(Ticket newTicket) {
        String insertUserRole="insert into ticket " +
                "(place ,user_id , flight_id, baggage,  priority_registration, priority_boarding) " +
                "values(?,?,?,?,?,?)";

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(insertUserRole);
            setTicketInStatement(statement,newTicket);
            statement.executeUpdate();
        }catch (SQLException e){
            //logging
        }

    }

    @Override
    public void create(Integer flightId,Integer n) {
        String insertTicket="insert into ticket " +
                "(place , flight_id) " +
                "values(?,?)";

        try(Connection connection=connectionFactory.getConnection()){
            for(int i=0;i<n;++i) {

                PreparedStatement statement = connection.prepareStatement(insertTicket);
                statement.setInt(1,i+1);
                statement.setInt(2,flightId);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            //logging
        }

    }


    @Override
    public List<Ticket> findByUserId(Integer userId) {
        String selectTicketByUserId="select * from (ticket inner join flight on ticket.flight_id=flight.flight_id) " +
                "where user_id=? and flight.status=0";
        List<Ticket> result=null;
        try(Connection connection=connectionFactory.getConnection()){
            result=selectById(connection,selectTicketByUserId,userId);
        }catch (SQLException e){
            //logging
        }

        return result;
    }
    @Override
    public Integer countAvaibleTicket(Integer id){
        String count="select count(ticket_id)  from ticket where status=0";
        Integer result=0;
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(count);
            ResultSet resultSet=statement.executeQuery();
            resultSet.next();
            result=resultSet.getInt("count(ticket_id)");
            return result;
        }catch (SQLException e){
            ///logging
        }
        return result;
    }
    @Override
    public List<Ticket> findAllByFlightId(Integer id) {
        String selectAllTicket="select * from ticket where status=0 and flight_id=?";
        List<Ticket> allActiveTicket=null;
        try(Connection connection=connectionFactory.getConnection()){
            allActiveTicket= selectById(connection,selectAllTicket,id);
        }catch (SQLException e){
            //logging
        }

        return allActiveTicket;
    }

    @Override
    public void update(Ticket updateTicket) {
        String updateTicketStatement="Update ticket SET place=? ,baggage=?,priority_boarding=?,priority_registration=?,user_id=? where ticket_id=?";

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(updateTicketStatement);

            setTicketInStatement(statement,updateTicket);
            statement.setInt(5,updateTicket.getUserId());
            statement.setInt(6,updateTicket.getId());
            statement.executeUpdate();
        }catch (SQLException e) {
            //logging
        }
    }

    @Override
    public void delete(Integer ticketId) {
        String deleteStatement="Update ticket set status=1 where ticket_id=?";

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(deleteStatement);
            statement.setInt(1,ticketId);

            statement.executeUpdate();
        }catch (SQLException e){
            //logging
        }
    }

    private void setTicketInStatement(PreparedStatement statement, Ticket updateTicket) throws SQLException {
        statement.setInt(1,updateTicket.getPlace());
        statement.setInt(2,updateTicket.getBaggage());
        statement.setBoolean(3,updateTicket.getPriorityRegistration());
        statement.setBoolean(4,updateTicket.getPriorityBoarding());
        statement.setInt(5,updateTicket.getUserId());
        statement.setInt(6,updateTicket.getId());
    }

    private List<Ticket> writeTicketToListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Ticket> result = new ArrayList<>();
        while (resultSet.next()) {
            Ticket newTicket = new Ticket();
            newTicket.setId(resultSet.getInt("ticket_id"));
            newTicket.setPlace(resultSet.getInt("place"));
            newTicket.setFlightId(resultSet.getInt("flight_id"));
            newTicket.setBaggage(resultSet.getInt("baggage"));
            newTicket.setPriorityBoarding(resultSet.getBoolean("priority_boarding"));
            newTicket.setPriorityRegistration(resultSet.getBoolean("priority_registration"));
            result.add(newTicket);
        }

        return result;
    }
    private List<Ticket> selectById(Connection connection,String selectAllTicket,Integer id) throws SQLException {
        PreparedStatement statement=connection.prepareStatement(selectAllTicket);
        statement.setInt(1,id);
        ResultSet result=statement.executeQuery();
        return writeTicketToListFromResultSet(result);
    }
}
