package com.keydividend.dao.impl;



import java.util.*;



import com.keydividend.util.ExceptionUtil;
import com.keydividend.util.MongoDBUtil;
import com.keydividend.entity.*;
import com.mongodb.client.result.DeleteResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.keydividend.dao.DividendDao;
import com.keydividend.constants.Constants;
import com.keydividend.constants.PortfolioHoldingSchemaConstants;
import com.keydividend.constants.TickerSchemaConstants;
import com.keydividend.constants.UserSchemaConstants;
import com.mongodb.client.result.UpdateResult;


/**
 * 
 * @author rupau
 *
 */

@Component
@Qualifier(Constants.DIVIDEND_DAO)
public class DividendDaoImpl implements DividendDao {
	private static final Logger LOGGER = LogManager.getLogger(DividendDaoImpl.class);
	
	@Qualifier(Constants.KEY_DIVIDEND_MONGO_TEMPLATE)
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	@Override
	public TickerSymbol getTickerSymbol(String ticker) {
		TickerSymbol tickerSymbol = null;
		try {
			Query query = new Query();
			query.addCriteria( Criteria.where(TickerSchemaConstants.tickerSymbol).is(ticker));
			query.fields().exclude("_id");
			query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);
			
			Document hint = new Document();
			hint.put(TickerSchemaConstants.tickerSymbol, 1.0);
			
			query.withHint(hint);
			Document tickerSymbolDoc = mongoTemplate.findOne(query, Document.class, Constants.TICKER_SYMBOL_COLLECTION);
			mongoTemplate.getConverter().write(tickerSymbol, tickerSymbolDoc);

		}catch (Exception e) {
			LOGGER.error(ExceptionUtil.getDetails(e));
		} 
		return tickerSymbol;
	}
	
	@Override
	public TickerSymbol saveTickerSymbol(TickerSymbol tickerSymbol)
	{
		TickerSymbol objTickerSymbol = mongoTemplate.save(tickerSymbol, Constants.TICKER_SYMBOL_COLLECTION);
		return objTickerSymbol;
	}

	public boolean addPortfolio(Portfolio portfolio)
	{
		String portfolioId = UUID.randomUUID().toString();
		portfolio.setPortfolioId(portfolioId);
		Portfolio portfolioDoc = mongoTemplate.save(portfolio, Constants.PORTFOLIO_COLLECTION);
		if(portfolioDoc != null)
			return true;
		else
			return false;
	}

	public boolean updatePortfolio(Portfolio portfolio)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("portfolioId").is(portfolio.getPortfolioId()));
		query.fields().exclude("_id");
		query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);
		Document hint = new Document();
		hint.put(PortfolioHoldingSchemaConstants.portfolioId, 1.0);
		query.withHint(hint);

		Gson gson = new Gson();
		Document portfolioDocument = Document.parse(gson.toJson(portfolio));
		Update update = MongoDBUtil.fromDocumentExcludeNullFields(portfolioDocument);
		UpdateResult  updateResult = mongoTemplate.updateFirst(query, update, Portfolio.class, Constants.PORTFOLIO_COLLECTION);
		if(updateResult != null && updateResult.getModifiedCount() == 1)
			return true;
		else
			return false;
	}

	public boolean removePortfolio(String portfolioId) {
		{
			Query query = new Query();
			query.addCriteria(Criteria.where("portfolioId").is(portfolioId));
			query.fields().exclude("_id");
			query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);
			Document hint = new Document();
			hint.put(PortfolioHoldingSchemaConstants.portfolioId, 1.0);
			query.withHint(hint);

			DeleteResult deleteResult = mongoTemplate.remove(query,Constants.PORTFOLIO_COLLECTION);
			if(deleteResult != null && deleteResult.getDeletedCount() > 0)
				return true;
			else
				return false;
		}
	}
	
	public boolean addHolding(Holding holding)
	{
		String holdingId = UUID.randomUUID().toString();
		holding.setHoldingId(holdingId);
		Holding holdingDoc = mongoTemplate.save(holding, Constants.HOLDING_COLLECTION);
		if(holdingDoc != null)
			return true;
		else
			return false;
	}
	public boolean updateHolding(Holding holding)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("holdingId").is(holding.getHoldingId()));
		query.fields().exclude("_id");
		query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);
		Document hint = new Document();
		hint.put(PortfolioHoldingSchemaConstants.holdingId, 1.0);
		query.withHint(hint);

		Gson gson = new Gson();
		Document holdingDocument = Document.parse(gson.toJson(holding));
		Update update = MongoDBUtil.fromDocumentExcludeNullFields(holdingDocument);
		UpdateResult  updateResult = mongoTemplate.updateFirst(query, update, Holding.class, Constants.HOLDING_COLLECTION);
		if(updateResult != null && updateResult.getModifiedCount() == 1)
			return true;
		else
			return false;
	}

	public boolean removeHolding(String holdingId) {
			Query query = new Query();
			query.addCriteria(Criteria.where("holdingId").is(holdingId));
			query.fields().exclude("_id");
			query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);
			Document hint = new Document();
			hint.put(PortfolioHoldingSchemaConstants.holdingId, 1.0);
			query.withHint(hint);

			DeleteResult deleteResult = mongoTemplate.remove(query,Constants.HOLDING_COLLECTION);
			if(deleteResult != null && deleteResult.getDeletedCount() > 0)
				return true;
			else
				return false;
	}

	public List<Portfolio> getPortfoliosByUserId(String userId)
	{
		List<Portfolio> portfolios = null;
		try {
			Query query = new Query();
			query.addCriteria( Criteria.where(UserSchemaConstants.userId).is(userId));
			query.fields().exclude("_id");
			query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);

			Document hint = new Document();
			hint.append(UserSchemaConstants.userId,1);
			query.withHint(hint);
			portfolios = mongoTemplate.find(query,Portfolio.class, Constants.PORTFOLIO_COLLECTION);

		}catch (Exception e) {
			LOGGER.error(ExceptionUtil.getDetails(e));
		}

		return portfolios;
	}
	
	public List<Holding> getHoldingsByPortfoliIds(List<String> portfolioIds)
	{
		List<Holding> holdings = null;
		try {
			Query query = new Query();
			query.addCriteria( Criteria.where(PortfolioHoldingSchemaConstants.portfolioId).in(portfolioIds));
			query.fields().exclude("_id");
			query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);

			Document hint = new Document();
			hint.append(PortfolioHoldingSchemaConstants.portfolioId,1);
			query.withHint(hint);
			holdings = mongoTemplate.find(query,Holding.class, Constants.HOLDING_COLLECTION);

		}catch (Exception e) {
			LOGGER.error(ExceptionUtil.getDetails(e));
		}
		
		return holdings;
	}


	public List<HistoricalData> getHistoricalData(List<String> tickerSymbols, Date fromDate, Date toDate)
	{
		List<HistoricalData> historicalData = null;

		Query query = new Query();
		query.addCriteria(Criteria.where(TickerSchemaConstants.tickerSymbol).in(tickerSymbols));
		query.addCriteria(Criteria.where(TickerSchemaConstants.stockDate).gt(fromDate));
		query.addCriteria(Criteria.where(TickerSchemaConstants.stockDate).lt(toDate));
		query.fields().exclude("_id");
		query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);
		Document hint = new Document();
		hint.put(TickerSchemaConstants.tickerSymbol, 1.0);
		hint.put(TickerSchemaConstants.stockDate, 1.0);
		query.withHint(hint);


		historicalData = mongoTemplate.find(query,HistoricalData.class,Constants.HISTORICAL_DATA_COLLECTION);

		return historicalData;

	}


	public List<TickerSymbol> getTickerSymbols(List<String> tickerSymbols)
	{
		List<TickerSymbol> tickerSymbolList = null;
		try {
			Query query = new Query();
			query.addCriteria( Criteria.where(TickerSchemaConstants.tickerSymbol).in(tickerSymbols));
			query.fields().exclude("_id");
			query.maxTimeMsec(Constants.MONGO_QUERY_TIMEOUT);

			Document hint = new Document();
			hint.put(TickerSchemaConstants.tickerSymbol, 1.0);

			query.withHint(hint);
			tickerSymbolList = mongoTemplate.find(query, TickerSymbol.class, Constants.TICKER_SYMBOL_COLLECTION);

		}catch (Exception e) {
			LOGGER.error(ExceptionUtil.getDetails(e));
		}
		return tickerSymbolList;
	}

}
