package org.acme;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("amqp:queue:audios")
            .to("log:audios")
            .to("file:///tmp?fileName=saida.png")
        ;
    }
    
}
