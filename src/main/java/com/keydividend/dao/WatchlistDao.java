package com.keydividend.dao;

import com.keydividend.entity.Watchlist;

import java.util.List;
import java.util.Map;

public interface WatchlistDao {


    public boolean addToWatchlist(Watchlist watchlist);
    public boolean removeFromWatchlist(Watchlist watchlist);
    public List<Watchlist> getWatchlistByUserId(String userId);
    public List<Watchlist> compareWatchlist(List<String> watchlistIds);

    public Map<String,List<Watchlist>> getWatchlistMap();
}
