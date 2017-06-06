package ua.training.service;

import ua.training.dao.UserDAO;
import ua.training.dao.WalletDAO;
import ua.training.dao.mysql.MySQLUserDAO;
import ua.training.dao.mysql.MySQLWalletDAO;
import ua.training.entity.User;
import ua.training.entity.Wallet;

import java.util.List;

public  class UserService {
    private UserDAO userDAO;
    private WalletDAO walletDAO;
    private static UserService userService=new UserService();

    private UserService(){
        userDAO= MySQLUserDAO.getInstance();
        walletDAO= MySQLWalletDAO.getInstance();
    }
    public static UserService getInstance(){
        return userService;
    }

    public void addUser(User user){
        userDAO.create(user);
    }
    public void update(User user){
        userDAO.update(user);
    }
    public Wallet findWalletByCode(String code){
       return walletDAO.findByCode(code);
    }
    public User findUserByLogin(String login){
        return userDAO.findByLogin(login);
    }

    public List<User> findAll(){
        return userDAO.findAll();
    }
    public List<Wallet>findAllWallets(){
        return walletDAO.findAll();
    }
    public void deleteUser(Integer id){
        userDAO.delete(id);
    }
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
        Wallet wallet=walletDAO.findByCode(code);
        wallet.setCount(count);
        walletDAO.update(wallet);
    }
    public User verifyUser(String login ,String password){
        User user=userDAO.findByLoginAndPassword(login,password);
        return user;
    }

}
