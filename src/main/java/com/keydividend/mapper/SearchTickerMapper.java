package com.keydividend.mapper;


import com.keydividend.entity.StockPrice;
import com.keydividend.entity.TickerExecutive;
import com.keydividend.entity.TickerFinancials;
import com.keydividend.entity.TickerSymbol;
import com.keydividend.util.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author rupau
 *
 */

public class SearchTickerMapper {
    private static final Logger LOGGER = LogManager.getLogger(SearchTickerMapper.class);

    public TickerSymbol convertCompanyProfile(JSONObject respJson, TickerSymbol tickerSymbol)
    {
        try {
            if(tickerSymbol == null)
                tickerSymbol = new TickerSymbol();
            tickerSymbol.setTickerSymbol(respJson.getString("symbol"));

            tickerSymbol.setExchange(respJson.getString("exchange"));
            tickerSymbol.setExchangeShortName(respJson.getString("exchangeShortName"));

            tickerSymbol.setCik(respJson.getString("cik"));


            StockPrice stockPrice = new StockPrice();
            stockPrice.setPrice(respJson.getDouble("price"));
            stockPrice.setMarketCap(respJson.getDouble("mktCap"));
            tickerSymbol.setStockPrice(stockPrice);


            tickerSymbol.setCurrencyCode(respJson.getString("currency"));
            tickerSymbol.setCountryCode(respJson.getString("country"));

            tickerSymbol.setCompanyName(respJson.getString("companyName"));
            tickerSymbol.setCompanyIpoDate(respJson.getString("ipoDate"));
            tickerSymbol.setCompanyIndustry(respJson.getString("industry"));
            tickerSymbol.setCompanySector(respJson.getString("sector"));
            tickerSymbol.setWebsiteUrl(respJson.getString("website"));
            tickerSymbol.setCompanyLogoUrl(respJson.getString("image"));
            tickerSymbol.setDescription(respJson.getString("description"));
            tickerSymbol.setFullTimeEmployees(respJson.getInt("fullTimeEmployees"));
            tickerSymbol.setCompanyAddress(respJson.getString("address"));
            tickerSymbol.setState(respJson.getString("city"));
            tickerSymbol.setCity(respJson.getString("state"));
            tickerSymbol.setZip(respJson.getString("zip"));
            tickerSymbol.setPhoneNumber(respJson.getString("phone"));



        }catch (Exception e)
        {
            LOGGER.error("Failed to convertCompanyProfile" + ExceptionUtil.getDetails(e));
        }


            /*"beta": 1.264,
            "volAvg": 62897980,
            "lastDiv": 1,
            "range": "164.08-199.62",
            "changes": 0.3,
            "isin": "US0378331005",
            "cusip": "037833100",
           "ceo": "Mr. Timothy D. Cook",
            "dcfDiff": 58.68441,
            "dcf": 132.98558769111654,
            "isEtf": false,
            "isActivelyTrading": true,
            "isAdr": false,
            "isFund": false*/

        return tickerSymbol;

    }


    public TickerExecutive convertExecutives(JSONObject respJson, TickerSymbol tickerSymbol)
    {
        TickerExecutive executive = null;
        try {
            executive =  new TickerExecutive();
            executive.setTitle(respJson.getString("title"));
            executive.setName(respJson.getString("name"));
            executive.setTitle(respJson.getString("pay"));
            executive.setTitle(respJson.getString("currencyPay"));
            executive.setTitle(respJson.getString("gender"));
            executive.setTitle(respJson.getString("yearBorn"));


        }catch (Exception e)
        {
            LOGGER.error("Failed to convertExecutives" + ExceptionUtil.getDetails(e));
        }

        return executive;

    }



