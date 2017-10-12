package com.gjj.igden.controller;

import com.gjj.igden.dao.daoUtil.DAOException;
import com.gjj.igden.model.Users;
import com.gjj.igden.model.WatchListDesc;
import com.gjj.igden.service.accountService.AccountService;
import com.gjj.igden.service.watchlist.WatchListDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class WatchListDescController {
  @Autowired
  private WatchListDescService service;//renamed WatchListDescServiceService to WatchListDescService 
  
  @Autowired
  private AccountService accountService;

  @RequestMapping(value = "/view-watchlist", method = RequestMethod.GET)
  public String viewAccount(ModelMap model, @RequestParam Long id) {
    model.addAttribute("stockSymbolsList", service.getStockSymbolsList(id));
    model.addAttribute("watchListId", id);
    return "view-watchlist";
  }

  @GetMapping(value = "/add-watchlist")
  public String addWatchList(ModelMap model, @RequestParam Long id) {
    System.out.println(id);
    //temporary commented to resolve autowiring issue while generating tables
    WatchListDesc theWatchListDesc = new WatchListDesc(id);
    model.addAttribute("theWatchListDesc", theWatchListDesc);
    return "lazyRowLoad";
  }

  @PostMapping(value = "/lazyRowAdd.web")
  public String lazyRowAdd(@ModelAttribute("theWatchListDesc") WatchListDesc theWatchListDesc,
                           @ModelAttribute("username1") String watchlistName, @RequestParam("id") Long accId) throws DAOException {
    System.out.println(accId);
    theWatchListDesc.setAccount(accountService.retrieveAccount(accId));
    theWatchListDesc.setWatchListName(watchlistName);
    service.create(theWatchListDesc);
    return "redirect:/view-account?id=2";
  }
}
