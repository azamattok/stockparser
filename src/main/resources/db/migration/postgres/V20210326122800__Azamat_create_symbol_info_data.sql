create table symbol_info_data
(
   id                 bigserial             not null primary key,
   symbol varchar(255),
   symbol_status_dict_id bigint,
   base_asset varchar(255),
   base_asset_precision integer,
   quote_asset varchar(255),
   quote_precision integer,
   iceberg_allowed boolean
);

create table symbol_status_dict (
    id bigserial not null primary key,
    name_ru varchar (255),
    name_kz varchar (255),
    name_en varchar (255),
    is_removed boolean not null
);

insert into symbol_status_dict (id,   name_en, name_kz, name_ru,  is_removed) values (1,  'PRE_TRADING', 'PRE_TRADING', 'PRE_TRADING', false );
insert into symbol_status_dict (id,   name_en, name_kz, name_ru,  is_removed) values (2,  'TRADING', 'TRADING', 'TRADING', false );
insert into symbol_status_dict (id,   name_en, name_kz, name_ru,  is_removed) values (3,  'POST_TRADING', 'POST_TRADING', 'POST_TRADING', false );
insert into symbol_status_dict (id,   name_en, name_kz, name_ru,  is_removed) values (4,  'END_OF_DAY', 'END_OF_DAY', 'END_OF_DAY', false );
insert into symbol_status_dict (id,   name_en, name_kz, name_ru,  is_removed) values (5,  'HALT', 'HALT', 'HALT', false );
insert into symbol_status_dict (id,   name_en, name_kz, name_ru,  is_removed) values (6,  'AUCTION_MATCH', 'AUCTION_MATCH', 'AUCTION_MATCH', false );
insert into symbol_status_dict (id,   name_en, name_kz, name_ru,  is_removed) values (7,  'BREAK', 'BREAK', 'BREAK', false );