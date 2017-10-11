package com.gjj.igden.dao;

import com.gjj.igden.model.IWatchListDesc;
import com.gjj.igden.model.WatchListDesc;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.Transient;

@Repository
public interface WatchListDescDao {
	
	List<String> getAllStockSymbols(Long id);

	List<IWatchListDesc> getDataSetsAttachedToAcc(Long id);

	void setNamedParamJbd(NamedParameterJdbcTemplate namedParamJbd);

	IWatchListDesc getWatchListDesc(Long dsId, Long accId);

	boolean addTicker(Long watchlistId, String tickerName);

	boolean deleteWatchListDesc(Long dsId, Long accId);

	boolean deleteWatchListDesc(IWatchListDesc watchListDesc);

	boolean createWatchListDesc(IWatchListDesc watchListDesc);

	boolean updateWatchListDesc(IWatchListDesc watchListDesc);

}
