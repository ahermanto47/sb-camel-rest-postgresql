#!/bin/bash

oc delete deploymentconfig sb-camel-rest-postgresql

oc delete buildconfig sb-camel-rest-postgresql

oc delete imagestream sb-camel-rest-postgresql

oc delete service sb-camel-rest-postgresql

oc delete route sb-camel-rest-postgresql

oc delete buildconfig sb-camel-rest-postgresql-pipeline


