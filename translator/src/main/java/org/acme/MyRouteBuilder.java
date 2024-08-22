package org.acme;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("amqp:queue:audios?receiveTimeout=100000&transactionTimeout=-1")
            .to("log:translatorEntrada?showBody=false&showHeaders=true")
            // Exemplo de curl. -F é tipo parametro do FORM.

            // curl 127.0.0.1:8001/inference \
            //     -H "Content-Type: multipart/form-data" \
            //     -F file="@jfk.wav" \
            //     -F temperature="0.0" \
            //     -F temperature_inc="0.2" \
            //     -F response_format="json"
            // .setHeader(Exchange.CONTENT_TYPE, constant("multipart/form-data"))
            // .to("file:///tmp?fileName=saida.png")
            .to("bean:transcriptAPI")
            .to("log:translatorSaida?showBody=false&showHeaders=true")
            .to("amqp:queue:transcriptions?exchangePattern=InOnly")
        ;
    }
    
}
