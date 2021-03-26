package kz.dilau.htc.filemanager.service.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import kz.dilau.htc.filemanager.config.DataProperties;
import kz.dilau.htc.filemanager.domain.market.CandlestickData;
import kz.dilau.htc.filemanager.repository.CandlestickDataRepository;
import kz.dilau.htc.filemanager.service.CandlestickDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandlestickDataServiceImpl implements CandlestickDataService {
    private final CandlestickDataRepository candlestickDataRepository;
    private final DataProperties dataProperties;


    @Override
    public void saveCandlestickFromBinanceMannually() {
        saveCandlestickFromBinance();
    }

    @Override
    public void saveCandlestickFromBinanceBySchedulle() {
        saveCandlestickFromBinance();
    }

    public void saveCandlestickFromBinance() {
        BinanceApiRestClient client = getBinanceApiRestClient();
        String symbol = "BTCUSDT";

        Long startTime = 1435317439l;
        Long endTime = 1616756475l;
        //not work
        List<Candlestick> candlestickBarsDemo = client.getCandlestickBars(symbol, CandlestickInterval.DAILY, 500, endTime, startTime);
        List<Candlestick> candlestickBarsDemo1 = client.getCandlestickBars(symbol, CandlestickInterval.DAILY, 500, startTime, endTime);
        //work
        List<Candlestick> candlestickBarsDemo2 = client.getCandlestickBars(symbol, CandlestickInterval.DAILY, 1000, null, null);
        List<Candlestick> candlestickBarsDemo3 = client.getCandlestickBars(symbol, CandlestickInterval.DAILY, 500, startTime, null);
        List<Candlestick> candlestickBarsDemo31 = client.getCandlestickBars(symbol, CandlestickInterval.DAILY, 1000, 1232949799l, null);
        //not work
        List<Candlestick> candlestickBarsDemo4 = client.getCandlestickBars(symbol, CandlestickInterval.DAILY, 500, null, endTime);
        List<Candlestick> candlestickBarsDemo5 = client.getCandlestickBars(symbol, CandlestickInterval.DAILY, null, startTime, endTime);
        //work
        List<Candlestick> candlestickBars = client.getCandlestickBars(symbol, CandlestickInterval.DAILY);
        for (Candlestick candlestick : candlestickBars) {
            CandlestickData candlestickData = CandlestickData.builder()
                    .openTime(candlestick.getOpenTime())
                    .open(candlestick.getOpen())
                    .high(candlestick.getHigh())
                    .low(candlestick.getLow())
                    .close(candlestick.getClose())
                    .volume(candlestick.getVolume())
                    .closeTime(candlestick.getCloseTime())
                    .quoteAssetVolume(candlestick.getQuoteAssetVolume())
                    .numberOfTrades(candlestick.getNumberOfTrades())
                    .takerBuyBaseAssetVolume(candlestick.getTakerBuyBaseAssetVolume())
                    .takerBuyQuoteAssetVolume(candlestick.getTakerBuyQuoteAssetVolume())
                    .symbol(symbol)
                    .build();
            candlestickDataRepository.save(candlestickData);
        }
    }

    @Override
    public void saveCandlestickFromBinanceBySymbol(String symbol, Long startTime) {
        BinanceApiRestClient client = getBinanceApiRestClient();
        Long maxCloseTime = startTime;
        List<Candlestick> candlestickBars = client.getCandlestickBars(symbol, CandlestickInterval.DAILY, 1000, startTime, null);
        List<CandlestickData> candlestickDataList = candlestickDataRepository.findAll();
        Set<Long> candlestickDataListTimeOpen = new HashSet<>();
        Set<String> candlestickDataListSymbol = new HashSet<>();
        for (CandlestickData candlestickData : candlestickDataList) {
            candlestickDataListTimeOpen.add(candlestickData.getOpenTime());
            candlestickDataListSymbol.add(candlestickData.getSymbol());
        }
        for (Candlestick candlestick : candlestickBars) {

            CandlestickData candlestickData = CandlestickData.builder()
                    .openTime(candlestick.getOpenTime())
                    .open(candlestick.getOpen())
                    .high(candlestick.getHigh())
                    .low(candlestick.getLow())
                    .close(candlestick.getClose())
                    .volume(candlestick.getVolume())
                    .closeTime(candlestick.getCloseTime())
                    .quoteAssetVolume(candlestick.getQuoteAssetVolume())
                    .numberOfTrades(candlestick.getNumberOfTrades())
                    .takerBuyBaseAssetVolume(candlestick.getTakerBuyBaseAssetVolume())
                    .takerBuyQuoteAssetVolume(candlestick.getTakerBuyQuoteAssetVolume())
                    .symbol(symbol)
                    .build();
            maxCloseTime = Math.max(maxCloseTime, candlestick.getCloseTime());
            if (candlestickDataListTimeOpen.contains(candlestick.getOpenTime()) && candlestickDataListSymbol.contains(symbol)) {
              return;
            } else {
                candlestickDataRepository.save(candlestickData);

            }

        }
        if (!candlestickBars.isEmpty()) {
            startTime = maxCloseTime / 1000 + 86400;
            saveCandlestickFromBinanceBySymbol(symbol, startTime);
        }
    }


    private BinanceApiRestClient getBinanceApiRestClient() {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(dataProperties.getBinanceApiKey(), dataProperties.getBinanceSecret());
        return factory.newRestClient();
    }
}
