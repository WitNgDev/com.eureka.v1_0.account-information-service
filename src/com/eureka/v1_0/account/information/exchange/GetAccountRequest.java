/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eureka.v1_0.account.information.exchange;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ceowit
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAccountRequest {
    
    private String accountName;
    
    private String accountUid;

    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
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
    
}
