package ua.training.service;

import ua.training.dao.UserDAO;
import ua.training.dao.WalletDAO;
import ua.training.dao.mysql.MySQLDAOFactory;
import ua.training.entity.User;
import ua.training.entity.Wallet;

import java.util.List;

public  class UserService {
    private UserDAO userDAO;
    private WalletDAO walletDAO;
    private static UserService userService=new UserService();

    private UserService(){
        userDAO= MySQLDAOFactory.getMySQLUserDAO();
        walletDAO= MySQLDAOFactory.getMySQLWalletDAO();
    }
    public static UserService getInstance(){
        return userService;
    }

    /**
     *
     * @param user new  user which we need to set to db
     */
    public void addUser(User user){
        userDAO.create(user);
    }

    /**
     *
     * @param user user which we need to updatein db
     */
    public void update(User user){
        userDAO.update(user);
    }

    /**
     *
     * @param code wallet code which  we need to find
     * @return wallet where wallet_code={@code}
     */
    public Wallet findWalletByCode(String code){
       return walletDAO.find(code);
    }

    /**
     *@return all active user
     */
    public List<User> findAll(){
        return userDAO.findAll();
    }

    /**
     *
     * @return all active wallets
     */
    public List<Wallet>findAllWallets(){
        return walletDAO.findAll();
    }
    public void deleteUser(Integer id){
        userDAO.delete(id);
    }

    /**
     *
     * @param user user who pay for  ticket
     * @param cost ticket total cost
     * @return if user has count in wallet begger than {@cost}
     */
    public  boolean pay(User user, Double cost){
        boolean flag=user.withdraw(cost);
        if(flag) {
            userDAO.update(user);
        }else return false;
        return flag;
    }
    public void createWallet(String code){
        walletDAO.create(code);
    }

    public void reffil(String code,Double  count){
        Wallet wallet=walletDAO.find(code);
        wallet.setCount(count);
        walletDAO.update(wallet);
    }

    public User verifyUser(String login ,String password){
        User user=userDAO.findByLoginAndPassword(login,password);
        return user;
    }

}
