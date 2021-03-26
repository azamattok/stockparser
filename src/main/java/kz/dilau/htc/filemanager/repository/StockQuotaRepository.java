package kz.dilau.htc.filemanager.repository;

import kz.dilau.htc.filemanager.domain.StockQuota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockQuotaRepository extends JpaRepository<StockQuota, Long> {
}
