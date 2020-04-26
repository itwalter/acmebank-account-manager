CREATE TABLE IF NOT EXISTS balances (
    id                              BIGINT UNSIGNED AUTO_INCREMENT,
    account                         BIGINT UNSIGNED NOT NULL,
    balance                         DECIMAL(20,2) DEFAULT '0',
    currency                        ENUM('HKD') NOT NULL,
    last_update_time                DATETIME,
    PRIMARY KEY (id),
    CONSTRAINT UNIQUE_ACCOUNT_NAME UNIQUE (account)
) ENGINE=InnoDB;

CREATE INDEX IF NOT EXISTS IDX_ACCOUNT_NAME ON balances (account);