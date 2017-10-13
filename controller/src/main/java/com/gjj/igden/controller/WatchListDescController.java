package com.gjj.igden.controller;

import com.gjj.igden.dao.daoUtil.DAOException;
import com.gjj.igden.model.WatchListDesc;
import com.gjj.igden.service.accountService.AccountService;
import com.gjj.igden.service.watchlist.WatchListDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class WatchListDescController {
  @Autowired
  private WatchListDescService service;//renamed WatchListDescServiceService to WatchListDescService 
  
  @Autowired
  private AccountService accountService;
  
  @RequestMapping(value = "/view-watchlist", method = RequestMethod.GET)
  public String viewWatchList(ModelMap model, @RequestParam Long watchListId) {
	  System.out.println("WatchListDescController.viewAccount()::: watchListId="+watchListId);
    model.addAttribute("stockSymbolsList", service.getStockSymbolsList(watchListId));
    model.addAttribute("watchListId", watchListId);
    return "view-watchlist";
  }

  @GetMapping(value = "/add-watchlist")
  public String addWatchList(ModelMap model, @RequestParam Long id) {
   System.out.println("WatchListDescController.addWatchList()="+id);
    WatchListDesc theWatchListDesc = new WatchListDesc();
    theWatchListDesc.setAccount(accountService.getAccount(id));
    model.addAttribute("theWatchListDesc", theWatchListDesc);
    return "lazyRowLoad";
  }

  @PostMapping(value = "/lazyRowAdd.web")
  public String lazyRowAdd(@ModelAttribute("theWatchListDesc") WatchListDesc theWatchListDesc, 
		  @RequestParam("acc_id") Long accId) throws DAOException {
    System.out.println("lazyRowAdd::::::::::::"+theWatchListDesc.getOperationParameterses());
    theWatchListDesc.setAccount(accountService.getAccount(accId));
    service.create(theWatchListDesc);
    return "redirect:/view-account?id="+accId;
  }
  
  @RequestMapping(value = "/delete-watchlist", method = RequestMethod.GET)
  public String deleteAccount(ModelMap model, @RequestParam("watchListId") Long watchListId, 
		  @RequestParam("accountId") Long accountId) {
    try {
    	System.out.println("accountId:::::::::::::::::::::::::::::::::::"+accountId);
		service.delete(new WatchListDesc(watchListId));
	} catch (DAOException e) {
		e.printStackTrace();
	}
    return "redirect:/view-account?id="+accountId;
  }
  
}
