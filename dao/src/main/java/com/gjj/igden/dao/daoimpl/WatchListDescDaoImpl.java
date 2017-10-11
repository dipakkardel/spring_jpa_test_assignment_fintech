package com.gjj.igden.dao.daoimpl;

import com.gjj.igden.dao.AbstractDAO;
import com.gjj.igden.dao.WatchListDescDao;
import com.gjj.igden.dao.WatchListDescRowMapper;
import com.gjj.igden.dao.WatchListTickersRowMapper;
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
public class WatchListDescDaoImpl extends AbstractDAO<WatchListDesc> {
/*COMPILLATION ERROR BECAUSE OF INCOMPLETE IMPLEMENTATION OF WatchListDescDaoImpl */
	public List<String> getAllStockSymbols(Long id) {
		//return em.createQuery("instId FROM wl_tickers WHERE watchlist_id_fk = :id\"")
		IWatchListDesc iWatchListDesc = (IWatchListDesc) em.createQuery("FROM WatchListDesc where id="+id+"").getSingleResult();
		List<String> list = em.createQuery("instId from InstId where iWatchListDesc="+iWatchListDesc).getResultList();
		return list;
	}

	public List<IWatchListDesc> getDataSetsAttachedToAcc(Long id) {
		return em.createQuery("FROMWatchListDesc WHERE account = "+new Account(id)).getResultList();
	}


	public IWatchListDesc getWatchListDesc(Long dsId, Long accId) {
		return (IWatchListDesc) em.createQuery("FROM WatchListDesc WHERE id = "+dsId+" AND Account = "+new Account(accId)).getSingleResult();
	}
/* TEMPORARY COMMENTED TILL TOTAL IMPLEMENTATION BECAUSE FOUND ONE MISSING COLUMN SYMBOL IN TABLE INSTID wl_tickers*/
	/*public boolean addTicker(Long watchlistId, String tickerName) {
		em.createQuery("");
		return false;
	}

	public boolean deleteWatchListDesc(Long dsId, Long accId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteWatchListDesc(IWatchListDesc watchListDesc) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean createWatchListDesc(IWatchListDesc watchListDesc) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateWatchListDesc(IWatchListDesc watchListDesc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WatchListDesc read(WatchListDesc obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WatchListDesc> readAll() {
		// TODO Auto-generated method stub
		return null;
	}*/

}
