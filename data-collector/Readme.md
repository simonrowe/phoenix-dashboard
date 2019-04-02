#Phoenix Data Collector

This microservice runs on each foundation and will send usage stats to a centrallised aggregation microservice.

### Getting Started
In order to run this, you will need to create a new OAuth2 client in UAA that has the cloud_controller.admin_read_only scope.

To do this simply run the following script (please substitute the variables )
```
UAA_ENDPOINT=blah
UAA_ADMIN_CLIENT_ID=admin
UAA_ADMIN_CLIENT_SECRET=
uaac target "$UAA_ENDPOINT"
uaac token client get "$UAA_ADMIN_CLIENT_ID" -s "$UAA_ADMIN_CLIENT_SECRET"
uaac client add uaa-client --authorities cloud_controller.admin_read_only --scope cloud_controller.admin_read_only --authorized_grant_types client_credentials,password -s uaa-client-secret

```