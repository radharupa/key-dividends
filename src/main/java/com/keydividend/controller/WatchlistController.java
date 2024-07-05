package com.keydividend.controller;

import com.keydividend.entity.Watchlist;
import com.keydividend.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/watchlist")
public class WatchlistController {

    @Autowired
    WatchlistService watchlistService;

    @PostMapping(path = "/addToWatchlist")
    public boolean addToWatchlist(@RequestBody Watchlist watchlist) throws Exception{
        return watchlistService.addToWatchlist(watchlist);
    }

    @PostMapping(path = "/removeFromWatchlist")
    public boolean removeFromWatchlist(@RequestBody Watchlist watchlist) throws Exception{
        return watchlistService.removeFromWatchlist(watchlist);
    }


    @PostMapping(path = "/getWatchlistByUserId")
    public List<Watchlist> getWatchlistByUserId(@RequestBody String userId) throws Exception{
        return watchlistService.getWatchlistByUserId(userId);
    }

    @PostMapping(path = "/compareWatchlist")
    public List<Watchlist> compareWatchlist(@RequestBody List<String> watchlistIds) throws Exception{
        return watchlistService.compareWatchlist(watchlistIds);
    }


}
