package com.gjj.igden.service.accountService;

import com.gjj.igden.dao.WatchListDescDao;
import com.gjj.igden.dao.daoUtil.DAOException;
import com.gjj.igden.dao.daoimpl.AccountDaoImpl;
import com.gjj.igden.dao.daoimpl.AvatarDaoImpl;
import com.gjj.igden.dao.daoimpl.WatchListDescDaoImpl;
import com.gjj.igden.model.Account;
import com.gjj.igden.model.Avatar;
import com.gjj.igden.model.IWatchListDesc;
import com.gjj.igden.service.passwordencoder.AppPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class AccountService {
  @Autowired
  private AccountDaoImpl accountDaoImpl;
  
  @Autowired
  private WatchListDescDaoImpl watchListDescDao;
  
  @Autowired AvatarDaoImpl avatarDaoImpl;

  public List<Account> getAccountList() {
	  return accountDaoImpl.readAll();
  }

  public boolean createAccount(Account account) {
	  try {
		  account.setCreationDate(new Date());
		  /*Avatar avatar = new Avatar();
		  avatar.setImage(avatarDaoImpl.getDefaultAvatar());*/
		  account.setAvatar(null);
		  account.setEnabled(true);
		  account.setPassword(AppPasswordEncoder.generatePassword(account.getPassword(), account.getAccountName()));
		  accountDaoImpl.create(account);
		  return true;
		} catch (DAOException e) {
			throw new RuntimeException("Account not created", e.getCause());
		}
  }

  public boolean updateAccount(Account account) {
	  Account ac = getAccount(account.getId());
	  if(null != ac) {
		  ac.setAccountName(account.getAccountName());
		  ac.setAdditionalInfo(account.getAdditionalInfo());
		  ac.setEmail(account.getEmail());
	  }
	  accountDaoImpl.update(ac);
		return true;
  }
  
  public Account getAccount(Long accId) {
	  return accountDaoImpl.read(new Account(accId));
  }

  public Account retrieveAccount(Long accId) {
	  Account account = new Account();
	  account = accountDaoImpl.read(new Account(accId));
	  List<IWatchListDesc> dataSets = watchListDescDao.getDataSetsAttachedToAcc(accId);
	  account.setDataSets(dataSets);
	  
	  return account;
  }

  public boolean delete(Long id) throws DAOException {
	  System.out.println("AccountService.delete()");
		Account account = getAccount(id);
		accountDaoImpl.delete(account);
		return true;
  }

  public boolean setImage(long accId, InputStream is) {
    return accountDaoImpl.setImage(accId, is);
  }

  public byte[] getImage(int accId){
    return accountDaoImpl.getImage(accId);
  }
}
