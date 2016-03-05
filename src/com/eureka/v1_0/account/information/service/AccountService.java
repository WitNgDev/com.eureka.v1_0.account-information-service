/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eureka.v1_0.account.information.service;

import com.eureka.v1_0.account.information.entities.Account;

/**
 *
 * @author ceowit
 */
public interface AccountService {
    
    public Account post(Account account) throws Exception;
    
    public Account getByUid(String accountUid) throws Exception;
    
    public Account getByName(String accountName) throws Exception;
    
    public Account put(Account persistedAccount) throws Exception;
    
    public Boolean delete(String accountName) throws Exception;
    
}
