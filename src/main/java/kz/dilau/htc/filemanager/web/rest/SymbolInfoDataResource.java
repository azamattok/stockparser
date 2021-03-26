package kz.dilau.htc.filemanager.web.rest;

import kz.dilau.htc.filemanager.service.CandlestickDataService;
import kz.dilau.htc.filemanager.service.SymbolInfoDataService;
import kz.dilau.htc.filemanager.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.OPEN_API_PATH)
@RequiredArgsConstructor
@Slf4j
public class SymbolInfoDataResource {
    private final SymbolInfoDataService symbolInfoDataService;

    @GetMapping("/updateAllSymbolInfo")
    public void updateAllSymbolInfo() {
        symbolInfoDataService.updateAllSymbolInfo();
    }




}
