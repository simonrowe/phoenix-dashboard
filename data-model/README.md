# Shared Data Model

This module contains entity objects used in the various microservices.

To get some help with generation of the flyway scripts you can run the following maven commands:

```
mvn compile jpa2ddl:generate
``` 
and generated scripts will appear in target/schema.sql