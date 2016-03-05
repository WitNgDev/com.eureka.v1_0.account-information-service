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
@Table(catalog = "donations_ng",schema = "_donations_ng_user_accounts_module",name = "_donations_ng_reset_password")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ResetPassword implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "externalUid")
    private Account account;
    
    @Column(nullable = false,unique = true)
    private String resetPasswordToken;
    
    private Boolean resetStatus;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    
    @Column(nullable = false,unique = true)
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
        if (!(object instanceof ResetPassword)) {
            return false;
        }
        ResetPassword other = (ResetPassword) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.donations.ng.v1_0.account.information.entities.ResetPassword[ id=" + getId() + " ]";
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
     * @return the resetStatus
     */
    public Boolean getResetStatus() {
        return resetStatus;
    }

    /**
     * @param resetStatus the resetStatus to set
     */
    public void setResetStatus(Boolean resetStatus) {
        this.resetStatus = resetStatus;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
    
}
