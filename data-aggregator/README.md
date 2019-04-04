# Phoenix Data Aggregator

This microservice runs on one foundation and is responsible for aggregating raw foundation data on a scheduled basis.

### Getting Started
The aggregator microservice depends on one RDBMS service (that contains the raw data to be aggregated):
* phoenix-db - this is a mysql service instance that persists the data!

This db should already be created by the data-ingestor module.


To control how often data is aggregated the environment variable CRONEXPRESSION can be set. 

Examples of this are:
```
0 0/30 * * * *  (run every 30 minutes)
0 0 0/1 * * *  (run every 1 hour)
0 0 6 * * *  (run every day at 6am)
```

n.b. This should run after the collectors have finished sending data to ingestor microservice.

This microservice is also responsible for generating the db schema. For this we are using flyway and all scripts can be found underneath src/main/resources/db/migration.

To generate a schema from the Data Model, please see instructions [here](../data-model/README.md).