package com.gjj.igden.model;

import com.gjj.igden.utils.EntityId;
import com.google.common.base.Objects;
import org.javatuples.Ennead;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("WeakerAccess")
@Entity
@Table(name="Market_Data")
public class Bar extends MarketData implements InterfaceOHLCData, EntityId {
    /**
     * mdId - i suppose this only for bar data_containers because for tick data_containers it is will
     * be impossible to supply all needed id
     */
    protected Long id;
    protected IWatchListDesc dataSetId;
    protected int barSize;
    protected double open;
    protected double high;
    protected double low;
    protected double close;
    protected long volume;
    protected String logInfo;

    public Bar() {
    }

    public Bar(IWatchListDesc dataSetId) {
        this.dataSetId = dataSetId;
    }

    public Bar(IWatchListDesc dataSetId, int barSize) {
        this.dataSetId = dataSetId;
        this.barSize = barSize;
    }

    public Bar(long mdId) {
        this.id = mdId;
    }

    public Bar(long mdId, IWatchListDesc dataSetId, String instId) {
        this.id = mdId;
        this.dataSetId = dataSetId;
        this.instId = new InstId(instId);
    }

    public Bar(long mdId, String instId) {
        this.id = mdId;
        this.instId = new InstId(instId);
    }

    public Bar(IWatchListDesc dataSetId, String instId, int barSize) {
        this.dataSetId = dataSetId;
        this.instId = new InstId(instId);
        this.barSize = barSize;
    }

    public Bar(IWatchListDesc dataSetId, String instId) {
        this.dataSetId = dataSetId;
        this.instId = new InstId(instId);
    }

    public Bar(Bar bar) {
        super(bar.instId, bar.dateTime);
        this.dataSetId = bar.dataSetId;
        this.id = bar.id;
        this.barSize = bar.barSize;
        this.open = bar.open;
        this.high = bar.high;
        this.low = bar.low;
        this.close = bar.close;
        this.volume = bar.volume;
        this.logInfo = bar.logInfo;
    }

    public Bar(InstId instId, Date dateTime, Long mdId, IWatchListDesc dataSetId, int barSize, double open,
               double high, double low, double close, long volume, String logInfo) {
        super(instId, dateTime);
        this.id = mdId;
        this.dataSetId = dataSetId;
        this.barSize = barSize;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.logInfo = logInfo;
    }

    public Bar(
            InstId instId,
            int barSize,
            Date dateTime,
            double open,
            double high,
            double low,
            double close) {
        this(instId, barSize, dateTime, open, high, low, close, 0, 0, null);
    }

    public Bar(
            InstId instId,
            int barSize,
            Date dateTime,
            double open,
            double high,
            double low,
            double close,
            long volume,
            String logInfo) {
        this(instId, barSize, dateTime, open, high, low, close, volume, 0, logInfo);
    }

    public Bar(
            InstId instId,
            int barSize,
            Date dateTime,
            double open,
            double high,
            double low,
            double close,
            long volume,
            long mdId,
            String logInfo) {
        super(instId, dateTime);
        this.id = mdId;
        this.barSize = barSize;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.logInfo = logInfo;
    }

    public Bar(Ennead<InstId, Long, Long, Integer, Double, Double,
            Double, Double, Long> ennead) {
        setMainData(ennead);
    }

    public Bar(IMetaDataUnit resultSet) {
        setMainData(resultSet.getDataUnit());
    }

    @Override
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long mdId) {
        this.id = mdId;
    }

    @Autowired
    @Transient
    public IWatchListDesc getDataSetId() {
        return dataSetId;
    }

    public void setDataSetId(IWatchListDesc dataSetId) {
        this.dataSetId = dataSetId;
    }

    @Transient
    public int getBarSize() {
        return barSize;
    }

    public void setBarSize(int barSize) {
        this.barSize = barSize;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    @Column(name="additional_info")
    public String getLogInfo() {
        return this.logInfo;
    }

    public String toString() {
        Instant fromUnixTimestamp = Instant.ofEpochSecond(dateTime.getTime());
        LocalDateTime time = LocalDateTime.ofInstant(fromUnixTimestamp,
                ZoneId.of("UTC-4"));
        return "\n{ " +
                "mdId=" + id +
                "; instId=" + instId +
                // 	", dateTime=" + dateTime +
                "; dateTime=" + time +
                "; barSize=" + barSize +
                "; high=" + high +
                "; low=" + low +
                "; open=" + open +
                "; close=" + close +
                "; volume=" + volume +
                "; info=" + logInfo +
                " } ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bar bar = (Bar) o;
        return Objects.equal(instId, bar.instId) &&
                Objects.equal(dateTime, bar.dateTime) &&
                // TODO m high priority: the line below - in some moment barSize is not setted up
                // TODO m high priority: use test createDataSetTestImportant() to determine the
                // TODO m high priority: bug source
                //	Objects.equal(barSize, bar.barSize) &&
                Objects.equal(high, bar.high) &&
                Objects.equal(low, bar.low) &&
                Objects.equal(open, bar.open) &&
                Objects.equal(close, bar.close) &&
                Objects.equal(volume, bar.volume) &&
                Objects.equal(id, bar.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(instId, dateTime, barSize, high, low, open, close, volume, id);
    }

    @Override
    public void reset() {
        super.reset();
        barSize = -1;
        id = null;
        high = 0.0;
        low = 0.0;
        open = 0.0;
        close = 0.0;
        volume = 0;
        logInfo = null;
    }

    public void copy(Bar bar) {
        this.instId = bar.instId;
        this.id = bar.id;
        this.dateTime = bar.dateTime;
        this.barSize = bar.barSize;
        this.high = bar.high;
        this.low = bar.low;
        this.open = bar.open;
        this.close = bar.close;
        this.volume = bar.volume;
        this.logInfo = bar.logInfo;
    }
    @Override
    @Transient
    public Ennead<InstId, Long, Long, Integer, Double, Double, Double, Double, Long> getMainData() {
        return null;
    }

    public void setMainData(Ennead<InstId, Long, Long, Integer, Double, Double,
            Double, Double, Long> ennead) {
        this.instId = ennead.getValue0();
        this.id = ennead.getValue1();
        this.dateTime = new Date(ennead.getValue2());
        this.barSize = ennead.getValue3();
        this.high = ennead.getValue4();
        this.low = ennead.getValue5();
        this.open = ennead.getValue6();
        this.close = ennead.getValue7();
        this.volume = ennead.getValue8();
        // this.logInfo = ennead.logInfo;
    }

}
