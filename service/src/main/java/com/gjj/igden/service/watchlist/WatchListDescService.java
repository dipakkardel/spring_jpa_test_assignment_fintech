package com.gjj.igden.service.watchlist;

import com.gjj.igden.dao.WatchListDescDao;
import com.gjj.igden.dao.daoUtil.DAOException;
import com.gjj.igden.dao.daoimpl.WatchListDescDaoImpl;
import com.gjj.igden.model.IWatchListDesc;
import com.gjj.igden.model.WatchListDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchListDescService {
	@Autowired
	private WatchListDescDaoImpl watchListDescDao;

	public List<IWatchListDesc> getDataSetsAttachedToAcc(Long id) {
		return watchListDescDao.getDataSetsAttachedToAcc(id);
	}

	public List<String> getStockSymbolsList(Long id) {
		return watchListDescDao.getAllStockSymbols(id);
	}

	public boolean delete(Long accId, Long watchListId) {
		return watchListDescDao.deleteWatchListDesc(watchListId, accId);
	}

	public boolean delete(IWatchListDesc watchListDesc) throws DAOException {
		return watchListDescDao.deleteWatchListDesc(watchListDesc);
	}

	public boolean create(IWatchListDesc watchListDesc) throws DAOException {
		watchListDesc.setStockSymbolsListFromOperationList(watchListDesc.getOperationParameterses());
		return watchListDescDao.createWatchListDesc(watchListDesc);
	}

	public IWatchListDesc getWatchListDesc(Long dsId, Long accId) {
		return watchListDescDao.getWatchListDesc(dsId, accId);
	}

	public boolean update(IWatchListDesc watchListDesc) {
		return watchListDescDao.updateWatchListDesc(watchListDesc);
	}
}
