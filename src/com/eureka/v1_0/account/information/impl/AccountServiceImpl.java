/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eureka.v1_0.account.information.impl;

import com.eureka.v1_0.account.information.entities.Account;
import com.eureka.v1_0.account.information.service.AccountService;
import com.wit.ng.aurora.commons.secure.password.service.SecurePasswordService;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ceowit
 */
@Service(value = "accountServiceImpl")
public class AccountServiceImpl implements AccountService {
    
    @PersistenceContext(name = "com.donations.ng.v1_0.account-information-persistence-unit")
    EntityManager em;
    
    @Autowired
    @Qualifier(value = "securePasswordServiceImpl")
    SecurePasswordService securePasswordService;
    
    @Override
    @Transactional
    public Account post(Account account) throws Exception {
        if (account != null) {
            try {
                account.setExternalUid(RandomStringUtils.randomAlphanumeric(128));
                account.setVerificationToken(RandomStringUtils.randomAlphanumeric(128));
                account.setCreationDate(Calendar.getInstance().getTime());
                account.setPassword(this.securePasswordService.createSecurePassword(account.getPassword()));
                account.setVerificationStatus(Boolean.FALSE);
                return account;
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
        throw new NullPointerException("Account object cannot be NULL. Please send a valid object");
    }
    
    @Override
    public Account getByUid(String accountUid) throws Exception {
        List<Account> accounts = this.em.createQuery("select a from Account a where a.externalUid = :uid")
                .setParameter("uid", accountUid)
                .getResultList();
        return accounts != null && !accounts.isEmpty() ? accounts.get(0) : null;
    }
    
    @Override
    public Account getByName(String accountName) throws Exception {
        List<Account> accounts = this.em.createQuery("select a from Account a where a.accountName = :name")
                .setParameter("name", accountName)
                .getResultList();
        return accounts != null && !accounts.isEmpty() ? accounts.get(0) : null;
    }
    
    @Override
    @Transactional
    public Account put(Account persistedAccount) throws Exception {
        try {
            if (persistedAccount != null) {
                this.em.merge(persistedAccount);
                return persistedAccount;
            }
            throw new NullPointerException("Account object cannot be NULL. Please send a valid object");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    @Transactional
    public Boolean delete(String accountName) throws Exception {
        try {
            if (accountName != null) {
                Account persistedAccount = this.getByName(accountName);
                this.em.merge(persistedAccount);
                this.em.remove(persistedAccount);
            }
            throw new NullPointerException("Account name cannot be NULL. Please send a valid object");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
}
