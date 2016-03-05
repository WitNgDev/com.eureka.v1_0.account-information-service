/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eureka.v1_0.account.information.exchange.exceptions;

/**
 *
 * @author ceowit
 */
public class AccountInformationApiException extends Exception{

    private String message;
    
    public AccountInformationApiException() {
    }

    public AccountInformationApiException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
}
