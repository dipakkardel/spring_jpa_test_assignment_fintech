package com.gjj.igden.model;

import org.apache.commons.collections4.FactoryUtils;
import org.apache.commons.collections4.list.LazyList;

import com.gjj.igden.utils.EntityId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Data_Set")
public class WatchListDesc implements IWatchListDesc, Serializable, EntityId {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "data_set_id")
	private Long id; // data_set_id
	
	private Account account; // account_fk
	
	@Column(name = "data_set_name")
	private String watchListName; // dataset name
	
	@Column(name = "data_set_desc")
	private String watchListDetails; // data set desc
	
	@Column(name = "market_data_frequency")
	private int marketDataFrequency; // market data frequency
	
	@Column(name="data_provider")
	private String dataProviders; // data providers
	
	@Transient
	private  List<String> stockSymbolsList; // wl_tickers (insta) -- delete
	@Transient
	private  List<OperationParameters> operationParameterses = LazyList.lazyList(new ArrayList<>(),
			FactoryUtils.instantiateFactory(OperationParameters.class));
	
	public WatchListDesc(Long id) {
		super();
		this.id = id;
	}

public List<String> getStockSymbolsList() {
    return stockSymbolsList;
  }

  public void setStockSymbolsList(List<String> stockSymbolsList) {
    this.stockSymbolsList = stockSymbolsList;
  }

  public void setStockSymbolsListFromOperationList(List<OperationParameters> stockSymbolsList) {
    List<String> stringList = stockSymbolsList
      .stream()
      .map(OperationParameters::getName)
      .collect(Collectors.toList());
    this.stockSymbolsList = stringList;
  }

  public List<OperationParameters> getOperationParameterses() {
    return operationParameterses;
  }

  public void setOperationParameterses(
    List<OperationParameters> operationParameterses) {
    this.operationParameterses = operationParameterses;
  }

  public Long getWatchListId() {
    return id;
  }

  public void setWatchListId(Long watchListId) {
    this.id = watchListId;
  }

  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name="account_id")
  public Account getAccount() {
    return account;
  }

  public void setAccount(Account accountId) {
    this.account = accountId;
  }

  public String getWatchListName() {
    return watchListName;
  }

  public void setWatchListName(String watchListName) {
    this.watchListName = watchListName;
  }

  public String getWatchListDetails() {
    return watchListDetails;
  }

  public void setWatchListDetails(String watchListDetails) {
    this.watchListDetails = watchListDetails;
  }

  public int getMarketDataFrequency() {
    return marketDataFrequency;
  }

  public void setMarketDataFrequency(int marketDataFrequency) {
    this.marketDataFrequency = marketDataFrequency;
  }

  public String getDataProviders() {

    // TODO m it should be List<providerID> like phone in social network
    return dataProviders;
  }

  public void setDataProviders(String dataProviders) {
    this.dataProviders = dataProviders;
  }

  public WatchListDesc() {
  }

  public WatchListDesc(Account accountId) {
    this.account = accountId;
  }

  public WatchListDesc(Long watchListId, Account accountId, String watchListName,
                       String watchListDetails, int marketDataFrequency, String dataProviders) {
    this.id = watchListId;
    this.account = accountId;
    this.watchListName = watchListName;
    this.watchListDetails = watchListDetails;
    this.marketDataFrequency = marketDataFrequency;
    this.dataProviders = dataProviders;
  }

  @Override
  public String toString() {
    return String.valueOf(" account id =  " + this.getAccount() + "\n " +
      "data set id = " + this.getWatchListId() + "\n " +
      "market data freq = " + this.getMarketDataFrequency() + "\n " +
      "data set name = " + this.getWatchListName() + "\n " +
      "data set description = " + this.getWatchListDetails() + "\n ");
  }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}