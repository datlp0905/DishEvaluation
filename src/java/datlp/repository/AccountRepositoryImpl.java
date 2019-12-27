/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.repository;

import datlp.entity.Account;

/**
 *
 * @author DATLPSE62823
 */
public class AccountRepositoryImpl
        extends BaseRepositoryImpl<Account, String>
        implements AccountRepository {

    public AccountRepositoryImpl() {
        super(Account.class);
    }

}
