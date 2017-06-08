package ua.training.constant;


public interface Query {
    String INSERT_FLIGHT = "Insert into flight(departure,destination,date,startcost,ticketCount) " +
            "values(?,?,?,?,?) ";
    String SELECT_FLIGHT_BY_ID = "Select * From flight where departure=? and destination=? and date=?  and status=0";
    String SELECT_FLIGHT_BY_DEPARTURE_AND_DESTINATION="Select * From flight " +
            "where departure=? and destination=?   and status=0";
    String SELECT_ALL_ACTIVE_FLIGHT="select * from flight where status=0";
    String UPDATE_FLIGHT="Update flight " +
            "SET departure=?,destination=?,date=?,startcost=?,flight_duration=?,flight_code=? " +
            "where flight_id=?";
    String DELETE_FLIGHT="Update flight set status=1 where flight_id=?";
    String INSERT_TICKET="insert into ticket " +
            "(place ,user_id , flight_id, baggage,  priority_registration, priority_boarding) " +
            "values(?,?,?,?,?,?)";
    String CREATE_TICKET="insert into ticket " +
            "(place , flight_id) " +
            "values(?,?)";
    String SELECT_TICKET_BY_USER_ID="select * from (ticket inner join flight on ticket.flight_id=flight.flight_id) " +
            "where user_id=? and flight.status=0";
    String COUNT_AVAIBLE_TICKET="select count(ticket_id)  from ticket where status=0";
    String UPDATE_TICKET="Update ticket SET place=? ,baggage=?,priority_boarding=?,priority_registration=?,user_id=? " +
            "where ticket_id=?";
    String DELETE_TICKET="Update ticket set status=1 where ticket_id=?";
    String INSERT_USER="Insert into user " +
            "(login ,password ,name, surname)" +
            " values(?,?,?,?)";
    String SELECT_USER_BY_LOGIN_AND_PASSWORD="Select * from" +
            "((user left join wallet on user.wallet_id=wallet.wallet_id)" +
            "left join user_role on user.role_id=user_role.role_id ) " +
            "where login=? and password=?  and user.status=0";
    String SELECT_ALL_USER="select * from ((user left join wallet on user.wallet_id=wallet.wallet_id)" +
            "left join user_role on user.role_id=user_role.role_id )" +
            " where user.status=0";

    String UPDATE_USER="Update user SET login=? , name=?,surname=?,wallet_id=? where user_id=?";
    String DELETE_USER="Update user set status=1 where user_id=?";

    String INSERT_USER_ROLE="insert into user_role(role_name) values(?)";
    String SELECT_USER_ROLE="select * from role where status=0";
    String UPDATE_USER_ROLE="Update role set role_name=? where role_id=?";
    String DELETE_USER_ROLE="Update role set status=1 where role_id=?";
    String INSERT_WALLET="insert into wallet(code) values(?)";
    String SELECT_WALLET="select * from wallet where code=? and status=0";
    String SELECT_ALL_WALLET="select * from wallet where status=0";
    String UPDATE_WALLET="Update wallet set count=? where code=?";
    String DELETE_WALLET="Update wallet set status=1 where wallet_id=?";
    String SELECT_TICKET_BY_FLIGHT_ID="select * from ticket where status=0 and flight_id=?";


}