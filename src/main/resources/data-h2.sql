INSERT INTO balances (account, balance, currency, last_update_time)
SELECT '12345678', '1000000', 'HKD', now() FROM DUAL
WHERE NOT EXISTS
(SELECT account FROM balances WHERE account = '12345678');

INSERT INTO balances (account, balance, currency, last_update_time)
SELECT '88888888', '1000000', 'HKD', now() FROM DUAL
WHERE NOT EXISTS
(SELECT account FROM balances WHERE account = '88888888');