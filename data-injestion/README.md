#Phoenix Injestion

This microservice runs on one foundation and provides a secure endpoint to injest data colleceted by the data-collector module.

### Getting Started
The injestion microservice depends 1 user provided service and one RDMS service:
* security - this sets the username and password that the collectors will use to interact with this service (Basic Authentication)
* phoenix-db - this is a mysql service instance that persists the data!

To create these provided services simply run the following commands (note the mysql service may change depending on your marketplace):
```
cf cups security -p '{ "user" : "user", "password" : "password"}'
cf cs p.mysql db-medium phoenix-db
```