/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eureka.v1_0.account.information.service;

import com.eureka.v1_0.account.information.entities.Login;

/**
 *
 * @author ceowit
 */
public interface AuthenticationService {
    
    public Login post(Login login) throws Exception;
    
    public Login getCurrent(String accountUid) throws Exception;
    
    public Login validate(String sessionUid,String accountUid) throws Exception;
    
    public Boolean logout(String sessionUid,String accountUid) throws Exception;
}
