package com.gjj.igden.service.accountService;

import com.gjj.igden.dao.AccountDao;
import com.gjj.igden.dao.WatchListDescDao;
import com.gjj.igden.dao.daoUtil.DAOException;
import com.gjj.igden.dao.daoimpl.AccountDaoImpl;
import com.gjj.igden.model.Account;
import com.gjj.igden.model.IWatchListDesc;
import com.gjj.igden.model.WatchListDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class AccountService {
  @Autowired
  private AccountDaoImpl accountDaoImpl;

  public List<Account> getAccountList() {
	  return accountDaoImpl.readAll();
  }

  public boolean createAccount(Account account) {
	  try {
		  accountDaoImpl.create(account);
			return true;
		} catch (DAOException e) {
			throw new RuntimeException("Account not created", e.getCause());
		}
  }

  public boolean updateAccount(Account account) {
	  accountDaoImpl.update(account);
		return true;
  }

  public Account retrieveAccount(Long accId) {
	  Account user = new Account();
		user.setId(new Long(accId));
		accountDaoImpl.read(user);
		return user;
  }

  public boolean delete(int id) {
		Account user = new Account();
		user.setId(new Long(id));
		accountDaoImpl.delete(user);
		return true;
  }

  public boolean setImage(long accId, InputStream is) {
    return accountDaoImpl.setImage(accId, is);
  }

  public byte[] getImage(int accId){
    return accountDaoImpl.getImage(accId);
  }
}
