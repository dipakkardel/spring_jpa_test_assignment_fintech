package com.gjj.igden.dao;

import com.gjj.igden.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
  public Account mapRow(ResultSet resultSet, int i) throws SQLException {
    Account account = new Account();
    account.setId(resultSet.getLong("account_id"));
    account.setAccountName(resultSet.getString("account_name"));
    account.setEmail(resultSet.getString("email"));
    account.setAdditionalInfo(resultSet.getString("additional_info"));
    account.setCreationDate(resultSet.getDate("creation_date"));
    return account;
  }
}
