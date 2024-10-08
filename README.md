# eda-arq-whisper

![alt text](image.png)

# Projetos

## Uploader
Disponibiliza a funcionalidade do upload de arquivos de audio utilizando HTTP/REST, envia esses arquivos via AMQP para o ArtemisMQ.
O retorno do REST são principal é um ID que deve ser utilizado no projeto `Retriever` para buscar a transcrição

## Translator
Recupera as mensagens com os arquivos de audio do ActiveMQ, submete para o modelo que faz a transcrição e retorna o texto para o ArtemisMQ

## Retriever
Disponibiliza um modo de acessar as transcrições geradas. Nesse exemplo recuperamos as transcrições diretamente do ArtemisMQ pelo ID.

Pode ser alterado para buscar de um banco (Postgres ou MongoDB) ou Redis

## WhisperCPP
Modelo de fato que faz a transcrição.


## Running LOCAL

## Pre requisitos
- [Podman](https://podman.io/docs/installation)
- [Quarkus CLI](https://quarkus.io/guides/cli-tooling)
- Java 21

## Subir ambiente
- `docker compose up` para subir o artemis
- Executar os passos do [WHISPER](whisper/README.md) para build e run do whisper server

- `quarkus dev`, ou `./mvnw quarkus:dev` nos projetos `uploader`, `translator` e `retriever`

- Execute `./upload.sh`. 
- A saída será a identificação da transcrição, ex: `7e36958b-710d-4461-8242-80a85adb42ec`
- Para consultar o resultado da transcrição: `curl localhost:8082/transcriptions/7e36958b-710d-4461-8242-80a85adb42ec`
  - Quando não está pronta, o retorno é vazio com http status 404

## Console WEB Artemis
http://localhost:8161/console/

Não funciona localmente ainda por conta de CORS

# OpenShift com whispercpp

- Instalar operador: Red Hat Integration - AMQ Broker for RHEL 8 (Multiarch)
- Instalar Operador: Data Grid
- Aplicar os yamls da pasta `yamls` para fazer deploy do artemis, cache e whisper
- Fazer login no OpenShift na linha de comando, ex: `oc login --token=sha256~...`
- URL da console do Artemis: `echo 'http://'$(oc get route artemis-wconsj-0-svc-rte -o=jsonpath='{.spec.host}' -n whisper)`
  - username e password: `admin` (está no yaml do Artemis)
  - Essa Console demora um pouco para ficar disponível
- URL da console do Cache: `echo 'http://'$(oc get route infinispan-external -o=jsonpath='{.spec.host}')`
  - username e password: quarkus/quarkus
- Deploy das aplicações Quarkus/Camel: 
  - Executar `bash deploy-all.sh`
  - Outra opção seria gerar imagens das aplicações e fazer o deploy por dentro da console web do OpenSHift: Todo projeto tem um arquivo `src/main/docker/Dockerfile.jvm` que explica como criar uma imagem daquele projeto
- URL do UPLOADER: `UPLOADER='http://'$(oc get route uploader -o=jsonpath='{.spec.host}' -n whisper)`
- URL do Swagger do UPLOADER: `echo $UPLOADER/q/swagger-ui`
  - Como submeter um arquivo:
    - `TRANS_ID=$(curl --header "Content-Type:application/octet-stream" --data-binary @samples/jfk.wav $UPLOADER/audios)`
    - Utilizar swagger
- URL do RETRIEVER:
  - `RETRIEVER='http://'$(oc get route retriever -o=jsonpath='{.spec.host}' -n whisper)`
  - `curl $RETRIEVER/transcriptions/$TRANS_ID`


# IMPORTANTE
- NÃO implantar assim em produção, vários aspectos importantes não foram feitos do melhor modo nessa PoC
- Exemplo: conexão ao cache está SEM TLS, acesso ao artemis está sem login e senha...

## Fontes

- https://github.com/ggerganov/whisper.cpp/
- https://ahmetoner.com/whisper-asr-webservice/run/
- https://github.com/viniciusfcf/ai-lab-recipes

- https://github.com/containers/ai-lab-recipes/blob/main/models/Containerfile
- https://learnopencv.com/fine-tuning-whisper-on-custom-dataset/

