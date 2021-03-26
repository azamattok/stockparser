create table stock_quota
(
   id                 bigserial             not null primary key,
  created_date       timestamp             not null,
  last_modified_date timestamp             not null,
    is_removed         boolean default false not null,
  binance_id bigint,
  price numeric,
  qty  numeric,
  quote_qty numeric,
  time bigint,
  is_buyer_maker boolean,
  is_best_match boolean,
  currency_pair varchar(255)
);

create table candlestick_data
(
   id                 bigserial             not null primary key,
   open_time bigint,
   open varchar(1000),
   high varchar(1000),
   low varchar(1000),
   close varchar(1000),
   volume varchar(1000),
   close_time bigint,
   quote_asset_volume varchar(1000),
   number_of_trades bigint,
   taker_buy_base_asset_volume varchar(1000),
   taker_buy_quote_asset_volume varchar(1000),
   symbol varchar(255)
);