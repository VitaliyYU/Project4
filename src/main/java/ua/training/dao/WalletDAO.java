package ua.training.dao;

import ua.training.entity.Wallet;

import java.util.List;


public interface WalletDAO {
    void create(String code);
    Wallet find(String code);
    List<Wallet> findAll();
    void update(Wallet updateWallet);
    void delete(Integer id);
}
