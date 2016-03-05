/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eureka.v1_0.account.information.exchange.api;

import com.eureka.v1_0.account.information.exchange.CreateAccountRequest;
import com.eureka.v1_0.account.information.exchange.CreateAccountResponse;
import com.eureka.v1_0.account.information.exchange.CreateResetPasswordTokenRequest;
import com.eureka.v1_0.account.information.exchange.CreateResetPasswordTokenResponse;
import com.eureka.v1_0.account.information.exchange.GetAccountRequest;
import com.eureka.v1_0.account.information.exchange.GetAccountResponse;
import com.eureka.v1_0.account.information.exchange.LoginRequest;
import com.eureka.v1_0.account.information.exchange.LoginResponse;
import com.eureka.v1_0.account.information.exchange.ResetPasswordRequest;
import com.eureka.v1_0.account.information.exchange.ResetPasswordResponse;

/**
 *
 * @author ceowit
 */
public interface AccountInformationApiService {
    
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) throws Exception;
    
    public CreateResetPasswordTokenResponse createResetPasswordToken(CreateResetPasswordTokenRequest createResetPasswordTokenRequest) throws Exception;
    
    public ResetPasswordResponse resetPassword(ResetPasswordRequest resetPasswordRequest) throws Exception;
    
    public LoginResponse login(LoginRequest loginRequest) throws Exception;
    
    public GetAccountResponse getAccountDetails(GetAccountRequest getAccountRequest) throws Exception;
    
}
