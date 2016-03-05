/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eureka.v1_0.account.information.exchange;

import com.eureka.v1_0.account.information.entities.ResetPassword;
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
public class ResetPasswordResponse {
    
    @XmlElement(required = true)
    private ResetPassword resetPassword;

    /**
     * @return the resetPassword
     */
    public ResetPassword getResetPassword() {
        return resetPassword;
    }

    /**
     * @param resetPassword the resetPassword to set
     */
    public void setResetPassword(ResetPassword resetPassword) {
        this.resetPassword = resetPassword;
    }
    
}
