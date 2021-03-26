package kz.dilau.htc.filemanager.service.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.TradeHistoryItem;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.market.*;
import kz.dilau.htc.filemanager.config.DataProperties;
import kz.dilau.htc.filemanager.repository.CandlestickDataRepository;
import kz.dilau.htc.filemanager.repository.StockQuotaRepository;
import kz.dilau.htc.filemanager.service.StockQuotaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockQuotaServiceImpl implements StockQuotaService {
    private final DataProperties dataProperties;
    private final CandlestickDataRepository candlestickDataRepository;
    private final StockQuotaRepository stockQuotaRepository;
    private final RestTemplate restTemplate;




    @Override
    public void saveQuotesFromBinanceMannually() {
        List quotes = gelQuotesFromId(getQuotesId());
    }

    @Override
    public void saveQuotesFromBinancBySchedule() {

    }


    @Override
    public ExchangeInfo getExchangeInfo() {
        BinanceApiRestClient client = getBinanceApiRestClient();
        return client.getExchangeInfo();
    }

    private BinanceApiRestClient getBinanceApiRestClient() {
        String API_KEY = "tJoTdWhpugHcQOLioppaICOZEkmsGv2tHj9A5N8APIH6nRqZ2JuxmChwsw84U1T5";
        String SECRET = "h8t94IiKP5J6AlXj6xZmTuiGbkELijbjDVHClmxomXDmYWGUXnxyYqcYtelhNOji";
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(API_KEY, SECRET);
        return factory.newRestClient();
    }

    private void saveQuotesFromBinance() {
        Long quotesId = getQuotesId();
        List quotes = gelQuotesFromId(quotesId);
        for (Object quote : quotes) {

        }
    }


    private Long getQuotesId() {
        return Long.valueOf(1);
    }


    private List gelQuotesFromId(Long quotesId) {
        String API_KEY = "tJoTdWhpugHcQOLioppaICOZEkmsGv2tHj9A5N8APIH6nRqZ2JuxmChwsw84U1T5";
        String SECRET = "h8t94IiKP5J6AlXj6xZmTuiGbkELijbjDVHClmxomXDmYWGUXnxyYqcYtelhNOji";
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(API_KEY, SECRET);
        BinanceApiRestClient client = factory.newRestClient();
        String symbol = "BTCUSDT";
        Integer limit = Integer.valueOf(500);
        Long fromId = Long.valueOf(500);
        List<TradeHistoryItem> tradeHistoryItems = client.getHistoricalTrades(symbol, limit, fromId);
        client.ping();
        long serverTime = client.getServerTime();
        System.out.println(serverTime);
        OrderBook orderBook = client.getOrderBook("NEOETH", 10);
        List<OrderBookEntry> asks = orderBook.getAsks();
        OrderBookEntry firstAskEntry = asks.get(0);
        System.out.println(firstAskEntry.getPrice() + " / " + firstAskEntry.getQty());
// Compressed/Aggregate trades list of a symbol
        List<AggTrade> aggTrades = client.getAggTrades("NEOETH");
        System.out.println(aggTrades);
        /*  Latest price of a symbol */
        TickerStatistics tickerStatistics = client.get24HrPriceStatistics("NEOETH");
        System.out.println(tickerStatistics.getLastPrice());

        List<TradeHistoryItem> trades = client.getTrades(symbol, 10000);
        //  List<Candlestick> candlestickBars = client.getCandlestickBars(symbol, 500, Long.valueOf(1585105780), Long.valueOf(1616641780));
        List<Candlestick> candlestickBars = client.getCandlestickBars(symbol, CandlestickInterval.DAILY);
        // List<Candlestick> candlestickBarsAllTime = client.getCandlestickBars(symbol, CandlestickInterval.DAILY, 1000, 1502967760, 1534475380);
        ExchangeInfo exchangeInfo = client.getExchangeInfo();
        //ошибка выходит
        //   List<Asset> allAssets = client.getAllAssets();


        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("24375bee713c57e7654dca8196f9a569");

        //  headers.setBearerAuth(getUserManagerToken());
        HttpEntity<Object> request = new HttpEntity<>(headers);

        // String url = dataProperties.getKeycloakUserManagerUrl() + USER_REST_ENDPOINT + USERS_INFO;
//https://api.binance.com
        String url = "https://api.binance.com/api/v3/historicalTrades";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);

        uriBuilder.queryParam("symbol", symbol);

        uriBuilder.queryParam("limit", limit);

        uriBuilder.queryParam("fromId", fromId);
        ResponseEntity response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<Object>() {
                }
        );

        return (List) response.getBody();
    }


}
