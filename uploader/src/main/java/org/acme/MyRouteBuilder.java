package org.acme;

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
            .to("amqp:queue:audios")
            .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
            .setBody(simple("OK"))
        ;

        from("direct:okResponse")
            .to("log:transacoes?showHeaders=true")
            .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
            .setBody(simple("OK"))
        ;
    }
    
}
