package kz.dilau.htc.filemanager.service.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.general.SymbolInfo;
import kz.dilau.htc.filemanager.config.DataProperties;
import kz.dilau.htc.filemanager.domain.general.SymbolInfoData;
import kz.dilau.htc.filemanager.domain.market.CandlestickData;
import kz.dilau.htc.filemanager.repository.CandlestickDataRepository;
import kz.dilau.htc.filemanager.repository.general.SymbolInfoDataRepository;
import kz.dilau.htc.filemanager.service.SymbolInfoDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class SymbolInfoDataServiceImpl implements SymbolInfoDataService {
    private final SymbolInfoDataRepository symbolInfoDataRepository;
    private final DataProperties dataProperties;

    @Override
    public void updateAllSymbolInfo() {
        BinanceApiRestClient client = getBinanceApiRestClient();
        ExchangeInfo exchangeInfo = client.getExchangeInfo();
        List<SymbolInfoData> symbolInfoDataList = symbolInfoDataRepository.findAll();
        Set<String> symbolSet = new HashSet<>();
        for (SymbolInfoData symbolInfoData : symbolInfoDataList) {
            symbolSet.add(symbolInfoData.getSymbol());
        }

        for (SymbolInfo symbolInfo : exchangeInfo.getSymbols()) {
            if (!symbolSet.contains(symbolInfo.getSymbol())) {
                SymbolInfoData symbolInfoData = SymbolInfoData.builder()
                        .symbol(symbolInfo.getSymbol())
                        // .statusId(symbolInfo.getStatus())
                        .baseAsset(symbolInfo.getBaseAsset())
                        .baseAssetPrecision(symbolInfo.getBaseAssetPrecision())
                        .quoteAsset(symbolInfo.getQuoteAsset())
                        .quotePrecision(symbolInfo.getQuotePrecision())
                        .icebergAllowed(symbolInfo.isIcebergAllowed())
                        .build();
                symbolInfoDataRepository.save(symbolInfoData);
            }
        }
    }

    private BinanceApiRestClient getBinanceApiRestClient() {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(dataProperties.getBinanceApiKey(), dataProperties.getBinanceSecret());
        return factory.newRestClient();
    }
}
