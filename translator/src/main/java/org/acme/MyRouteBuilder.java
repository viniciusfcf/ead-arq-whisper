package org.acme;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("amqp:queue:audios")
            .to("log:audios?showHeaders=true")
            //Aqui eu vou enviar pro Modelo e aguardar a resposta
            .to("file:///tmp?fileName=saida.png")
            .setBody(constant("Vinicius Ferraz Campos"))
            
            //Quando eu configuro correlationProperty, o camel nao altera o JMSCorrelationID
            .to("amqp:queue:transcriptions?correlationProperty=ABC")
        ;
    }
    
}
