package ua.training.dao.mysql;

import ua.training.dao.WalletDAO;
import ua.training.database.ConnectionFactory;
import ua.training.entity.Wallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaliy on 21.05.17.
 */
public class MySQLWalletDAO implements WalletDAO {
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
        String insertWallet="insert into wallet(code) values(?)";

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(insertWallet);
            statement.setString(1,code);
            statement.executeUpdate();
        }catch (SQLException e){
            //logging
        }
    }

    @Override
    public Wallet findByCode(String code) {
        String selectWalletByIdStatment="select * from wallet where code=? and status=0";
        Wallet wallet=null;
        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(selectWalletByIdStatment);
            statement.setString(1,code);
            ResultSet resultSet=statement.executeQuery();
            wallet=writeWalletToListFromResultSet(resultSet).get(0);
        }catch (SQLException e){
            //logging
        }
        return wallet;
    }

    @Override
    public List<Wallet> findAll() {
        String  selectAllWallet="select * from wallet where status=0";
        List<Wallet> wallets=null;

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(selectAllWallet);
            ResultSet resultSet=statement.executeQuery();
            wallets=writeWalletToListFromResultSet(resultSet);
        }catch (SQLException e){
            //logging
        }

        return wallets;
    }

    @Override
    public void update(Wallet updateWallet) {
        String updateStatment="Update wallet set count=? where code=?";

        try(Connection connection=connectionFactory.getConnection()) {
            PreparedStatement statement=connection.prepareStatement(updateStatment);
            statement.setDouble(1,updateWallet.getCount());
            statement.setString(2,updateWallet.getCode());
            statement.executeUpdate();
        }catch (SQLException e){
            //logging
        }
    }

    @Override
    public void delete(Integer walletId) {
        String deleteStatement="Update wallet set status=1 where wallet_id=?";

        try(Connection connection=connectionFactory.getConnection()){
            PreparedStatement statement=connection.prepareStatement(deleteStatement);
            statement.setInt(1,walletId);

            statement.executeUpdate();
        }catch (SQLException e){
            //logging
        }
    }



    private List<Wallet> writeWalletToListFromResultSet(ResultSet resultSet) throws SQLException {
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
