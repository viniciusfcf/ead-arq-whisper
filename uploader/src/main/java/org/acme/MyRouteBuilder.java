package org.acme;

import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {


        rest("/hello")
            .get("/say")
                .to("direct:okResponse");
                
        rest("/audios")
            .post()
                .consumes("application/octet-stream")
                .to("direct:sendMessage");
    
        from("direct:sendMessage")
            .to("log:audiosEntrada?showBody=false&showHeaders=true")
            .setHeader("TranscriptionID", () -> UUID.randomUUID().toString())
            .to("amqp:queue:audios?exchangePattern=InOnly&useMessageIDAsCorrelationID=true")
            .to("log:audiosSaida?showBody=false&showHeaders=true")
            .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
            .setBody(header("TranscriptionID"))
        ;

        from("direct:okResponse")
            .to("log:transacoes?showHeaders=true")
            .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
            .setBody(simple("OK"))
        ;
    }
    
}
