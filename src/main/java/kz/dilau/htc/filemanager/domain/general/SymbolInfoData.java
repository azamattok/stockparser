package kz.dilau.htc.filemanager.domain.general;

import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.general.SymbolFilter;
import kz.dilau.htc.filemanager.domain.base.BaseEntity;
import kz.dilau.htc.filemanager.domain.dictionary.SymbolStatusDict;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "symbol_info_data")
@Data
public class SymbolInfoData extends BaseEntity<Long> {
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "symbol_status_dict_id")
    private Long statusId;
    @Column(name = "base_asset")
    private String baseAsset;
    @Column(name = "base_asset_precision")
    private Integer baseAssetPrecision;
    @Column(name = "quote_asset")
    private String quoteAsset;
    @Column(name = "quote_precision")
    private Integer quotePrecision;
    /*
        private List<OrderType> orderTypes;*/
    @Column(name = "iceberg_allowed")
    private boolean icebergAllowed;
   /*   @Column(name = "oco_allowed")
  private boolean ocoAllowed;
    @Column(name = "quote_order_qty_market_allowed")
    private boolean quoteOrderQtyMarketAllowed;
    @Column(name = "is_spot_trading_allowed")
    private boolean isSpotTradingAllowed;
    @Column(name = "is_margin_trading_allowed")
    private boolean isMarginTradingAllowed;*/
/*
    private List<SymbolFilter> filters;

 */

}
