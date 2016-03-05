/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eureka.v1_0.account.information.exchange.impl;

import com.eureka.v1_0.account.information.entities.Account;
import com.eureka.v1_0.account.information.entities.Login;
import com.eureka.v1_0.account.information.entities.ResetPassword;
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
import com.eureka.v1_0.account.information.exchange.api.AccountInformationApiService;
import com.eureka.v1_0.account.information.exchange.exceptions.AccountInformationApiException;
import com.eureka.v1_0.account.information.service.AccountService;
import com.eureka.v1_0.account.information.service.AuthenticationService;
import com.eureka.v1_0.account.information.service.ResetPasswordService;
import com.wit.ng.aurora.commons.jaxb.marshalling.io.JaxbHandler;
import com.wit.ng.christians.ng.ilogger.api.WitLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author ceowit
 */
@Service(value = "accountInformationApiServiceImpl")
public class AccountInformationApiServiceImpl implements AccountInformationApiService {

    @Autowired
    @Qualifier(value = "accountServiceImpl")
    AccountService accountService;

    @Autowired
    @Qualifier(value = "resetPasswordServiceImpl")
    ResetPasswordService resetPasswordService;

    @Autowired
    @Qualifier(value = "authenticationServiceImpl")
    AuthenticationService authenticationService;

    @Autowired
    @Qualifier(value = "witLoggerServiceImpl")
    WitLoggerService witLoggerService;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) throws Exception {
        if (createAccountRequest != null) {
            witLoggerService.debug(JaxbHandler.toXml(createAccountRequest));
            if (createAccountRequest.getPassword().equals(createAccountRequest.getConfirmPassword())) {
                Account account = new Account();
                account.setAccountName(createAccountRequest.getAccountName());
                account.setPassword(createAccountRequest.getPassword());
                Account persistedAccount = this.accountService.post(account);
                if (persistedAccount != null) {
                    CreateAccountResponse createAccountResponse = new CreateAccountResponse();
                    createAccountResponse.setAccount(this.accountService.getByName(createAccountRequest.getAccountName()));
                    return createAccountResponse;
                }
            }
        }
        throw new AccountInformationApiException("Unable to complete account creation process .. Please try again");
    }

    @Override
    public CreateResetPasswordTokenResponse createResetPasswordToken(CreateResetPasswordTokenRequest createResetPasswordTokenRequest) throws Exception {
        if (createResetPasswordTokenRequest != null) {
            ResetPassword resetPassword = new ResetPassword();
            resetPassword.setAccount(new Account());
            resetPassword.getAccount().setAccountName(createResetPasswordTokenRequest.getAccountName());
            ResetPassword persistedResetPassword = this.resetPasswordService.post(resetPassword);
            if (persistedResetPassword != null) {
                CreateResetPasswordTokenResponse createResetPasswordTokenResponse = new CreateResetPasswordTokenResponse();
                createResetPasswordTokenResponse.setResetPassword(persistedResetPassword);
                return createResetPasswordTokenResponse;
            }
        }
        throw new AccountInformationApiException("Failure to create account reset password token .. Please try again");
    }

    @Override
    public ResetPasswordResponse resetPassword(ResetPasswordRequest resetPasswordRequest) throws Exception {
        if (resetPasswordRequest != null) {
            witLoggerService.debug(JaxbHandler.toXml(resetPasswordRequest));
            ResetPassword persistedResetPassword = this.resetPasswordService.reset(resetPasswordRequest.getAccountUid(), resetPasswordRequest.getResetPasswordToken(), resetPasswordRequest.getPassword());
            if (persistedResetPassword != null) {
                ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
                resetPasswordResponse.setResetPassword(persistedResetPassword);
                return resetPasswordResponse;
            }
        }
        throw new AccountInformationApiException("Failed to reset password .. Please try again later.");
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        if (loginRequest != null) {
            witLoggerService.debug(JaxbHandler.toXml(loginRequest));
            Login login = new Login();
            login.setAccount(this.accountService.getByName(loginRequest.getAccountName()));
            login.setIpAddress(loginRequest.getIpAddress());
            login.setChannel(loginRequest.getChannel());
            Login persistedLogin = this.authenticationService.post(login);
            if (persistedLogin != null) {
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setLogin(persistedLogin);
                return loginResponse;
            }
        }
        throw new AccountInformationApiException("Failed to login .. Please try again later.");
    }

    @Override
    public GetAccountResponse getAccountDetails(GetAccountRequest getAccountRequest) throws Exception {
        if (getAccountRequest != null) {
            Account persistedAccount = this.accountService.getByName(getAccountRequest.getAccountName());
            if (persistedAccount != null) {
                GetAccountResponse getAccountResponse = new GetAccountResponse();
                getAccountResponse.setAccount(persistedAccount);
                return getAccountResponse;
            }
        }
        throw new AccountInformationApiException("Failed to get account details .. Please try again later.");
    }

}
