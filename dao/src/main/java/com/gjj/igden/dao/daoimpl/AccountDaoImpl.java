package com.gjj.igden.dao.daoimpl;

import com.gjj.igden.dao.AbstractDAO;
import com.gjj.igden.dao.daoUtil.DAOException;
import com.gjj.igden.model.Account;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
@Transactional
public class AccountDaoImpl extends AbstractDAO<Account> {

    public void create(Account account) throws DAOException {
        super.create(account);
        /*insertAvatar(account, getDefaultAvatar());*/
    }

    @Override
    public Account read(Account account) {
        return (Account) em.createQuery("from Account where account_id = "+account.getId()).getSingleResult();
    }

    @Override
    public List<Account> readAll() {
    	BarDaoImpl b = new BarDaoImpl();
        return em.createQuery("from Account").getResultList();
    }

    private CriteriaQuery<Account> getAccountCriteriaQuery() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        Root<Account> root = cq.from((Account.class));
        cq.select(root);
        return cq;
    }
    
    public void delete(Account account) throws DAOException {
    	System.out.println("AccountDaoImpl.delete()");
    	account = read(account);
    	account.setAvatar(null);
    	account.setDataSets(null);
    	super.delete(account);
    	/*
    	if(null != account) {
    		em.createQuery("DELETE from Account where id="+account.getId()).executeUpdate();
    	} else {
    		throw new DAOException("account not found!");
    	}*/
    		
    }

    public void update(Account account) {
    	super.update(account);
    }

	public boolean setImage(long accId, InputStream is) {
		return false;
		
	}

	public byte[] getImage(int accId) {
		return null;
		
	}
}