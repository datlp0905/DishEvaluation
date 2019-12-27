/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.service;

import datlp.entity.Account;

/**
 *
 * @author DATLPSE62823
 */
public interface AccountService {
    
    Account checkLogin(String username, String password);
}
