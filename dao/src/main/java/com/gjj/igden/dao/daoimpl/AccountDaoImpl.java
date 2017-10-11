package com.gjj.igden.dao.daoimpl;

import com.gjj.igden.dao.AbstractDAO;
import com.gjj.igden.dao.daoUtil.DAOException;
import com.gjj.igden.model.Account;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
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

	@Override
    public void create(Account account) throws DAOException {
        super.create(account);
        /*insertAvatar(account, getDefaultAvatar());*/
    }

    @Override
    public Account read(Account account) {
            return em.find(Account.class, account.getId());
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


    public byte[] getDefaultAvatar() throws DAOException {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("default.jpg")) {
            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
    
    public void delete(Account id) {
       super.delete(id);
    }

    
    public void update(Account acc) {
    	super.update(acc);
    }

	public boolean setImage(long accId, InputStream is) {
		return false;
		
	}

	public byte[] getImage(int accId) {
		return null;
		
	}
}