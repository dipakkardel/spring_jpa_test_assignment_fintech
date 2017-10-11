package com.gjj.igden.dao;

import com.gjj.igden.model.Account;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.List;

public interface AccountDao {
  void setDataSource(DataSource dataSource);

  void setNamedParamJbd(NamedParameterJdbcTemplate namedParamJbd);

  List<Account> getAllAccounts();

  boolean delete(Account account);

  boolean delete(Long id);

  Account getAccountById(Long id);

  boolean update(Account acc);

  boolean create(Account account);

  boolean setImage(Long accId, InputStream is);

  byte[] getImage(Long accId);
}
