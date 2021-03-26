package kz.dilau.htc.filemanager.domain;

import kz.dilau.htc.filemanager.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "stock_quota")
@Data
public class StockQuota extends BaseEntity<Long> {
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    protected ZonedDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date", nullable = false)
    protected ZonedDateTime lastModifiedDate;

    @Column(name = "is_removed", nullable = false, columnDefinition = "boolean default false")
    private Boolean isRemoved = false;
    @Column(name = "binance_id")
    Long binanceId;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "qty")
    private BigDecimal qty;
    @Column(name = "quoteQty")
    private BigDecimal quoteQty;
    @Column(name = "time")
    private Long time;
    @Column(name = "is_buyer_maker")
    private Boolean isBuyerMaker;
    @Column(name = "is_best_match")
    private Boolean isBestMatch;
    @Column(name = "currency_pair")
    String currencyPair;
}
