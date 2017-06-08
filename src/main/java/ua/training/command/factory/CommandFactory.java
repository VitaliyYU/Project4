package ua.training.command.factory;

import ua.training.command.Command;
import ua.training.command.admin.*;
import ua.training.command.authorization.*;
import ua.training.command.flight.*;
import ua.training.command.wallet.AddWallet;
import ua.training.command.wallet.AddWalletRedirection;
import ua.training.constant.Actions;

import javax.servlet.http.HttpServletRequest;


public class CommandFactory {


    private CommandFactory(){}

    public static Command getCommand(HttpServletRequest request){
        Command command=null;
        String commandName= request.getParameter("command");
        try {
            switch (commandName) {
                case Actions.LOGOUT:
                    command=new LogOut();
                    break;
                case Actions.LOGIN_REDIRECT:
                    command=new LogInRedirection();
                    break;
                case Actions.LOGIN:
                    command=new LogIn();
                    break;
                case Actions.ADD_WALLET_REDIRECT:
                    command=new AddWalletRedirection();
                    break;
                case Actions.ADD_WALLET:
                    command=new AddWallet();
                    break;
                case Actions.USER_TICKET:
                    command=new ShowUserTicket();
                    break;
                case Actions.TO_REGISTRATION:
                    command = new RegistrationRedirection();
                    break;
                case Actions.REGISTRATION_COMMAND:
                    command = new Registration();
                    break;
                case Actions.FLIGHT_COMMAND:
                    command=new ShowFlight();
                    break;
                case Actions.REFILL:
                    command=new RefillWallet();
                    break;
                case Actions.TICKETS:
                    command=new ShowTicket();
                    break;
                case Actions.CONFIRM:
                    command=new Confirm();
                    break;
                case Actions.BUY_TICKET_COMMAND:
                    command=new BuyTicket();
                    break;
                case Actions.SEARCH:
                    command=new Search();
                    break;
                case Actions.SHOW_USER:
                    command=new ShowUsers();
                    break;
                case Actions.DELETE_USER:
                    command=new DeleteUser();
                    break;
                case Actions.ADD_FLIGHT_REDIRECTION:
                    command=new AddFlightRedirection();
                    break;
                case Actions.ADD_FLIGHT:
                    command=new AddFlight();
                    break;
                case Actions.REFILL_REDIRECT:
                    command= new RefilRedirect();
                    break;
                case Actions.SHOW_FLIGHT:
                    command=new ShowFlight();
                    break;
                case Actions.ChangeLanguage:
                    command=new ChangeLanguage();
                    break;
                default:
                    command = new ErrorCommand();
            }
        }catch (NullPointerException e){
            command=new ErrorCommand();
        }
        return command;
    }
}
