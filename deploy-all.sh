oc new-project whisper
cd uploader
quarkus build -Dquarkus.openshift.deploy=true
cd ../retriever
quarkus build -Dquarkus.openshift.deploy=true
cd ../translator
quarkus build -Dquarkus.openshift.deploy=true