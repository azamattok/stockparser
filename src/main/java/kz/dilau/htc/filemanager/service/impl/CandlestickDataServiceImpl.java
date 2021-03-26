package kz.dilau.htc.filemanager.service.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import kz.dilau.htc.filemanager.domain.CandlestickData;
import kz.dilau.htc.filemanager.repository.CandlestickDataRepository;
import kz.dilau.htc.filemanager.service.CandlestickDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandlestickDataServiceImpl implements CandlestickDataService {
    private final CandlestickDataRepository candlestickDataRepository;

    @Override
    public void saveCandlestickFromBinanceMannually() {
        saveCandlestickFromBinance();
    }

    @Override
    public void saveCandlestickFromBinanceBySchedulle() {
        saveCandlestickFromBinance();
    }

    public void saveCandlestickFromBinance() {
        String API_KEY = "tJoTdWhpugHcQOLioppaICOZEkmsGv2tHj9A5N8APIH6nRqZ2JuxmChwsw84U1T5";
        String SECRET = "h8t94IiKP5J6AlXj6xZmTuiGbkELijbjDVHClmxomXDmYWGUXnxyYqcYtelhNOji";
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(API_KEY, SECRET);
        BinanceApiRestClient client = factory.newRestClient();
        String symbol = "BTCUSDT";
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
}
