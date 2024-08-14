package org.acme;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("amqp:queue:transcriptions")
            .to("log:transcriptions?showHeaders=true")
            .process(new CacheProcessor())
        ;
    }
    
}
