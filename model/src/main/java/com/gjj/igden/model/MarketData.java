package com.gjj.igden.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class MarketData {
  /**
   * instance Id as a tool for solving some problems : for example conditional problems ( like
   * finding new day ) ; jMetric ; performance metrics and ect
   */
  private static final String MYSQL_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  protected InstId instId;
  protected Date dateTime;

  protected MarketData() {
  }

  public MarketData(InstId instId, Date dateTime) {
    this.instId = instId;
    this.dateTime = dateTime;
  }

  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name = "instId_fk")
  public InstId getInstId() {
    return instId;
  }

  public void setInstId(String instId) {
    this.instId = new InstId(instId);
  }

  public void setInstId(InstId instId) {
    this.instId = instId;
  }

  @Column(name="ticker")
  public String getTicket() {
    return instId.getSymbol();
  }
  
  public void setTicket(String symbol) {
	     instId.setSymbol(symbol);
	  }

  @Column(name="date")
  public Date getDateTime() {
    return dateTime;
  }

  public void setDateTime(Date dateTime) {
    this.dateTime = dateTime;
  }

  public void setDateTime(String dateTime) throws ParseException {
    SimpleDateFormat stf = new SimpleDateFormat(MYSQL_TIME_FORMAT);
    // stf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
    stf.setTimeZone(instId.getExchange().getTimeZone());
    java.util.Date date = stf.parse(dateTime);
    // System.err.println(date);
    this.dateTime = new java.sql.Date(date.getTime());
  }

  @Transient
  public Date getDateTimeMySQLFormat() throws ParseException {
    /*Date dateFromEpoch = new Date(dateTime * 1000);
    SimpleDateFormat df = new SimpleDateFormat(MYSQL_TIME_FORMAT);
    df.setTimeZone(instId.getExchange().getTimeZone());
    return df.format(dateFromEpoch);*/
	  return dateTime;
  }

  public void reset() {
    this.instId = null;
    this.dateTime = new Date(0);
  }
}
