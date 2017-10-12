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

	public List<String> getAllStockSymbols(Long id) {
		//return em.createQuery("instId FROM wl_tickers WHERE watchlist_id_fk = :id\"")
		IWatchListDesc iWatchListDesc = (IWatchListDesc) em.createQuery("FROM WatchListDesc where id="+id).getSingleResult();
		em.createQuery("select instId FROM InstId where watchlist_id_fk=1").getResultList().forEach(System.out::println);
		List<String> list = em.createQuery("select instId FROM InstId where watchlist_id_fk="+iWatchListDesc.getId()).getResultList();
		
		return list;
	}

	public List<IWatchListDesc> getDataSetsAttachedToAcc(Long id) {
		return em.createQuery("FROM WatchListDesc WHERE account_fk_id = "+id).getResultList();
	}


	public IWatchListDesc getWatchListDesc(Long dsId, Long accId) {
		return (IWatchListDesc) em.createQuery("FROM WatchListDesc WHERE id = "+dsId+" AND Account = "+new Account(accId)).getSingleResult();
	}

	public boolean addTicker(Long watchlistId, String tickerName) {
		em.createQuery("INSERT INTO InstId (iWatchListDesc, instId) VALUES (:watchlistId, :tickerNam");
		return false;
	}

	public boolean deleteWatchListDesc(Long dsId, Long accId) {
		em.createQuery("delete from WatchListDesc where id="+dsId+" and account="+new Account(accId));
		return true;
	}

	public boolean deleteWatchListDesc(IWatchListDesc watchListDesc) throws DAOException {
		super.delete(watchListDesc);
		return true;
	}

	public boolean createWatchListDesc(IWatchListDesc watchListDesc) throws DAOException {
		super.create(watchListDesc);
		return false;
	}

	public boolean updateWatchListDesc(IWatchListDesc watchListDesc) {
		super.update(watchListDesc);
		return false;
	}

	@Override
	public IWatchListDesc read(IWatchListDesc obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IWatchListDesc> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
    
	
	
	
	
	
	
	/*private NamedParameterJdbcTemplate namedParamJbd;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParamJbd = new NamedParameterJdbcTemplate(dataSource);
    }

    public void setNamedParamJbd(NamedParameterJdbcTemplate namedParamJbd) {
        this.namedParamJbd = namedParamJbd;
    }

    public List<String> getAllStockSymbols(Long watchListDescId) {
        SqlParameterSource params = new MapSqlParameterSource("id", watchListDescId);
        String sqlQuery = "SELECT instId FROM wl_tickers WHERE watchlist_id_fk = :id";
        return namedParamJbd.query(sqlQuery, params, new WatchListTickersRowMapper());
    }

    public List<IWatchListDesc> getDataSetsAttachedToAcc(Long accId) {
        SqlParameterSource params = new MapSqlParameterSource("accountId", accId);
        final String getDataFromDataSetTable = "SELECT * FROM data_set WHERE account_fk_id = :accountId";
        List<IWatchListDesc> watchListDescs = namedParamJbd.query(getDataFromDataSetTable,
                params, new WatchListDescRowMapper());
        watchListDescs.forEach(p -> p.setStockSymbolsList(getAllStockSymbols(p.getId())));
        return watchListDescs;
    }

    public IWatchListDesc getWatchListDesc(Long dsId, Long accId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("accId", accId);
        parameters.put("dsId", dsId);
        final String sqlQuery =
                "SELECT * FROM data_set WHERE account_fk_id = :accId AND data_set_id = :dsId";
        return namedParamJbd.queryForObject(sqlQuery, parameters, new WatchListDescRowMapper());
    }

    @Override
    public boolean addTicker(Long watchlistId, String tickerName) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("watchlistId", watchlistId);
        paramMap.put("tickerName", tickerName);
        final String INSERT_QUERY =
                "INSERT INTO wl_tickers (`watchlist_id_fk`, `instId`) VALUES (:watchlistId, :tickerName);";
        return namedParamJbd.update(INSERT_QUERY, paramMap) == 1;
    }

    @Transactional
    public boolean deleteWatchListDesc(Long dsId, Long accId) {
        IWatchListDesc dataSet = this.getWatchListDesc(dsId, accId);
        SqlParameterSource beanParams = new BeanPropertySqlParameterSource(dataSet);
        String sqlQuery = "	DELETE FROM data_set WHERE data_set_id = :watchListId" +
                " AND account_fk_id = :accountId;";
        return namedParamJbd.update(sqlQuery, beanParams) == 1;
    }

    @Transactional
    public boolean deleteWatchListDesc(IWatchListDesc watchListDesc) {
        SqlParameterSource beanParams = new BeanPropertySqlParameterSource(watchListDesc);
        String sqlQuery = "	DELETE FROM data_set WHERE data_set_id = :watchListId" +
                " AND account_fk_id = :accountId;";
        return namedParamJbd.update(sqlQuery, beanParams) == 1;
    }

    @Transactional
    public boolean createWatchListDesc(IWatchListDesc watchListDesc) {
        if (createWatchListDescFields(watchListDesc)) {
            List<String> tickers = watchListDesc.getStockSymbolsList();
            if (tickers != null && tickers.size() > 0) {
                for (String ticker : tickers) {
                    if (!setWatchListTickers(ticker)) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean createWatchListDescFields(IWatchListDesc watchListDesc) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("accId", watchListDesc.getAccount(), Types.INTEGER);
        parameters.addValue("data_set_name", watchListDesc.getWatchListName());
        parameters.addValue("market_data_frequency", watchListDesc.getMarketDataFrequency());
        parameters.addValue("data_set_description", watchListDesc.getWatchListDetails());
        parameters.addValue("data_providers", watchListDesc.getDataProviders());
        String sqlQuery = " INSERT INTO data_set ( account_fk_id, data_set_name, " +
                "data_set_description, market_data_frequency, data_providers ) " +
                "VALUES ( :accId, :data_set_name, :data_set_description," +
                " :market_data_frequency, :data_providers);";
        return namedParamJbd.update(sqlQuery, parameters) == 1;
    }

    @Transactional
    public boolean setWatchListTickers(String ticker) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("instId", ticker);
        String sqlQuery = " INSERT INTO wl_tickers (instId, watchlist_id_fk)" +
                "VALUES ( :instId, LAST_INSERT_ID()) ;";
        return namedParamJbd.update(sqlQuery, parameters) == 1;
    }

    @Transactional
    public boolean updateWatchListDesc(IWatchListDesc watchListDesc) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("data_set_id", watchListDesc.getId());
        parameters.put("account_fk_id", watchListDesc.getAccount());
        parameters.put("data_set_name", watchListDesc.getWatchListName());
        String sqlQuery = "UPDATE data_set SET data_set_name = :data_set_name " +
                "WHERE data_set_id = :data_set_id AND account_fk_id = :account_fk_id";
        return namedParamJbd.update(sqlQuery, parameters) == 1;
    }*/

}
