package com.gjj.igden.model;

import java.beans.Transient;
import java.util.List;

import com.gjj.igden.utils.EntityId;

public interface IWatchListDesc extends EntityId {
	@Transient
	List<String> getStockSymbolsList();

	void setStockSymbolsList(List<String> stockSymbolsList);

	void setStockSymbolsListFromOperationList(List<OperationParameters> stockSymbolsList);

	Long getId();

	void setId(Long watchListId);

	Account getAccount();

	void setAccount(Account account);

	String getWatchListName();

	void setWatchListName(String watchListName);

	String getWatchListDetails();

	void setWatchListDetails(String watchListDetails);

	int getMarketDataFrequency();

	void setMarketDataFrequency(int marketDataFrequency);

	String getDataProviders();

	void setDataProviders(String dataProviders);

	String toString();

	@Transient
	List<OperationParameters> getOperationParameterses();

	void setOperationParameterses(List<OperationParameters> operationParameterses);
}
