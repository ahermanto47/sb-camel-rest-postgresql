# sb-camel-rest-postgresql
sb-camel-rest-postgresql

1. Install Jenkins
oc process openshift//jenkins-ephemeral | oc apply -f-

2. Cleanup
./cleanup.sh

3. Install pipeline
oc apply -f manifests/jenkins-pipeline.yaml