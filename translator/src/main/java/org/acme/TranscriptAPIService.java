package org.acme;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.camel.Exchange;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.util.Timeout;
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

    @Inject
    @ConfigProperty(name = "app.transcriptionAPITimeout")
    Integer transcriptionAPITimeout;

    public String transcript(Exchange exchange) throws Exception {
        ConnectionConfig connConfig = ConnectionConfig.custom()
                // .setConnectTimeout(timeout, TimeUnit.HOURS)
                .setSocketTimeout(transcriptionAPITimeout, TimeUnit.MINUTES)
                .build();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(3000L))
                .build();
        BasicHttpClientConnectionManager cm = new BasicHttpClientConnectionManager();
        cm.setConnectionConfig(connConfig);
        try (final CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(cm)
                .build()) {

            var body = exchange.getIn().getBody(InputStream.class);
            HttpEntity entity = MultipartEntityBuilder
                    .create()
                    .addBinaryBody(
                            "file",
                            body,
                            ContentType.APPLICATION_OCTET_STREAM,
                            "TranscriptionID="+exchange.getIn().getHeader("TranscriptionID"))
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
