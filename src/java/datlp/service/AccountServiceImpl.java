/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.service;

import datlp.entity.Account;
import datlp.repository.AccountRepository;
import datlp.repository.AccountRepositoryImpl;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DATLPSE62823
 */
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    public AccountServiceImpl() {
        accountRepository = new AccountRepositoryImpl();
    }
    
    
    @Override
    public Account checkLogin(String username, String password) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", username);
        parameters.put("password", password);
        
        Account account = accountRepository.find("Account.checkLogin", parameters);
        return account;
    }
    
}
