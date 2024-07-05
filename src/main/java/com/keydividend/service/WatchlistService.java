package com.keydividend.service;

import com.keydividend.dao.WatchlistDao;
import com.keydividend.entity.Watchlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * @author  rupau
 *
 */
@Service
public class WatchlistService {

    @Autowired
    WatchlistDao watchlistDao;

    public boolean addToWatchlist(Watchlist watchlist) {
        return watchlistDao.addToWatchlist(watchlist);
    }

    public boolean removeFromWatchlist(Watchlist watchlist) {
        return watchlistDao.removeFromWatchlist(watchlist);
    }

    public List<Watchlist> getWatchlistByUserId(String userId)
    {
        return watchlistDao.getWatchlistByUserId(userId);
    }

    public List<Watchlist> compareWatchlist(List<String> watchlistIds)
    {
        return watchlistDao.compareWatchlist(watchlistIds);
    }


    public void watchlistNotifications()
    {
        watchlistDao.getWatchlistMap();
    }
}
