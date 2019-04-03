#Phoenix Data Aggregator

This microservice runs on one foundation and is responsible for aggregating raw foundation data on a scheduled basis.

### Getting Started
The aggregator microservice depends on one RDBMS service (that contains the raw data to be aggregated):
* phoenix-db - this is a mysql service instance that persists the data!

This db should already be created by the data-injestion module.