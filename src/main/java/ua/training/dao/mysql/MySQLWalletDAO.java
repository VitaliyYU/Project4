package ua.training.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.constant.Query;
import ua.training.dao.WalletDAO;
import ua.training.database.ConnectionFactory;
import ua.training.entity.Wallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLWalletDAO implements WalletDAO {
    private  static final Logger log=Logger.getLogger(MySQLWalletDAO.class);

    private ConnectionFactory connectionFactory;

    private static MySQLWalletDAO walletDAO=new MySQLWalletDAO();


    public MySQLWalletDAO(){
        this.connectionFactory=ConnectionFactory.getInstance();
    }

    public  static MySQLWalletDAO getInstance(){
        return walletDAO;
    }
    @Override
    public void create(String code) {
        log.info("begin create wallet with code="+code);
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(Query.INSERT_WALLET);

            statement.setString(1,code);

            statement.executeUpdate();
            log.info("success create  wallet with code"+code);
        }catch (SQLException e){
            log.error("error by creating wallet with code="+code,e);
        }
    }

    @Override
    public Wallet find(String code) {

        Wallet wallet=null;

        log.info("begin find wallet by code"+code);
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(Query.SELECT_WALLET);

            statement.setString(1,code);

            ResultSet resultSet=statement.executeQuery();
            wallet=parse(resultSet).get(0);
            log.info("success find  wallet by code="+code);
        }catch (SQLException e){
            log.error("error find wallet with code="+code,e);
        }catch (IndexOutOfBoundsException e){
            log.error("don`t  find wallet with code"+code,e);
        }
        return wallet;
    }

    @Override
    public List<Wallet> findAll() {
        log.info("begin finding  all wallet");
        List<Wallet> wallets=null;

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(Query.SELECT_ALL_WALLET);
            ResultSet resultSet=statement.executeQuery();
            wallets=parse(resultSet);
            log.info("success find all wallet");
        }catch (SQLException e){
            log.error("error finding all wallet",e);
        }

        return wallets;
    }

    @Override
    public void update(Wallet updateWallet) {
        log.info("begin updating  wallet "+updateWallet);
        try(Connection connection=connectionFactory.getConnection()) {
            PreparedStatement statement=connection.prepareStatement(Query.UPDATE_WALLET);

            statement.setDouble(1,updateWallet.getCount());
            statement.setString(2,updateWallet.getCode());

            statement.executeUpdate();
            log.info("success update wallet "+updateWallet);
        }catch (SQLException e){
            log.error("error update wallet",e);
        }
    }

    @Override
    public void delete(Integer walletId) {
        log.info("begin delete wallet with id="+walletId);
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(Query.DELETE_WALLET);

            statement.setInt(1,walletId);

            statement.executeUpdate();
            log.info("success delete wallet with id="+walletId);
        }catch (SQLException e){
            log.error("error delete wallet with id "+walletId,e);
        }
    }



    private List<Wallet> parse(ResultSet resultSet) throws SQLException {
        List<Wallet> result = new ArrayList<>();

        while (resultSet.next()) {
            Wallet newWallet = new Wallet();
            newWallet.setId(resultSet.getInt("wallet_id"));
            newWallet.setCode(resultSet.getString("code"));
            newWallet.setCount(resultSet.getDouble("count"));
            result.add(newWallet);
        }

        return result;
    }
}
