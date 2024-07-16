package com.keydividend.rest;


import com.keydividend.entity.TickerExecutive;
import com.keydividend.entity.TickerFinancials;
import com.keydividend.entity.TickerSymbol;
import com.keydividend.mapper.SearchTickerMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author rupau
 *
 */
@Service(FinancialModelingPrepRestService.BEAN_ID)
public class FinancialModelingPrepRestService implements StocksRestService {
    public static final String BEAN_ID = "FinancialModelingPrep";

    private static final Logger logger = LoggerFactory.getLogger(FinancialModelingPrepRestService.class);

    private static final String apiKey = "apikey=85c16b522d26bff90e8a7dee43216ac1";
    private static final String symbolsUrl = "https://financialmodelingprep.com/api/v3/financial-statement-symbol-lists";
    private static final String searchTickerUrl = "https://financialmodelingprep.com/api/v3/search?limit=10&exchange=NASDAQ";
    private static final String companyProfileUrl = "https://financialmodelingprep.com/api/v3/profile/";
    private static final String stockGradeUrl = "https://financialmodelingprep.com/api/v3/grade/";
    private static final String executivesUrl = "https://financialmodelingprep.com/api/v3/key-executives/";
    private static final String financialMetricsUrl = "https://financialmodelingprep.com/api/v3/key-metrics/";

    private static final String fullStockPriceUrl = "https://financialmodelingprep.com/api/v3/quote/";
    private static final String batchPriceUrl = "https://financialmodelingprep.com/api/v4/batch-pre-post-market/";
    private static final String stockPriceUrl = "https://financialmodelingprep.com/api/v3/quote-short/";
    private static final String stockPriceChangeUrl = "https://financialmodelingprep.com/api/v3/stock-price-change/";
    private static final String dividendPriceUrl = "https://financialmodelingprep.com/api/v3/historical-price-full/stock_dividend/";
    private static final String multiCompanyStockPriceUrl = "https://financialmodelingprep.com/api/v3/quote/AAPL,MSFT";

    private static final String historicalStockPriceUrl = "https://financialmodelingprep.com/api/v3/historical-price-full/AAPL";
    private static final String historicalChartUrl =  "https://financialmodelingprep.com/api/v3/historical-chart/5min/AAPL?from=2023-08-10&to=2023-09-10";

    private static final String stockNewsUrl = "https://financialmodelingprep.com/api/v3/stock_news?page=0";
    private static final String stockNewsWithSentimentsUrl = "https://financialmodelingprep.com/api/v4/stock-news-sentiments-rss-feed?page=0";


