package ua.training.dao;

import ua.training.entity.Wallet;

import java.util.List;

/**
 * Created by vitaliy on 23.05.17.
 */
public interface WalletDAO {
    void create(String code);
    Wallet findByCode(String code);
    List<Wallet> findAll();
    void update(Wallet updateWallet);
    void delete(Integer id);
}
