/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eureka.v1_0.account.information.impl;

import com.eureka.v1_0.account.information.entities.Account;
import com.eureka.v1_0.account.information.entities.ResetPassword;
import com.eureka.v1_0.account.information.service.AccountService;
import com.eureka.v1_0.account.information.service.ResetPasswordService;
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
@Service(value = "resetPasswordServiceImpl")
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @PersistenceContext(name = "com.donations.ng.v1_0.account-information-persistence-unit")
    EntityManager em;

    @Autowired
    @Qualifier(value = "accountServiceImpl")
    AccountService accountService;

    @Autowired
    @Qualifier(value = "securePasswordServiceImpl")
    SecurePasswordService securePasswordService;

    @Override
    @Transactional
    public ResetPassword post(ResetPassword resetPassword) throws Exception {
        if (resetPassword != null) {
            try {
                resetPassword.setAccount(this.accountService.getByName(resetPassword.getAccount().getAccountName()));
                resetPassword.setResetPasswordToken(RandomStringUtils.randomAlphanumeric(128));
                resetPassword.setResetStatus(Boolean.FALSE);
                resetPassword.setStartDate(Calendar.getInstance().getTime());
                this.em.persist(resetPassword);
                return resetPassword;
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
        throw new NullPointerException("ResetPassword object cannot be NULL. Please send a valid object");
    }

    @Override
    public ResetPassword getByToken(String accountUid, String resetPasswordToken) throws Exception {
        List<ResetPassword> resetPassword = this.em.createQuery("select r from ResetPassword r where r.account.externalUid = :uid and r.resetPasswordToken = :token")
                .setParameter("uid", accountUid).setParameter("token", resetPasswordToken).getResultList();
        return resetPassword != null && !resetPassword.isEmpty() ? resetPassword.get(0) : null;
    }

    @Override
    @Transactional
    public ResetPassword reset(String accountUid, String resetPasswordToken, String password) throws Exception {
        ResetPassword persistedResetPassword = this.getByToken(accountUid, resetPasswordToken);
        if (persistedResetPassword != null && !persistedResetPassword.getResetStatus()) {
            Account persistedAccount = this.accountService.getByUid(accountUid);
            persistedAccount.setPassword(securePasswordService.createSecurePassword(password));
            this.accountService.put(persistedAccount);
            persistedResetPassword.setResetStatus(Boolean.TRUE);
            persistedResetPassword.setEndDate(Calendar.getInstance().getTime());
        }
        throw new NullPointerException("ResetPassword object cannot be NULL. Please send a valid object");
    }

    @Override
    @Transactional
    public ResetPassword put(ResetPassword persistedResetPassword) throws Exception {
        if (persistedResetPassword != null) {
            try {
                this.em.merge(persistedResetPassword);
                return persistedResetPassword;
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
        throw new NullPointerException("ResetPassword object cannot be NULL. Please send a valid object");
    }

}
