# Phoenix Data Collector

This microservice runs on each foundation and will send usage stats to a centrallised ingestor endpoint.

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

The collector microservice depends on 2 user provided services:
* uaa-client
* capi
* ingestor

To create these user provided services simply run the following commands:
```
cf cups uaa-client -p '{ "clientId" : "uaa-client", "clientSecret" : "uaa-client-secret", "uri" : "[REPLACE_ME_WITH_UAA_URI]" }'
cf cups capi -p '{"uri" : "[REPLACE_ME_WITH_CAPI_URI]" }'
cf cups ingestor -p '{ "uri" : "[REPLACE_ME_WITH_INGESTOR_URI]", "user" : "user", "password" : "password" }'

```

To control how often data is collected the environment variable SCHEDULER_CRONEXPRESSION. 

Examples of this are:
```
0 0/30 * * * *  (run every 30 minutes)
0 0 0/1 * * *  (run every 1 hour)
0 0 6 * * *  (run every day at 6am)
```