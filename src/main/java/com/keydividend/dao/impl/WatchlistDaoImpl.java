package com.keydividend.dao.impl;

import com.keydividend.constants.WatchlistSchemaConstants;
import com.keydividend.dao.DividendDao;
import com.keydividend.dao.WatchlistDao;
import com.keydividend.entity.HistoricalData;
import com.keydividend.entity.Watchlist;
import com.keydividend.constants.Constants;
import com.keydividend.constants.TickerSchemaConstants;
import com.keydividend.constants.UserSchemaConstants;
import com.mongodb.client.result.DeleteResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author rupau
 *
 */
@Component
@Qualifier(Constants.WATCHLIST_DAO)
public class WatchlistDaoImpl implements WatchlistDao {
    private static final Logger LOGGER = LogManager.getLogger(WatchlistDaoImpl.class);

    @Qualifier(Constants.KEY_DIVIDEND_MONGO_TEMPLATE)
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    DividendDao dividendDao;

    public boolean addToWatchlist(Watchlist watchlist) {
        String watchlistId = UUID.randomUUID().toString();
        watchlist.setWatchlistId(watchlistId);
        Watchlist watchlistDoc = mongoTemplate.save(watchlist, Constants.WATCHLIST_COLLECTION);
        if(watchlistDoc != null)
            return true;
        else
            return false;
    }

    public boolean removeFromWatchlist(Watchlist watchlist) {
        Query query = new Query();
        query.addCriteria(Criteria.where(UserSchemaConstants.userId).is(watchlist.getUserId()));
        query.addCriteria(Criteria.where(TickerSchemaConstants.tickerSymbol).is(watchlist.getTickerSymbol()));
        query.fields().exclude("_id");
        query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);
        Document hint = new Document();
        hint.put(UserSchemaConstants.userId, 1.0);
        hint.put(TickerSchemaConstants.tickerSymbol, 1.0);
        query.withHint(hint);

        DeleteResult deleteResult = mongoTemplate.remove(query,Constants.WATCHLIST_COLLECTION);
        if(deleteResult != null && deleteResult.getDeletedCount() > 0)
            return true;
        else
            return false;
    }




    public List<Watchlist> getWatchlistByUserId(String userId)
    {
        List<Watchlist> watchlists = null;

        Query query = new Query();
        query.addCriteria(Criteria.where(UserSchemaConstants.userId).is(userId));
        query.fields().exclude("_id");
        query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);
        Document hint = new Document();
        hint.put(UserSchemaConstants.userId, 1.0);
        query.withHint(hint);


        watchlists = mongoTemplate.find(query,Watchlist.class,Constants.WATCHLIST_COLLECTION);

        List<String> tickerSymbols = watchlists.stream().map(w -> w.getTickerSymbol()).collect(Collectors.toList());
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);

        List<HistoricalData> historicalDataList = dividendDao.getHistoricalData(tickerSymbols, cal.getTime(),new Date());
        if(historicalDataList != null && !historicalDataList.isEmpty()) {
            Map<String, List<HistoricalData>> historicalDataMap = historicalDataList.stream().collect(Collectors.groupingBy(HistoricalData::getTickerSymbol));

            for (Watchlist watchlist : watchlists) {
                if(watchlist.getTickerSymbol() != null && !watchlist.getTickerSymbol().isEmpty()
                        && historicalDataMap != null && historicalDataMap.containsKey(watchlist.getTickerSymbol()))
                {
                    List<HistoricalData> tempList = new ArrayList<HistoricalData>();
                    tempList.add(historicalDataMap.get(watchlist.getTickerSymbol()).get(historicalDataMap.get(watchlist.getTickerSymbol()).size()-1));
                    watchlist.setHistoricalDataList(tempList);
                }
            }

        }
        return watchlists;
    }


    public List<Watchlist> compareWatchlist(List<String> watchlistIds)
    {
        List<Watchlist> watchlists = null;

        Query query = new Query();
        query.addCriteria(Criteria.where(UserSchemaConstants.watchlistId).is(watchlistIds));
        query.fields().exclude("_id");
        query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);
        Document hint = new Document();
        hint.put(UserSchemaConstants.userId, 1.0);
        query.withHint(hint);


        watchlists = mongoTemplate.find(query,Watchlist.class,Constants.WATCHLIST_COLLECTION);

        List<String> tickerSymbols = watchlists.stream().map(w -> w.getTickerSymbol()).collect(Collectors.toList());
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -4);

        List<HistoricalData> historicalDataList = dividendDao.getHistoricalData(tickerSymbols, cal.getTime(),new Date());
        if(historicalDataList != null && !historicalDataList.isEmpty()) {
            Map<String, List<HistoricalData>> historicalDataMap = historicalDataList.stream().collect(Collectors.groupingBy(HistoricalData::getTickerSymbol));

            for (Watchlist watchlist : watchlists) {
                if(watchlist.getTickerSymbol() != null && !watchlist.getTickerSymbol().isEmpty()
                        && historicalDataMap != null && historicalDataMap.containsKey(watchlist.getTickerSymbol()))
                {
                    watchlist.setHistoricalDataList(historicalDataMap.get(watchlist.getTickerSymbol()));
                }
            }

        }
        return watchlists;
    }

    public Map<String,List<Watchlist>> getWatchlistMap()
    {
        Map<String,List<Watchlist>> watchlistMap = null;

        List<Watchlist> watchlists = null;

        Query query = new Query();
        query.addCriteria(Criteria.where(WatchlistSchemaConstants.priceCap).ne(null));
        query.fields().exclude("_id");
        query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);
        Document hint = new Document();
        hint.put(WatchlistSchemaConstants.priceCap, 1.0);
        query.withHint(hint);


        watchlists = mongoTemplate.find(query,Watchlist.class,Constants.WATCHLIST_COLLECTION);

        watchlistMap = watchlists.stream().collect(Collectors.groupingBy(Watchlist::getTickerSymbol));


        return watchlistMap;
    }

}
