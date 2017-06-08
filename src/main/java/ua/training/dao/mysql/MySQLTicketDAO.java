package ua.training.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.constant.Query;
import ua.training.dao.TicketDAO;
import ua.training.database.ConnectionFactory;
import ua.training.entity.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySQLTicketDAO implements TicketDAO {

    private static final Logger log=Logger.getLogger(MySQLTicketDAO.class);

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
        log.info("begin create ticket");

        try(Connection connection=connectionFactory.getConnection()){
            log.info("success connection on TicketDAO");
            PreparedStatement statement=connection.prepareStatement(Query.INSERT_TICKET);

            setTicketInStatement(statement,newTicket);

            statement.executeUpdate();
            log.info("success create Ticket"+newTicket);
        }catch (SQLException e){
            //logging
        }

    }

    @Override
    public void create(Integer flightId,Integer n) {
        log.info("begin auto create "+n+" Ticket ");

        try(Connection connection=connectionFactory.getConnection()){
            for(int i=0;i<n;++i) {
                log.info("success connection on method auto create "+n+"ticket");
                PreparedStatement statement = connection.prepareStatement(Query.CREATE_TICKET);
                statement.setInt(1,i+1);
                statement.setInt(2,flightId);

                statement.executeUpdate();
                log.info("success create "+n+" ticket");
            }
        }catch (SQLException e){
            log.error("error auto create "+n+" ticket",e);
        }

    }


    @Override
    public List<Ticket> findByUserId(Integer userId) {
        List<Ticket> result=null;

        log.info("begin find Ticket by  User id "+ userId);
        try(Connection connection=connectionFactory.getConnection()){
            result=selectById(connection,Query.SELECT_TICKET_BY_USER_ID,userId);

            log.info("success find Ticket by user id "+userId);
        }catch (SQLException e){
            log.error("error find  by  user id "+userId,e);
        }

        return result;
    }

    @Override
    public Integer countAvailableTicket(Integer id){

        Integer result=0;
        log.info("begin counting available ticket by flight id= "+id);

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(Query.COUNT_AVAIBLE_TICKET);
            ResultSet resultSet=statement.executeQuery();

            resultSet.next();
            result=resultSet.getInt("count(ticket_id)");
            log.info("success counting  available ticket by flight id= "+id);
        }catch (SQLException e){
            log.error("error counting ticket by  flight id= "+id,e);
        }
        return result;
    }

    @Override
    public List<Ticket> findAllByFlightId(Integer id) {

        List<Ticket> allActiveTicket=null;
        log.info("begin find all ticket by flight id  ="+id);
        try(Connection connection=connectionFactory.getConnection()){
            allActiveTicket= selectById(connection,Query.SELECT_TICKET_BY_FLIGHT_ID,id);
            log.info("success find ticket by  flight id ="+id);
        }catch (SQLException e){
            log.error("error find ticket by flight id="+id);
        }

        return allActiveTicket;
    }

    @Override
    public Boolean update(Ticket updateTicket) {
            log.info("begin updating ticket"+updateTicket);
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(Query.UPDATE_TICKET);

            setTicketInStatement(statement,updateTicket);
            statement.setInt(5,updateTicket.getUserId());
            statement.setInt(6,updateTicket.getId());

            if(statement.executeUpdate()>0){
                log.info("success update ticket"+updateTicket);
                return true;
            }else {
                log.info("this ticket is already update");
                return false;
            }
        }catch (SQLException e) {
            log.error("error updating ticket"+updateTicket);
        }
        return false;
    }

    @Override
    public void delete(Integer ticketId) {
        log.info("begin delete ticket by id"+ticketId);

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(Query.DELETE_TICKET);
            statement.setInt(1,ticketId);

            statement.executeUpdate();
            log.info("success delete ticket by id"+ticketId);
        }catch (SQLException e){
            log.error("error delete ticket by id="+ticketId);
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

    private List<Ticket> parse(ResultSet resultSet) throws SQLException {
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
        return parse(result);
    }
}
