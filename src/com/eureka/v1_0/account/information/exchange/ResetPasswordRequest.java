/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eureka.v1_0.account.information.exchange;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ceowit
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ResetPasswordRequest {
    
    @XmlElement(required = true)
    private String resetPasswordToken;
    
    @XmlElement(required = true)
    private String accountUid;
    
    @XmlElement(required = true)
    private String password;
    
    /**
     * @return the resetPasswordToken
     */
    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    /**
     * @param resetPasswordToken the resetPasswordToken to set
     */
    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    /**
     * @return the accountUid
     */
    public String getAccountUid() {
        return accountUid;
    }

    /**
     * @param accountUid the accountUid to set
     */
    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