    public JSONArray getResponse(String url)
    {
        JSONArray respJson = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = null;
            response = restTemplate.getForEntity(url, String.class);
            if(response.getStatusCode().equals(HttpStatus.OK)) {
                logger.info("success : "+url+" Response Body : "+response.getBody());
                respJson = new JSONArray(response.getBody());
                System.out.println(respJson);
                System.out.println(respJson.length());
            }else {
                logger.info("failed : "+response.getBody());
            }
        } catch (Exception e){
            logger.error("Failed:::"+e.getMessage());
            e.printStackTrace();
        }
        return respJson;
    }

    public List<TickerSymbol> getSymbols()
    {
        List<TickerSymbol> tickerSymbols = new ArrayList<TickerSymbol>();
        try {
            JSONArray respJson = getResponse(symbolsUrl+"?"+apiKey);

            if(respJson != null && respJson.length() > 0) {
                for(int i=0;i<respJson.length();i++) {
                    TickerSymbol tickerSymbol = new TickerSymbol();
                    tickerSymbol.setTickerSymbol(respJson.get(i).toString());

                    tickerSymbol = getCompanyProfile(tickerSymbol);
                    tickerSymbol = getExecutives(tickerSymbol);

                    tickerSymbols.add(tickerSymbol);
                }
            }
        } catch (Exception e){
            logger.error("Failed:::"+e.getMessage());
            e.printStackTrace();
        }
        return tickerSymbols;
    }


    public TickerSymbol getCompanyProfile(TickerSymbol tickerSymbol)
    {
        try {
            if(tickerSymbol == null || tickerSymbol.getTickerSymbol() == null || tickerSymbol.getTickerSymbol().isEmpty())
                return null;
            JSONArray respJson = getResponse(companyProfileUrl+tickerSymbol.getTickerSymbol()+"?"+apiKey);
            if(respJson != null) {
                if(respJson != null && respJson.length() > 0) {
                    System.out.println(respJson.get(0).toString());

                    JSONObject respJsonObj = new JSONObject(respJson.get(0).toString());
                    new SearchTickerMapper().convertCompanyProfile(respJsonObj, tickerSymbol);
                }
            }
        } catch (Exception e){
            logger.error("Failed:::"+e.getMessage());
            e.printStackTrace();
        }
        return tickerSymbol;
    }

    public TickerSymbol getStockGrade(TickerSymbol tickerSymbol)
    {
        try {
            if(tickerSymbol == null || tickerSymbol.getTickerSymbol() == null || tickerSymbol.getTickerSymbol().isEmpty())
                return null;
            JSONArray respJson = getResponse(stockGradeUrl+tickerSymbol.getTickerSymbol()+"?"+apiKey);
            if(respJson != null) {
                if(respJson != null && respJson.length() > 0) {
                    System.out.println(respJson.get(0).toString());

                    JSONObject respJsonObj = new JSONObject(respJson.get(0).toString());
                    new SearchTickerMapper().convertStockGradeInfo(respJsonObj, tickerSymbol);
                }
            }
        } catch (Exception e){
            logger.error("Failed:::"+e.getMessage());
            e.printStackTrace();
        }
        return tickerSymbol;
    }

    public TickerSymbol getExecutives(TickerSymbol tickerSymbol)
    {
        try {
            if(tickerSymbol == null || tickerSymbol.getTickerSymbol() == null || tickerSymbol.getTickerSymbol().isEmpty())
                return null;
            JSONArray respJson = getResponse(executivesUrl+tickerSymbol.getTickerSymbol()+"?"+apiKey);
            SearchTickerMapper searchTickerMapper =new SearchTickerMapper();
            if(respJson != null && respJson.length() > 0) {
                for(int i = 0; i < respJson.length(); i++)
                {
                    JSONObject respJsonObj = new JSONObject(respJson.get(i).toString());
                    TickerExecutive tickerExecutive = searchTickerMapper.convertExecutives(respJsonObj, tickerSymbol);
                    tickerSymbol.addTickerExecutive(tickerExecutive);
                }
            }

        } catch (Exception e){
            logger.error("Failed:::"+e.getMessage());
            e.printStackTrace();
        }
        return tickerSymbol;
    }

    public TickerSymbol getFinancials(TickerSymbol tickerSymbol)
    {
        try {
            if(tickerSymbol == null || tickerSymbol.getTickerSymbol() == null || tickerSymbol.getTickerSymbol().isEmpty())
                return null;
            JSONArray respJson = getResponse(financialMetricsUrl+tickerSymbol.getTickerSymbol()+"?period=annual"+apiKey);
            SearchTickerMapper searchTickerMapper =new SearchTickerMapper();
            if(respJson != null && respJson.length() > 0) {

                for(int i = 0; i < respJson.length(); i++)
                {
                    JSONObject respJsonObj = new JSONObject(respJson.get(i).toString());
                    TickerFinancials tickerFinancials = searchTickerMapper.convertFinancialMetrics(respJsonObj, tickerSymbol);
                    tickerSymbol.addTickerFinancials(tickerFinancials);
                }
            }
        } catch (Exception e){
            logger.error("Failed:::"+e.getMessage());
            e.printStackTrace();
        }
        return tickerSymbol;
    }


    public TickerSymbol getStockPrice(String tickerSymbol)
    {
        try {
            JSONArray respJson = getResponse(stockPriceUrl+tickerSymbol+"?"+apiKey);
            if(respJson != null && respJson.length() > 0)
                {
                    for(int i = 0; i < respJson.length(); i++)
                    {
                        JSONObject respJsonObj = new JSONObject(respJson.get(i).toString());
                        System.out.println(respJsonObj);
                    }
                }


        } catch (Exception e){
            logger.error("Failed:::"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    public TickerSymbol getFullStockPrice(String tickerSymbol)
    {
        try {
            JSONArray respJson = getResponse(fullStockPriceUrl+tickerSymbol+"?"+apiKey);
        } catch (Exception e){
            logger.error("Failed:::"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<TickerSymbol> getBatchStockPrice(List<String> tickerSymbols)
    {
        try {
            JSONArray respJson = getResponse(batchPriceUrl+tickerSymbols+"?"+apiKey);
        } catch (Exception e){
            logger.error("Failed:::"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void getDividendPrice(String tickerSymbol)
    {
        try {
            JSONArray respJson = getResponse(dividendPriceUrl+tickerSymbol+"?"+apiKey);
            if(respJson != null && respJson.length() > 0) {

            }
        } catch (Exception e){
            logger.error("Failed:::"+e.getMessage());
            e.printStackTrace();
        }
    }

    public TickerSymbol searchTicker(String tickerSymbol)
    {
        TickerSymbol objTickerSymbol = null;
        try {
            JSONArray respJson = getResponse(searchTickerUrl+"&"+apiKey+"&query="+tickerSymbol);
            if(respJson != null && respJson.length() > 0)
            {
                SearchTickerMapper searchTickerMapper =new SearchTickerMapper();
                for(int i = 0; i < respJson.length(); i++)
                {
                    JSONObject respJsonObj = new JSONObject(respJson.get(i).toString());
                    System.out.println(respJsonObj);
                    objTickerSymbol = searchTickerMapper.convertCompanyProfile(respJsonObj,null);

                }
            }

        } catch (Exception e){
            logger.error("Failed:::"+e.getMessage());
            e.printStackTrace();
        }
        return objTickerSymbol;
    }

    public void getStockNews(List<String> ticketSymbols)
    {

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = null;
            String newsUrl = stockNewsUrl+"&"+apiKey;
            if(ticketSymbols != null && !ticketSymbols.isEmpty())
                newsUrl += "&tickers="+String.join(",", ticketSymbols);

            response = restTemplate.getForEntity(newsUrl, String.class);
            if(response.getStatusCode().equals(HttpStatus.OK)) {
                logger.info("success : "+newsUrl+" Response Body : "+response.getBody());
                JSONArray respJsonArray = new JSONArray(response.getBody());
                if(respJsonArray != null && respJsonArray.length() > 0)
                {
                    for(int i = 0; i < respJsonArray.length(); i++)
                    {
                        JSONObject respJson = new JSONObject(respJsonArray.get(i).toString());
                        System.out.println(respJson);
                    }
                }
            }else {
                logger.info("failed : "+response.getBody());
            }
        } catch (Exception e){
            logger.error("Failed:::"+e.getMessage());
            e.printStackTrace();
        }
    }

    public void getStockNewsWithSentiments()
    {

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = null;
            response = restTemplate.getForEntity(stockNewsWithSentimentsUrl+"&"+apiKey, String.class);
            if(response.getStatusCode().equals(HttpStatus.OK)) {
                logger.info("success : "+stockNewsWithSentimentsUrl+"&"+apiKey+" Response Body : "+response.getBody());
                JSONArray respJsonArray = new JSONArray(response.getBody());
                if(respJsonArray != null && respJsonArray.length() > 0)
                {
                    for(int i = 0; i < respJsonArray.length(); i++)
                    {
                        JSONObject respJson = new JSONObject(respJsonArray.get(i).toString());
                        System.out.println(respJson);
                    }
                }
            }else {
                logger.info("failed : "+response.getBody());
            }
        } catch (Exception e){
            logger.error("Failed:::"+e.getMessage());
            e.printStackTrace();
        }
    }
}
