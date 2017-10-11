package com.gjj.igden.model;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.gjj.igden.model.IWatchListDesc;
import com.gjj.igden.utils.EntityId;
import com.gjj.igden.utils.Exchange;
import com.google.common.base.Objects;

/**
 * Instrument identifier
 */
// table name = wl_tickers
@Entity
@Table(name = "wl_tickers")
public class InstId {
	@Id
	@Column(name = "instId")
	private String instId;
	private final static String SEPARATOR = "@";
	private String symbol; // create composite primary key symbol + exch_id
	@Transient private String exchId;
	// new parameter WachListDesc
	@Autowired
	@ManyToOne(cascade=CascadeType.ALL,targetEntity=WatchListDesc.class)
	private IWatchListDesc iWatchListDesc;
	private @Transient Exchange exchange;

	public InstId(String str) {
		validateCorrectString(str);
		String[] tokens = str.split("@");
		symbol = tokens[0].toUpperCase();
		exchId = tokens[1].toUpperCase();
	}

	public InstId(String symbol, String exchId) {
		this.symbol = symbol;
		this.exchId = exchId;
	}

	@Transient
	public Exchange getExchange() {
		if (java.util.Objects.equals(exchId, "NASDAQ")) {
			return Exchange.NASDAQ;
		}
		if (java.util.Objects.equals(exchId, "NYSE")) {
			return Exchange.NYSE;
		}
		return exchange;
	}

	private void validateCorrectString(String str) {
	}
	
	public void setInstId(String instId) {
		this.instId = instId;
	}

	
	public String getInstId() {
		return symbol + SEPARATOR + getExchange().getExchId();
	}
	
	
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	public void setExchId(String exchId) {
		this.exchId = exchId;
	}

	public String getExchId() {
		return exchId;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
	
	public IWatchListDesc getiWatchListDesc() {
		return iWatchListDesc;
	}

	public void setiWatchListDesc(IWatchListDesc iWatchListDesc) {
		this.iWatchListDesc = iWatchListDesc;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof InstId)) {
			return false;
		}
		InstId altInstId = (InstId) o;
		return Objects.equal(symbol, altInstId.symbol) && Objects.equal(exchId, altInstId.exchId);
	}

	@Transient
	public Exchange getExchange(String exchId) {
		if (exchId.equalsIgnoreCase("NYSE")) {
			return Exchange.NYSE;
		} else if (exchId.equalsIgnoreCase("nasdaq")) {
			return Exchange.NASDAQ;
		} else {
			throw new IllegalArgumentException("your exchange id is not correct");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(symbol, exchId);
	}

	@Override
	public String toString() {
		return symbol + SEPARATOR + exchId;
	}
}