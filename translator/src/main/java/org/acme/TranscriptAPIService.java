package org.acme;

import java.io.InputStream;

import org.apache.camel.Exchange;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@ApplicationScoped
@Named("transcriptAPI")
public class TranscriptAPIService {

    @Inject
    ObjectMapper mapper = new ObjectMapper();

    @Inject
    @ConfigProperty(name = "app.transcriptionAPI")
    String transcriptionAPI;

    public String transcript(Exchange exchange) throws Exception {
        try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
            var body = exchange.getIn().getBody(InputStream.class);
            HttpEntity entity = MultipartEntityBuilder
                    .create()
                    .addBinaryBody(
                        "file",
                        body,
                        ContentType.APPLICATION_OCTET_STREAM, 
                        "filename")
                    .build();

            HttpPost httpPost = new HttpPost(transcriptionAPI);
            httpPost.setEntity(entity);

            final Result result = httpClient.execute(httpPost, response -> {
                // Process response message and convert it into a value object
                return new Result(response.getCode(), mapper.readTree(EntityUtils.toString(response.getEntity())));
            });
            System.out.println("TranscriptAPIService.x()" + result);
            return result.transcricao;
        }
    }

    static class Result {

        final int status;
        final JsonNode content;

        final boolean erro;

        final String transcricao;

        Result(final int status, final JsonNode content) {
            this.status = status;
            this.content = content;
            erro = this.content.get("error") != null;
            if (!erro) {
                transcricao = this.content.get("text").asText();
            } else {
                transcricao = null;
            }
        }

        @Override
        public String toString() {
            return "Result [status=" + status + ", content=" + content + ", erro=" + erro + ", transcricao="
                    + transcricao + "]";
        }

    }
}
