package ua.training.dao.mysql;

import ua.training.dao.FlightDAO;
import ua.training.database.ConnectionFactory;
import ua.training.entity.Flight;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by vitaliy on 21.05.17.
 */
public class MySQLFlightDAO implements FlightDAO {

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
        String insertFlight="Insert into flight(departure,destination,date,startcost,ticketCount) " +
                                "values(?,?,?,?,?) ";
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(insertFlight);

            setFlightInStatement(statement, newFlight);
            statement.executeUpdate();
        }catch (SQLException e){
            //logging
        }
    }

    @Override
    public Flight findById(Integer flightId) {
        String selectFlightById="Select * From flight where id=? and status=0";
        Flight flight=null;

        try(Connection connection  = connectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(selectFlightById);
            statement.setInt(1,flightId);
            ResultSet resultSet=statement.executeQuery();

            flight=writeFlightToListFromResultSet(resultSet).get(0);
        }catch (SQLException e){
            //logging
        }

        return  flight;
    }

    @Override
    public List<Flight> findByDepartureAndDestinationAndDate(String departure, String destination, Date date) {
        String selectFlightById="Select * From flight where departure=? and destination=? and date=?  and status=0";
        List<Flight> flight=null;
        try(Connection connection  = connectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(selectFlightById);
            statement.setString(1,departure);
            statement.setString(2,destination);
            statement.setDate(3,date);
            ResultSet resultSet=statement.executeQuery();
            flight=writeFlightToListFromResultSet(resultSet);
        }catch (SQLException e){
            //logging
        }
        return  flight;
    }

    @Override
    public List<Flight> findByDepartureAndDestination(String departure, String destination) {
        String selectFlightById="Select * From flight where departure=? and destination=?   and status=0";
        List<Flight> flight=null;
        try(Connection connection  = connectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(selectFlightById);
            statement.setString(1,departure);
            statement.setString(2,destination);

            ResultSet resultSet=statement.executeQuery();
            flight=writeFlightToListFromResultSet(resultSet);
        }catch (SQLException e){
            //logging
        }
        return  flight;
    }

    @Override
    public List<Flight> findAll() {
        String selectAllFlight="select * from flight where status=0";
        List<Flight> allFlight=null;

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(selectAllFlight);
            ResultSet resultSet=statement.executeQuery();

            allFlight=writeFlightToListFromResultSet(resultSet);
        }catch (SQLException e){
            //logging
        }

        return allFlight;
    }

    @Override
    public Flight findByCode(String code) {
        String selectFlightById="Select * From flight where code=? and status=0";
        Flight flight=null;

        try(Connection connection  = connectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(selectFlightById);
            statement.setString(1,code);
            ResultSet resultSet=statement.executeQuery();

            flight=writeFlightToListFromResultSet(resultSet).get(0);
        }catch (SQLException e){
            //logging
        }

        return  flight;

    }

    public void update(Flight updateFlight) {
        String updateFlightStatement="Update flight SET departure=?,destination=?,date=?,startcost=?,flight_duration=?,flight_code=? where flight_id=?";

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(updateFlightStatement);

            setFlightInStatement(statement,updateFlight);
            statement.setInt(7,updateFlight.getId());
            statement.executeUpdate();
        }catch (SQLException e) {
            //logging
        }
    }
    @Override
    public void delete(Integer flightId) {
        String deleteStatement="Update flight set status=1 where flight_id=?";

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(deleteStatement);
            statement.setInt(1,flightId);

            statement.executeUpdate();
        }catch (SQLException e){
            //logging
        }
    }

    private void  setFlightInStatement(PreparedStatement statement,Flight newFlight) throws SQLException {
        statement.setString(1,newFlight.getDeparture());
        statement.setString(2,newFlight.getDestination());
        statement.setDate(3, (Date) newFlight.getDate());
        statement.setDouble(4,newFlight.getStartCost());
        statement.setInt(5,newFlight.getTicketCount());
    }

    private List<Flight> writeFlightToListFromResultSet(ResultSet resultSet) throws SQLException {
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
