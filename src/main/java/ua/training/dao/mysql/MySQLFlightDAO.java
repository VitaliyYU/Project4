package ua.training.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.constant.Query;
import ua.training.dao.FlightDAO;
import ua.training.database.ConnectionFactory;
import ua.training.entity.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MySQLFlightDAO implements FlightDAO {
    private static Logger log=Logger.getLogger(MySQLFlightDAO.class);

    private ConnectionFactory connectionFactory;
    private static MySQLFlightDAO flightDAO=new MySQLFlightDAO();


    public MySQLFlightDAO(){
        this.connectionFactory=ConnectionFactory.getInstance();
    }

    public static MySQLFlightDAO getInstance(){
        return flightDAO;
    }

    @Override
    public void create(Flight newFlight) {
        try(Connection connection=connectionFactory.getConnection()){
            log.info("Start create flight ");
            PreparedStatement statement=connection.prepareStatement(Query.INSERT_FLIGHT);

            setFlightInStatement(statement, newFlight);

            statement.executeUpdate();

            log.info("success create flight");
        }catch (SQLException e){
            log.error("error create Flight",e);
        }
    }

    @Override
    public List<Flight> find(String departure, String destination, Date date) {
        List<Flight> flight=null;
            log.info("begin find Flight  by Departure "+departure+"and destination"+destination+"and date" +date);

        try(Connection connection  = connectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(Query.SELECT_FLIGHT_BY_ID);

            log.info("success connection on find Flight");

            statement.setString(1,departure);
            statement.setString(2,destination);
            statement.setDate(3,date);

            ResultSet resultSet=statement.executeQuery();

            flight=parse(resultSet);

            log.info("success find  flight by departure"+departure+"and destination"+destination+"and date"+date);
        }catch (SQLException e){
            log.error("error find by  departure and destination",e);
        }
        return  flight;
    }

    @Override
    public List<Flight> find(String departure, String destination) {

        List<Flight> flight=null;

        log.info("begin find Flight  by Departure "+departure+"and destination"+destination);

        try(Connection connection  = connectionFactory.getConnection()){
            log.info("success connection on find Flight");

            PreparedStatement statement = connection.prepareStatement(Query.SELECT_FLIGHT_BY_DEPARTURE_AND_DESTINATION);

            statement.setString(1,departure);
            statement.setString(2,destination);

            ResultSet resultSet=statement.executeQuery();

            flight=parse(resultSet);
            log.info("success find Flight  by Departure "+departure+"and destination"+destination+"and date" );

        }catch (SQLException e){
            log.error("error find flight by departure and destination:"+departure+","+destination,e);

        }
        return  flight;
    }

    @Override
    public List<Flight> findAll() {

        log.info("begin find all active Flight ");

        List<Flight> allFlight=null;

        try(Connection connection=connectionFactory.getConnection()){
            log.info("success connection find all Flight");
            PreparedStatement statement=connection.prepareStatement(Query.SELECT_ALL_ACTIVE_FLIGHT);
            ResultSet resultSet=statement.executeQuery();

            allFlight=parse(resultSet);
            log.info("success find all flight");
        }catch (SQLException e){
            log.error("error find all",e);
        }

        return allFlight;
    }


    public void update(Flight updateFlight) {

        log.info("begin update flight"+updateFlight);
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(Query.UPDATE_FLIGHT);

            setFlightInStatement(statement,updateFlight);

            statement.setInt(7,updateFlight.getId());
            statement.executeUpdate();
            log.info("success update flight");
        }catch (SQLException e) {
            log.error("error update flight"+updateFlight);
        }
    }
    @Override
    public void delete(Integer flightId) {

        log.info("begin delete flight by id "+flightId);
        try(Connection connection=connectionFactory.getConnection()){
            log.info("success connection");
            PreparedStatement statement=connection.prepareStatement(Query.DELETE_FLIGHT);
            statement.setInt(1,flightId);

            statement.executeUpdate();
            log.info("success delete flight by id="+flightId);
        }catch (SQLException e){
            log.error("error delete flight by id"+flightId,e);
        }
    }

    private void  setFlightInStatement(PreparedStatement statement,Flight newFlight) throws SQLException {
        statement.setString(1,newFlight.getDeparture());
        statement.setString(2,newFlight.getDestination());
        statement.setDate(3, (Date) newFlight.getDate());
        statement.setDouble(4,newFlight.getStartCost());
        statement.setInt(5,newFlight.getTicketCount());
    }

    private List<Flight> parse(ResultSet resultSet) throws SQLException {
        List<Flight> result = new ArrayList<>();

        while (resultSet.next()) {
            Flight newFlight = new Flight();
            newFlight.setId(resultSet.getInt("flight_id"));
            newFlight.setDate(resultSet.getDate("date"));
            newFlight.setDeparture(resultSet.getString("departure"));
            newFlight.setDestination(resultSet.getString("destination"));
            newFlight.setStartCost(resultSet.getDouble("startcost"));
            newFlight.setTicketCount(resultSet.getInt("ticketCount"));
            result.add(newFlight);
        }
        return result;
    }
}
