/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eureka.v1_0.account.information.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ceowit
 */
@Entity
@Table(catalog = "donations_ng",schema = "_donations_ng_user_accounts_module",name = "_donations_ng_user_login")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Login implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "externalUid")
    private Account account;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date logOutDate;    
    
    @Column(nullable = false)
    private String channel;
    
    @Column(nullable = false)
    private String ipAddress;
    
    @Column(nullable = true,unique = false)
    private String sessionUid;
    
    @Column(nullable = true,unique = false)
    private String externalUid;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.donations.ng.v1_0.account.information.entities.Login[ id=" + getId() + " ]";
    }

    /**
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * @return the loginDate
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * @param loginDate the loginDate to set
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * @return the sessionUid
     */
    public String getSessionUid() {
        return sessionUid;
    }

    /**
     * @param sessionUid the sessionUid to set
     */
    public void setSessionUid(String sessionUid) {
        this.sessionUid = sessionUid;
    }

    /**
     * @return the externalUid
     */
    public String getExternalUid() {
        return externalUid;
    }

    /**
     * @param externalUid the externalUid to set
     */
    public void setExternalUid(String externalUid) {
        this.externalUid = externalUid;
    }

    /**
     * @return the logOutDate
     */
    public Date getLogOutDate() {
        return logOutDate;
    }

    /**
     * @param logOutDate the logOutDate to set
     */
    public void setLogOutDate(Date logOutDate) {
        this.logOutDate = logOutDate;
    }

    /**
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
}
