package ua.training.command.factory;

import ua.training.command.Command;
import ua.training.command.admin.*;
import ua.training.command.authorization.LogIn;
import ua.training.command.authorization.Registration;
import ua.training.command.authorization.RegistrationRedirection;
import ua.training.command.flight.*;
import ua.training.command.wallet.AddWallet;
import ua.training.command.wallet.AddWalletRedirection;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vitaliy on 23.05.17.
 */
public class CommandFactory {

    final static String AUTHORIZATION_COMMAND="login";
    final static String REGISTRATION_COMMAND="registration";
    final static String FLIGHT_COMMAND="flights";
    final static String BUY_TICKET_COMMAND="buy";
    final static String CONFIRM_TO_BUY_TICKET_COMMAND="confirm";
    final static String TO_REGISTRATION="toRegister";
    final static String TICKETS="tickets";
    final static String CONFIRM="confirm";
    final static String SEARCH="search";
    final static String SHOW_USER="showUser";
    final static String DELETE_USER="deleteUser";
    final static String ADD_FLIGHT_REDIRECTION="addFlightRedirection";
    final static String ADD_FLIGHT="addFlight";
    final static  String REFILL="refill";
    final  static String REFILL_REDIRECT="refillRedirect";
    final static String SHOW_FLIGHT="showFlight";
    final static String USER_TICKET="userTicket";
    final static String ADD_WALLET="addWallet";
    final static String ADD_WALLET_REDIRECT="addWalletRedirection";

    private CommandFactory(){}

    public static Command getCommand(HttpServletRequest request){
        Command command=null;
        String commandName= request.getParameter("command");
        try {
            switch (commandName) {
                case ADD_WALLET_REDIRECT:
                    command=new AddWalletRedirection();
                    break;
                case ADD_WALLET:
                    command=new AddWallet();
                    break;
                case USER_TICKET:
                    command=new ShowUserTicket();
                    break;
                case TO_REGISTRATION:
                    command = new RegistrationRedirection();
                    break;
                case REGISTRATION_COMMAND:
                    command = new Registration();
                    break;
                case FLIGHT_COMMAND:
                    command=new ShowFlight();
                    break;
                case REFILL:
                    command=new RefillWallet();
                    break;
                case TICKETS:
                    command=new ShowTicket();
                    break;
                case CONFIRM:
                    command=new Confirm();
                    break;
                case BUY_TICKET_COMMAND:
                    command=new BuyTicket();
                    break;
                case SEARCH:
                    command=new Search();
                    break;
                case SHOW_USER:
                    command=new ShowUsers();
                    break;
                case DELETE_USER:
                    command=new DeleteUser();
                    break;
                case ADD_FLIGHT_REDIRECTION:
                    command=new AddFlightRedirection();
                    break;
                case ADD_FLIGHT:
                    command=new AddFlight();
                    break;
                case REFILL_REDIRECT:
                    command= new RefilRedirect();
                    break;
                case SHOW_FLIGHT:
                    command=new ShowFlight();
                    break;
                default:
                    command = new LogIn();
            }
        }catch (NullPointerException e){
            command=new LogIn();
        }catch (NumberFormatException e){
            command=new LogIn();
        }
        return command;
    }
}
