package org.acme;

import java.util.List;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;

public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        rest("/hello")
                .get("")
                .description("Endpoint que responde um simples OK")
                .to("direct:okResponse");

        rest("/audios")
                .post()
                .description("Endpoint que faz transcrições de audios")
                .type(List.class)
                .param().name("body").type(RestParamType.body).endParam()
                .consumes("application/octet-stream")
                
                .to("direct:sendMessage");

        from("direct:sendMessage")
                .to("log:audiosEntrada?showBody=false&showHeaders=true")
                .setHeader("TranscriptionID", () -> UUID.randomUUID().toString())
                .to("activemq:queue:audios?exchangePattern=InOnly&useMessageIDAsCorrelationID=true")
                .to("log:audiosSaida?showBody=false&showHeaders=true")
                .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
                .setBody(header("TranscriptionID"));

        from("direct:okResponse")
                .to("log:transacoes?showHeaders=true")
                .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
                .setBody(simple("OK"));
    }
}