    public TickerFinancials convertFinancialMetrics(JSONObject respJson, TickerSymbol tickerSymbol)
    {
        TickerFinancials tickerFinancials = null;
        try {
            tickerFinancials =  new TickerFinancials();
            tickerFinancials.setNetIncome(respJson.getDouble("netIncomePerShare"));



        }catch (Exception e)
        {
            LOGGER.error("Failed to convertExecutives" + ExceptionUtil.getDetails(e));
        }

        return tickerFinancials;

        /*
        {
		"symbol": "AAPL",
		"date": "2022-09-24",
		"calendarYear": "2022",
		"period": "FY",
		"revenuePerShare": 24.31727304755197,
		"netIncomePerShare": 6.154614437637777,
		"operatingCashFlowPerShare": 7.532762624088375,
		"freeCashFlowPerShare": 6.872425646259799,
		"cashPerShare": 2.9787931805221803,
		"bookValuePerShare": 3.124822127430853,
		"tangibleBookValuePerShare": 3.124822127430853,
		"shareholdersEquityPerShare": 3.124822127430853,
		"interestDebtPerShare": 7.585118441624466,
		"marketCap": 2439367314090,
		"enterpriseValue": 2535790314090,
		"peRatio": 24.441823533260525,
		"priceToSalesRatio": 6.186137718067193,
		"pocfratio": 19.970096962693717,
		"pfcfRatio": 21.888923611981014,
		"pbRatio": 48.14034011071204,
		"ptbRatio": 48.14034011071204,
		"evToSales": 6.430662580618166,
		"enterpriseValueOverEBITDA": 19.42524045388039,
		"evToOperatingCashFlow": 20.75947240783948,
		"evToFreeCashFlow": 22.754146192134094,
		"earningsYield": 0.040913477615088595,
		"freeCashFlowYield": 0.04568520671581333,
		"debtToEquity": 2.3695334701610355,
		"debtToAssets": 0.3403750478377344,
		"netDebtToEBITDA": 0.738641499605488,
		"currentRatio": 0.8793560286267226,
		"interestCoverage": 40.74957352439441,
		"incomeQuality": 1.2239211246154926,
		"dividendYield": 0.006083954603424043,
		"payoutRatio": 0.14870294480125848,
		"salesGeneralAndAdministrativeToRevenue": 0,
		"researchAndDdevelopementToRevenue": 0.06657148363798665,
		"intangiblesToTotalAssets": 0,
		"capexToOperatingCashFlow": -0.08766199212450164,
		"capexToRevenue": -0.02715505873283155,
		"capexToDepreciation": -0.9643371757925072,
		"stockBasedCompensationToRevenue": 0.022920005680550203,
		"grahamNumber": 20.801963754945305,
		"roic": 0.5861678044404918,
		"returnOnTangibleAssets": 0.2829244092925685,
		"grahamNetNet": -12.67929632054538,
		"workingCapital": -18577000000,
		"tangibleAssetValue": 50672000000,
		"netCurrentAssetValue": -166678000000,
		"investedCapital": 2.3695334701610355,
		"averageReceivables": 56219000000,
		"averagePayables": 59439000000,
		"averageInventory": 5763000000,
		"daysSalesOutstanding": 56.400204905560855,
		"daysPayablesOutstanding": 104.6852773031054,
		"daysOfInventoryOnHand": 8.07569806661716,
		"receivablesTurnover": 6.471607693822622,
		"payablesTurnover": 3.486641191608828,
		"inventoryTurnover": 45.19733117670845,
		"roe": 1.9695887275023682,
		"capexPerShare": -0.6603369778285755
	}
         */

    }

    public TickerSymbol convertStockGradeInfo(JSONObject respJson, TickerSymbol tickerSymbol)
    {
        try {
            tickerSymbol.setStockGrade(respJson.getString("newGrade"));
            tickerSymbol.setStockPrevGrade(respJson.getString("previousGrade"));
            tickerSymbol.setStockGradingCompany(respJson.getString("gradingCompany"));
        }catch (Exception e){
            LOGGER.error("Failed to convertStockGradeInfo" + ExceptionUtil.getDetails(e));
        }
        return tickerSymbol;
    }
}
