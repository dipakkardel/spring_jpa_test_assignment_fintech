package com.gjj.igden.service.test;

import com.gjj.igden.service.watchlist.WatchListDescService;
import com.gjj.igden.service.test.daostub.WatchListDescDaoStub;
import com.gjj.igden.model.Account;
import com.gjj.igden.model.IWatchListDesc;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@Configuration
@ComponentScan(basePackageClasses = {WatchListDescService.class,
  WatchListDescDaoStub.class})
class SpringTextContext {
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTextContext.class)
public class WatchListDescServiceTest {
  @Autowired
  private WatchListDescService watchListDescService;

  @Test
  public void simpleReadTest() throws Exception {
    watchListDescService.getStockSymbolsList(1L).forEach(System.out::println);
  }

  @Test
  public void testCreateH2DataBaseTest() {
    List<IWatchListDesc> dataSetList = watchListDescService.getDataSetsAttachedToAcc(1L);
    final int expectedDataSetsAmount = 4;
    Assert.assertEquals(expectedDataSetsAmount, dataSetList.size());
  }

  @Test
  public void testReturnBarList() {
    IWatchListDesc dataSet = watchListDescService.getDataSetsAttachedToAcc(2L).get(0);
    System.out.println(dataSet.getWatchListName());
    Assert.assertNotNull(dataSet);
    Assert.assertEquals("test-aapl-5minBar-preMarketdata", dataSet.getWatchListName());
  }

  @Test
  public void testDelete02() throws Exception {
    List<IWatchListDesc> dataSetList = watchListDescService.getDataSetsAttachedToAcc(1L);
    final int expectedDataSetsAmount = dataSetList.size();
    boolean deleteResultFlag = watchListDescService.delete(dataSetList.get(0));
    Assert.assertTrue(deleteResultFlag);
    System.out.println("after deletion ");
    dataSetList = watchListDescService.getDataSetsAttachedToAcc(1L);
    Assert.assertNotEquals(expectedDataSetsAmount, dataSetList.size());
  }

  @Test
  public void testCreateDataSet() throws Exception {
    Long accId = 1L;
    IWatchListDesc newWatchList = watchListDescService.getWatchListDesc(1L, accId);
    List<IWatchListDesc> dataSetList = watchListDescService.getDataSetsAttachedToAcc(1L);
    int expectedDataSetsAmountAfterDeletion = 4;
    Assert.assertEquals(expectedDataSetsAmountAfterDeletion, dataSetList.size());
    Assert.assertNotNull(newWatchList);
    newWatchList.setId(111L);
    Account account = new Account();
    account.setId(accId);
    newWatchList.setAccount(account);
    newWatchList.setWatchListName("just testing around");
    Assert.assertTrue(watchListDescService.create(newWatchList));
    dataSetList = watchListDescService.getDataSetsAttachedToAcc(1L);
    expectedDataSetsAmountAfterDeletion = 5;
    Assert.assertEquals(expectedDataSetsAmountAfterDeletion, dataSetList.size());
  }

  @Test
  public void testUpdate() throws Exception {
    final Long accId = 1L;
    IWatchListDesc dataSet = watchListDescService.getWatchListDesc(1L, accId);
    dataSet.setWatchListName("test update");
    Account account = new Account();
    account.setId(accId);
    dataSet.setAccount(account);
    watchListDescService.update(dataSet);
    final String dataSetNameDirect = watchListDescService.getWatchListDesc(1L, 1L).getWatchListName();
    Assert.assertEquals("test update", dataSetNameDirect);
  }

  @Test
  public void test01Read() throws Exception {
    List<IWatchListDesc> watchListDescs = watchListDescService.getDataSetsAttachedToAcc(1L);
    final int size = 4;
    Assert.assertEquals(size,
      watchListDescs.size());
  }

  /*










  */
}
