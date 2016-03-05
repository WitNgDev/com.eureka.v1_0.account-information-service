/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eureka.v1_0.account.information.api;

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
import com.wit.ng.aurora.commons.jaxb.marshalling.io.JaxbHandler;
import com.wit.ng.christians.ng.ilogger.api.WitLoggerService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ceowit
 */
@Controller
@RequestMapping(value = "/accounts")
public class AccountInformationController {

    @Autowired
    @Qualifier(value = "accountInformationApiServiceImpl")
    AccountInformationApiService accountInformationApiService;

    @Autowired
    @Qualifier(value = "witLoggerServiceImpl")
    WitLoggerService witLoggerService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest createAccountRequest, HttpServletRequest request, HttpServletResponse response) {
        if (createAccountRequest != null) {
            try {
                CreateAccountResponse createAccountResponse = this.accountInformationApiService.createAccount(createAccountRequest);
                witLoggerService.debug(JaxbHandler.toXml(createAccountRequest));
                if (createAccountResponse != null) {
                    witLoggerService.debug(JaxbHandler.toXml(createAccountResponse));
                    response.setStatus(HttpServletResponse.SC_OK);
                    return createAccountResponse;
                } else {
                    response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                }
            } catch (Exception ex) {
                witLoggerService.warn(ex);
                ex.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE, value = "/resettoken")
    public CreateResetPasswordTokenResponse createResetPasswordToken(@RequestBody CreateResetPasswordTokenRequest createResetPasswordTokenRequest, HttpServletRequest request, HttpServletResponse response) {
        if (createResetPasswordTokenRequest != null) {
            try {
                witLoggerService.debug(JaxbHandler.toXml(createResetPasswordTokenRequest));
                CreateResetPasswordTokenResponse createResetPasswordTokenResponse = this.accountInformationApiService.createResetPasswordToken(createResetPasswordTokenRequest);
                if (createResetPasswordTokenResponse != null) {
                    witLoggerService.debug(JaxbHandler.toXml(createResetPasswordTokenResponse));
                    response.setStatus(HttpServletResponse.SC_OK);
                    return createResetPasswordTokenResponse;
                } else {
                    response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                }
            } catch (Exception ex) {
                witLoggerService.warn(ex);
                ex.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE, value = "/resetpassword")
    public ResetPasswordResponse resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest, HttpServletRequest request, HttpServletResponse response) {
        if (resetPasswordRequest != null) {
            try {
                witLoggerService.debug(JaxbHandler.toXml(resetPasswordRequest));
                ResetPasswordResponse resetPasswordResponse = this.accountInformationApiService.resetPassword(resetPasswordRequest);
                if (resetPasswordResponse != null) {
                    witLoggerService.debug(JaxbHandler.toXml(resetPasswordResponse));
                    response.setStatus(HttpServletResponse.SC_OK);
                    return resetPasswordResponse;
                } else {
                    response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                }
            } catch (Exception ex) {
                witLoggerService.warn(ex);
                ex.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE, value = "/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        if (loginRequest != null) {
            try {
                witLoggerService.debug(JaxbHandler.toXml(loginRequest));
                LoginResponse loginResponse = this.accountInformationApiService.login(loginRequest);
                if (loginResponse != null) {
                    witLoggerService.debug(JaxbHandler.toXml(loginResponse));
                    response.setStatus(HttpServletResponse.SC_OK);
                    return loginResponse;
                } else {
                    response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                }
            } catch (Exception ex) {
                witLoggerService.warn(ex);
                ex.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE, value = "/details")
    public GetAccountResponse getAccounts(@RequestBody GetAccountRequest getAccountRequest, HttpServletRequest request, HttpServletResponse response) {
        if (getAccountRequest != null) {
            try {
                witLoggerService.debug(JaxbHandler.toXml(getAccountRequest));
                GetAccountResponse getAccountResponse = this.accountInformationApiService.getAccountDetails(getAccountRequest);
                if (getAccountResponse != null) {
                    witLoggerService.debug(JaxbHandler.toXml(getAccountResponse));
                    response.setStatus(HttpServletResponse.SC_OK);
                    return getAccountResponse;
                } else {
                    response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                }
            } catch (Exception ex) {
                witLoggerService.warn(ex);
                ex.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }

}
