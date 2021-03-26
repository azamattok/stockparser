package kz.dilau.htc.filemanager.domain;

import kz.dilau.htc.filemanager.domain.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candlestick_data")
@Data
public class CandlestickData extends BaseEntity<Long> {
    @Column(name = "open_time")
    private Long openTime;
    @Column(name = "open")
    private String open;
    @Column(name = "high")
    private String high;
    @Column(name = "low")
    private String low;
    @Column(name = "close")
    private String close;
    @Column(name = "volume")
    private String volume;
    @Column(name = "close_time")
    private Long closeTime;
    @Column(name = "quote_asset_volume")
    private String quoteAssetVolume;
    @Column(name = "number_of_trades")
    private Long numberOfTrades;
    @Column(name = "taker_buy_base_asset_volume")
    private String takerBuyBaseAssetVolume;
    @Column(name = "taker_buy_quote_asset_volume")
    private String takerBuyQuoteAssetVolume;
    @Column(name = "symbol")
    private String symbol;
}
