package kz.dilau.htc.filemanager.repository;

import kz.dilau.htc.filemanager.domain.CandlestickData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CandlestickDataRepository extends JpaRepository<CandlestickData, Long>, JpaSpecificationExecutor<CandlestickData> {

}
