#Phoenix Data Aggregator

This microservice runs on one foundation and aggregates a count of application instances (and other stats TBD), and provides a dashboard.

### Getting Started
The aggregator microservice depends 1 user provided service and one RDMS service:
* security - this sets the username and password that the collectors will use to interact with this service (Basic Authentication)
* aggregator-db - this is a mysql service instance that persists the data!

To create these provided services simply run the following commands (note the mysql service may change depending on your marketplace):
```
cf cups security -p '{ "user" : "user", "password" : "password"}'
cf cs p.mysql db-medium aggregator-db
```