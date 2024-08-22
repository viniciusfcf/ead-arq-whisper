# ead-arq-whisper

![alt text](image.png)

# Projetos

## Uploader
Disponibiliza a funcionalidade do upload de arquivos de audio utilizando HTTP/REST, envia esses arquivos via AMQP para o ArtemisMQ.
O retorno do REST são 3 atributos: MessageID, Time (tempo que a transcrição deve demorar) e URLRetrieverID (é um atalho que a aplicação pode fazer o GET)

## Translator
Recupera as mensagens com os arquivos de audio do ActiveMQ, submete para o modelo que faz a transcrição e retorna o texto para o ArtemisMQ

## Retriever
Disponibiliza um modo de acessar as transcrições geradas. Nesse exemplo recuperamos as transcrições diretamente do ArtemisMQ pelo ID.

Pode ser alterado para buscar de um banco (Postgres ou MongoDB) ou cache (Redis ou Infinispan).

## WhisperCPP
Modelo de fato que faz a transcrição.


## Running

`docker compose up` para subir o artemis

`quarkus dev`, ou `./mvnw quarkus:dev` nos projetos `uploader` e `translator`

Execute `./upload.sh`. Irá enviar a imagem `teste.png` para o uploader

Abra o arquivo `/tmp/saida.png`. Ele deve ter o mesmo conteudo do arquivo `teste.png`

## Console WEB Artemis
http://localhost:8161/console/

Não funciona ainda corretamente por conta de CORS

## Fontes

https://github.com/ggerganov/whisper.cpp/

https://github.com/containers/ai-lab-recipes/blob/main/models/Containerfile