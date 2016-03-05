/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eureka.v1_0.account.information.impl;

import com.eureka.v1_0.account.information.entities.Login;
import com.eureka.v1_0.account.information.service.AccountService;
import com.eureka.v1_0.account.information.service.AuthenticationService;
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
@Service(value = "loginServiceImpl")
public class AuthenticationServiceImpl implements AuthenticationService {

    @PersistenceContext(name = "com.donations.ng.v1_0.account-information-persistence-unit")
    EntityManager em;

    @Autowired
    @Qualifier(value = "accountServiceImpl")
    AccountService accountService;

    @Override
    @Transactional
    public Login post(Login login) throws Exception {
        if (login != null) {
            try {
                login.setAccount(this.accountService.getByName(login.getAccount().getAccountName()));
                login.setExternalUid(RandomStringUtils.randomAlphanumeric(128));
                login.setLoginDate(Calendar.getInstance().getTime());
                login.setSessionUid(RandomStringUtils.randomAlphanumeric(128));
                this.em.persist(login);
                return login;
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
        throw new NullPointerException("Login cannot be NULL. Please send a valid object");
    }

    @Override
    public Login getCurrent(String accountUid) throws Exception {
        List<Login> logins = this.em.createQuery("select l from Login l where l.account.externalUid = :uid order by l.loginDate desc")
                .setParameter("uid", accountUid)
                .getResultList();
        return logins != null && !logins.isEmpty() ? logins.get(0) : null;
    }

    @Override
    public Login validate(String sessionUid, String accountUid) throws Exception {
        List<Login> logins = this.em.createQuery("select l from Login l where l.sessionUid = :uid and l.account.externalUid = :account order by l.loginDate desc")
                .setParameter("account", accountUid)
                .setParameter("uid", sessionUid)
                .getResultList();
        return logins != null && !logins.isEmpty() ? logins.get(0) : null;
    }

    @Override
    @Transactional
    public Boolean logout(String sessionUid, String accountUid) throws Exception {
        Login persistedLogin = this.validate(sessionUid, accountUid);
        if (persistedLogin != null) {
            try {
                persistedLogin.setLogOutDate(Calendar.getInstance().getTime());
                this.em.merge(persistedLogin);
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
        return false;
    }

}
