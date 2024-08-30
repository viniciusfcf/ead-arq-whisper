package org.acme;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        // 100.000.000 ms = 16 min o timeout
        from("activemq:queue:audios?receiveTimeout={{app.receiveTimeout}}&transactionTimeout=-1")
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
            .to("activemq:queue:transcriptions?exchangePattern=InOnly")
        ;
    }
    
}
