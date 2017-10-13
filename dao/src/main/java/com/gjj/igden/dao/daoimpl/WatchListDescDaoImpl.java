package com.gjj.igden.dao.daoimpl;

import com.gjj.igden.dao.AbstractDAO;
import com.gjj.igden.dao.WatchListDescDao;
import com.gjj.igden.dao.WatchListDescRowMapper;
import com.gjj.igden.dao.WatchListTickersRowMapper;
import com.gjj.igden.dao.daoUtil.DAOException;
import com.gjj.igden.model.Account;
import com.gjj.igden.model.IWatchListDesc;
import com.gjj.igden.model.WatchListDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WatchListDescDaoImpl extends AbstractDAO<IWatchListDesc> {

	@Override
	public IWatchListDesc read(IWatchListDesc obj) {
		return (WatchListDesc) em.createQuery("FROM WatchListDesc where id="+obj.getId()).getSingleResult();
	}

	@Override
	public List<IWatchListDesc> readAll() {
		return em.createQuery("FROM WatchListDesc").getResultList();
	}
	
	public boolean createWatchListDesc(IWatchListDesc watchListDesc) throws DAOException {
		super.create(watchListDesc);
		return false;
	}
	
	public List<IWatchListDesc> getDataSetsAttachedToAcc(Long id) {
		return em.createQuery("FROM WatchListDesc WHERE account_fk_id = "+id).getResultList();
	}

	public List<String> getAllStockSymbols(Long id) {
		IWatchListDesc iWatchListDesc = (IWatchListDesc) em.createQuery("FROM WatchListDesc where id="+id).getSingleResult();
		em.createQuery("select instId FROM InstId where watchlist_id_fk=1").getResultList().forEach(System.out::println);
		List<String> list = em.createQuery("select instId FROM InstId where watchlist_id_fk="+iWatchListDesc.getId()).getResultList();
		
		return list;
	}
	
	public boolean deleteWatchListDesc(Long dsId, Long accId) {
		em.createQuery("delete from WatchListDesc where id="+dsId+" and account="+new Account(accId));
		return true;
	}
	
	public IWatchListDesc getWatchListDesc(Long dsId, Long accId) {
		return (IWatchListDesc) em.createQuery("FROM WatchListDesc WHERE id = "+dsId+" AND Account = "+new Account(accId)).getSingleResult();
	}
	
	public boolean deleteWatchListDesc(IWatchListDesc watchListDesc) throws DAOException {
		super.delete(read(watchListDesc));
		return true;
	}
	
	public boolean updateWatchListDesc(IWatchListDesc watchListDesc) {
		super.update(watchListDesc);
		return false;
	}
	

}
