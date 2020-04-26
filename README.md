# Getting Started

## Example in Postman

Please refer to "ACMEBANK Account Manager.postman_collection.json"

## Known issue

Live FX rate has not been implemented. All transfers are considered as same currency.

CAS is used for updating balance. Not a good choice if there is more updates than queries.

The persistence is the performance bottleneck. In-memory database/cache should be introduced.

In order to ensure accuracy, the addition and deduction are not performned in SQL level, and BigDecimal calculation is enforced. But this approach is not good for performance, as the latency will increase per request.

Jackson/GSON/Fastjson are not used, because there are not many fields.
