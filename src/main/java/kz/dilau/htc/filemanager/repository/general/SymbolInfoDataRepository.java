package kz.dilau.htc.filemanager.repository.general;

import kz.dilau.htc.filemanager.domain.general.SymbolInfoData;
import kz.dilau.htc.filemanager.domain.market.CandlestickData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SymbolInfoDataRepository extends JpaRepository<SymbolInfoData, Long>, JpaSpecificationExecutor<SymbolInfoData> {
}
