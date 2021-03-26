package kz.dilau.htc.filemanager.domain.dictionary;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "symbol_status_dict")
public class SymbolStatusDict extends BaseCustomDictionary {
    public static final Long PRE_TRADING = 1L;
    public static final Long TRADING = 2L;
    public static final Long POST_TRADING = 3L;
    public static final Long END_OF_DAY = 4l;
    public static final Long HALT = 5L;
    public static final Long AUCTION_MATCH = 6L;
    public static final Long BREAK = 7L;
}
