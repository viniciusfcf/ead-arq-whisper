package org.acme;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("amqp:queue:transcriptions")
            .to("log:transcriptions?showHeaders=true")
            .to("bean:cacheBean?method=put")
        ;

        rest("/transcriptions")
            .get("/{id}")
                .to("direct:getTranscription")
            
        ;

        from("direct:getTranscription")
            .to("bean:cacheBean?method=get(${header.id})")
            .choice()
            .when(body().isNull())
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
            .otherwise()
                .to("bean:myCipher?method=decrypt")
        ;

    }
    
}
