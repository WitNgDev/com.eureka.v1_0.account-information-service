/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eureka.v1_0.account.information.service;

import com.eureka.v1_0.account.information.entities.ResetPassword;

/**
 *
 * @author ceowit
 */
public interface ResetPasswordService {
    
    public ResetPassword post(ResetPassword resetPassword) throws Exception;
    
    public ResetPassword getByToken(String accountUid,String resetPasswordToken) throws Exception;
    
    public ResetPassword reset(String accountUid,String resetPasswordToken,String password) throws Exception;
    
    public ResetPassword put(ResetPassword persistedResetPassword) throws Exception;
}
